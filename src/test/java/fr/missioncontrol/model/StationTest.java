package fr.missioncontrol.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class StationTest {

    @Test 
    void constructorShouldCreateStation() {
        Station station = new Station("mir");

        assertEquals("mir", station.getName());
        
        IllegalArgumentException exception =  assertThrows(
            IllegalArgumentException.class,
            () -> station.getResource("Oxygène")
        );

        assertEquals("Cette ressource n'existe pas.", exception.getMessage());

        IllegalArgumentException exception2 = assertThrows(
            IllegalArgumentException.class,
            () -> station.getMember("Alice")
        );

        assertEquals("Ce membre n'existe pas.", exception2.getMessage());
    }


    @ParameterizedTest 
    @EmptySource 
    @ValueSource (strings = {"   "})
    void constructorShouldRejectInvalidName(String name) {
        IllegalArgumentException e = assertThrows(
            IllegalArgumentException.class,
            () -> new Station(name)
        );

        assertEquals("Le nom de la station est obligatoire.", e.getMessage());
    }


    @Test 
    void getResourceShouldReturnResource() {
        Station station = new Station("mir");
        Resource oxygen = new Resource("Oxygène", 1000);

        station.addResource(oxygen);

        assertSame(station.getResource("Oxygène"), oxygen);
    }


    @ParameterizedTest 
    @EmptySource 
    @ValueSource (strings = {"   "})
    void getResourceShouldRejectInvalidName(String name) {
        Station station = new Station("mir");

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> station.getResource(name)
        );

        assertEquals("Le nom de la ressource est obligatoire.", exception.getMessage());
    }


    @Test 
    void getMemberShouldReturnMember() {
        Station station = new Station("mir");
        CrewMember member = new CrewMember("Alice", "Commandant", 30);

        station.addMember(member);

        assertSame(station.getMember("Alice"), member);
    }


    @ParameterizedTest 
    @EmptySource 
    @ValueSource (strings = {"   "})
    void getMemberShouldRejectInvalidName(String name) {
        Station station = new Station("mir");

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> station.getMember(name)
        );

        assertEquals("Le nom de la personne est obligatoire.", exception.getMessage());
    }


    @Test
    void addMemberShouldRejectNull() {
        Station station = new Station("mir");

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> station.addMember(null)
        );

        assertEquals("Le membre ne peut pas être null.", exception.getMessage());

        assertThrows(
            IllegalArgumentException.class,
            () -> station.getMember("Alice")
        );
    }


    @Test
    void addMemberShouldRejectExistingMember() {
        Station station = new Station("mir");
        CrewMember member = new CrewMember("Alice", "Commandant", 30);

        station.addMember(member);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> station.addMember(member)
        );

        assertEquals("Ce membre est déjà présent dans la station.", exception.getMessage());

        assertSame(member, station.getMember("Alice"));

    }


    @Test
    void addResourceShouldRejectNull() {
        Station station = new Station("mir");

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> station.addResource(null)
        );

        assertEquals("La ressource ne peut pas être nulle.", exception.getMessage());

        assertThrows(
            IllegalArgumentException.class,
            () -> station.getResource("Oxygène")
        );
    }


    @Test
    void addResourceShouldRejectExistingResource() {
        Station station = new Station("mir");
        Resource oxygen = new Resource("Oxygène", 1000);

        station.addResource(oxygen);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> station.addResource(oxygen)
        );

        assertEquals("Cette ressource existe déjà.", exception.getMessage());

        assertSame(station.getResource("Oxygène"), oxygen);
    }

}
package fr.missioncontrol.model;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


class CrewMemberTest {

    @Test 
    void constructorShouldCreateCrewMember () {
        CrewMember member = new CrewMember(
            "Alice",
            "Commandant",
            20
        );

        assertEquals("Alice", member.getName());
        assertEquals("Commandant", member.getRole());
        assertEquals(20, member.getOxygenConsumptionPerDay());

    }

    @ParameterizedTest 
    @NullSource 
    @EmptySource 
    @ValueSource (strings = {"   "})
    void constructorShouldRejectInvalidName(String name) {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new CrewMember(
                name,
                "Commandant",
                20
                )
        );

        assertEquals("Le nom de la personne est obligatoire", exception.getMessage());        
    }


    @ParameterizedTest 
    @NullSource 
    @EmptySource 
    @ValueSource (strings = {"   "})
    void constructorShouldRejectInvalidRole(String role) {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new CrewMember(
                "Alice",
                role,
                20
                )
        );

        assertEquals("Le rôle de la personne est obligatoire", exception.getMessage());        
    }


    @ParameterizedTest 
    @ValueSource (ints = {0, -1})
    void constructorShouldRejectInvalidOxygenConsumptionPerDay(int consumption) {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new CrewMember(
                "Alice",
                "Commandant",
                consumption
            )
        );

        assertEquals("La consommation d'oxygène est strictement positive", exception.getMessage());
    }

}
package fr.missioncontrol.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {

	@Test
	void constructorShouldCreateResource() {
		Resource oxygen = new Resource("Oxygène", 1000);

		assertEquals("Oxygène", oxygen.getName());
		assertEquals(1000, oxygen.getAvailableQuantity());
	}


	@Test
	void constructorShouldAcceptZeroQuantity() {
		Resource oxygen = new Resource("Oxygène", 0);

		assertEquals("Oxygène", oxygen.getName());
		assertEquals(0, oxygen.getAvailableQuantity());
	}

	
	@ParameterizedTest
	@NullSource 
	@EmptySource 
	@ValueSource(strings = { "   " })
	void constructorShouldRejectInvalidName(String name) {

		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> new Resource(name, 100));

		assertEquals(
				"Le nom est obligatoire", exception.getMessage());
	}


	@ParameterizedTest
	@ValueSource(ints = { -1, -100 })
	void constructorShouldRejectInvalidQuantity(int quantity) {

		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> new Resource("Oxygène", quantity));

		assertEquals(
				"La quantité disponible doit être positive ou nulle",
				exception.getMessage());
	}

	@Test
	void getNameShouldReturnName() {
		Resource oxygen = new Resource("Oxygène", 1000);
		assertEquals(
				"Oxygène",
				oxygen.getName());
	}

	@Test
	void getAvailableQuantityShouldReturnQuantity() {
		Resource oxygen = new Resource("Oxygène", 1000);
		assertEquals(
				1000,
				oxygen.getAvailableQuantity());
	}

	@Test
	void consumeShouldReturnAvailableQuantityReduced() {
		Resource oxygen = new Resource("Oxygène", 1000);
		oxygen.consume(100);
		assertEquals(
				900,
				oxygen.getAvailableQuantity());
	}

	@Test
	void consumeShouldReturnAvailableQuantityZero() {
		Resource oxygen = new Resource("Oxygène", 1000);
		oxygen.consume(1000);
		assertEquals(
			0,
			 oxygen.getAvailableQuantity());
	}


	@Test 
	void consumeShouldRejectQuantityGreaterThanAvailableQuantity() {
		Resource oxygen = new Resource("Oxygène", 1000);
		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> oxygen.consume(1001)
		);
		assertEquals(
			"La quantité consommée ne peut excéder la quantité restante",
			exception.getMessage()
		);
		assertEquals(1000, oxygen.getAvailableQuantity());
	}


	@ParameterizedTest 
	@ValueSource(ints = {-1, 0})
	void consumeShouldRejectInvalidValues(int value) {
		Resource oxygen = new Resource("Oxygène", 1000);

		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> oxygen.consume(value)
		);

		assertEquals("La quantité consommée doit être positive", exception.getMessage());
		assertEquals(1000, oxygen.getAvailableQuantity());
	}


	@Test
	void addShouldReturnAvailableQuantityIncreased() {
		Resource oxygen = new Resource("Oxygène", 1000);
		oxygen.add(100);
		assertEquals(1100, oxygen.getAvailableQuantity());
	}


	@ParameterizedTest 
	@ValueSource(ints = {-1, 0})
	void addShouldRejectInvalidValues(int value) {
		Resource oxygen = new Resource("Oxygène", 1000);
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
			() -> oxygen.add(value)
		);

		assertEquals(
			"La valeur ajoutée doit être positive", 
			exception.getMessage()
		);

		assertEquals(1000, oxygen.getAvailableQuantity());
	}

}
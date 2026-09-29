package fr.missioncontrol.model;


public class Resource {
    
    private String name;
    private int availableQuantity;

    public Resource(String name, int availableQuantity) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                "Le nom est obligatoire"
            );
        }

        if (availableQuantity < 0) {
            throw new IllegalArgumentException(
                "La quantité disponible doit être positive ou nulle"
            );
        }

        this.name = name;
        this.availableQuantity = availableQuantity;
    }

    public String getName() {
        return this.name;
    }

    public int getAvailableQuantity() {
        return this.availableQuantity;
    }

    public void consume(int value) {
        if (value > this.availableQuantity) {
            throw new IllegalArgumentException(
                "La quantité consommée ne peut excéder la quantité restante"
            );
        }
        if (value <= 0) {
            throw new IllegalArgumentException(
                "La quantité consommée doit être positive"
            );
        }
        this.availableQuantity -= value;
    }

    public void add(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(
                "La valeur ajoutée doit être positive"
            );
        }
        this.availableQuantity += value;
    }


}
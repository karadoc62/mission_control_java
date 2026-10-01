package fr.missioncontrol.model;


public class CrewMember {

    private String name;
    private String role;
    private int oxygenConsumptionPerDay;

    public CrewMember(String name, String role, int oxygenConsumptionPerDay) {
        
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Le nom de la personne est obligatoire");
        }

        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Le rôle de la personne est obligatoire");
        }

        if (oxygenConsumptionPerDay <= 0){
            throw new IllegalArgumentException("La consommation d'oxygène est strictement positive");
        }
        
        this.name = name;
        this.role = role;
        this.oxygenConsumptionPerDay = oxygenConsumptionPerDay;
    }


    public String getName() {
        return this.name;
    }


    public String getRole() {
        return this.role;
    }


    public int getOxygenConsumptionPerDay() {
        return this.oxygenConsumptionPerDay;
    }

}
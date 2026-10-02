package fr.missioncontrol.model;

import java.util.Map;
import java.security.KeyException;
import java.util.HashMap;

public class Station {

    private String name;
    private Map<String, Resource> resources;
    private Map<String, CrewMember> members;

    public Station(String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Le nom de la station est obligatoire.");
        }

        this.name = name;
        this.resources = new HashMap<>();
        this.members = new HashMap<>();
    }


    public String getName() {
        return this.name;
    }


    public Resource getResource(String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Le nom de la ressource est obligatoire.");
        }
        if (!this.resources.containsKey(name)) {
            throw new IllegalArgumentException("Cette ressource n'existe pas.");
        }
        return this.resources.get(name);
    }


    public CrewMember getMember(String name) {
        
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Le nom de la personne est obligatoire.");
        }
        if (!this.members.containsKey(name)) {
            throw new IllegalArgumentException("Ce membre n'existe pas.");
        }
        return this.members.get(name);
    }


    public void addMember(CrewMember member) {

        if (member == null) {
            throw new IllegalArgumentException("Le membre ne peut pas être null.");
        }
        if (this.members.containsKey(member.getName())) {
            throw new IllegalArgumentException("Ce membre est déjà présent dans la station.");
        }
        this.members.put(member.getName(), member);
    }


    public void addResource(Resource resource) {
        
        if (resource == null) {
            throw new IllegalArgumentException("La ressource ne peut pas être nulle.");
        }
        if (this.resources.containsKey(resource.getName())) {
            throw new IllegalArgumentException("Cette ressource existe déjà.");
        }
        this.resources.put(resource.getName(), resource);
    }




}
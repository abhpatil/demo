package com.fabi.demo;
import java.util.List;

class Organization {
    private String organizationName;
    private List<Database> databases;

    public Organization(String organizationName, List<Database> databases) {
        this.organizationName = organizationName;
        this.databases = databases;
    }

    // Getters and setters

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public List<Database> getDatabases() {
        return databases;
    }

    public void setDatabases(List<Database> databases) {
        this.databases = databases;
    }
}
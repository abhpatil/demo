package com.fabi.demo;

import java.util.List;

class Database {
    private String databaseName;
    private List<Table> tables;

    public Database(String databaseName, List<Table> tables) {
        this.databaseName = databaseName;
        this.tables = tables;
    }

    // Getters and setters

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
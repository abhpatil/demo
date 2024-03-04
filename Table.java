package com.fabi.demo;

import java.util.List;

class Table {
    private String tableName;
    private List<Column> columns;

    public Table(String tableName, List<Column> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    // Getters and setters

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
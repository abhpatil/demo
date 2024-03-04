package com.fabi.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SchemaManager {

    private final Map<String, Table> tableCache = new HashMap<>();
    private final SchemaService schemaService = new SchemaService();;

    @Autowired
    public SchemaManager() {
        // Initialize the Guava cache with a refresh interval of 5 minutes


        // Call scanSchema method during initialization
        initializeCache();
    }

    public Table getTable(String tableName) {
        return tableCache.get(tableName);
    }

    // Method to initialize the cache by calling scanSchema() of SchemaService
    private void initializeCache() {
        // Assuming scanSchema returns a Map<String, Table> for each table in the schema
        Map<String, Table> schemaMap = schemaService.scanSchema("interview-sandbox.c7myq48g2e87.us-east-1.rds.amazonaws.com", 5432, "toy_example", "interviewee", "fabi.aiiscool");

        // Put each table entry into the cache
        schemaMap.forEach((tableName, table) -> tableCache.put(tableName, table));
    }

    // Method to get all Table objects from the cache as a List
    public List<Table> getAllTables() {
        return tableCache.values().stream().collect(Collectors.toList());
    }

    // Method to check if a table has a column with a foreign key pointing to another table in the cache
    public List<String> getNeighborsForTable(String tableName) {
        Table table = tableCache.get(tableName);

        if (table == null) {
            // Table not found in cache
            return Collections.emptyList();
        }

        return table.getColumns().stream()
                .filter(column -> column.getForeignKeyTo() != null && tableCache.get(column.getForeignKeyTo()) != null)
                .map(Column::getForeignKeyTo)
                .collect(Collectors.toList());
    }
}

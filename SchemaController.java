package com.fabi.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schema")
public class SchemaController {

    private final SchemaManager schemaManager;

    @Autowired
    public SchemaController(SchemaManager schemaManager) {
        this.schemaManager = schemaManager;
    }

    // API to get all tables
    @GetMapping("/tables")
    public List<Table> getAllTables() {
        // Assuming SchemaManager provides a method to get all table names
        return schemaManager.getAllTables();
    }

    // API to get details of a specific table
    @GetMapping("/tables/{tableName}")
    public Table getTable(@PathVariable String tableName) {
        return schemaManager.getTable(tableName);
    }

    // API to show neighbors of a specific table
    @GetMapping("/tables/{tableName}/neighbors")
    public List<String> showNeighbours(@PathVariable String tableName) {
        // Assuming SchemaManager provides a method to get neighboring tables
        return schemaManager.getNeighborsForTable(tableName);
    }
}

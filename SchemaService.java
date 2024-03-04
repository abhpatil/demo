package com.fabi.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchemaService {

    public Map<String,Table> scanSchema(String host, int port, String dbName, String userName, String password) {
        Map<String,Table> allTables = new HashMap<>();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + dbName, userName, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                Table table = new Table(tableName, getColumns(metaData, tableName));
                allTables.put(table.getTableName(),table);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allTables;
    }

    private List<Column> getColumns(DatabaseMetaData metaData, String tableName) throws SQLException {
        List<Column> columns = new ArrayList<>();
        ResultSet columnsResultSet = metaData.getColumns(null, null, tableName, null);

        while (columnsResultSet.next()) {
            String columnName = columnsResultSet.getString("COLUMN_NAME");
            String dataType = columnsResultSet.getString("TYPE_NAME");
            int size = columnsResultSet.getInt("COLUMN_SIZE");
            boolean isNull = "YES".equals(columnsResultSet.getString("IS_NULLABLE"));
            //boolean isPK = columnsResultSet.getBoolean("COLUMN_KEY");

            String foreignKeyTo = getPrimaryKeyTableName(metaData, tableName, columnName);

            Column column = new Column(columnName, dataType, size, isNull, false, foreignKeyTo);
            columns.add(column);
        }

        return columns;
    }

    private String getPrimaryKeyTableName(DatabaseMetaData metaData, String tableName, String columnName) throws SQLException {
        ResultSet importedKeysResultSet = metaData.getImportedKeys(null, null, tableName);
        while (importedKeysResultSet.next()) {
            String fkColumnName = importedKeysResultSet.getString("FKCOLUMN_NAME");
            String fkTableName = importedKeysResultSet.getString("FKTABLE_NAME");

            if (columnName.equals(fkColumnName)) {
                // Retrieve the primary key table name for the given foreign key
                return importedKeysResultSet.getString("PKTABLE_NAME");
            }
        }

        return null;
    }
}

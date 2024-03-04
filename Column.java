package com.fabi.demo;
class Column {
    private String columnName;
    private String dataType;
    private int size;
    private boolean isNull;
    private boolean isPK;
    private String foreignKeyTo;

    public Column(String columnName, String dataType, int size, boolean isNull, boolean isPK, String foreignKeyTo) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.size = size;
        this.isNull = isNull;
        this.isPK = isPK;
        this.foreignKeyTo = foreignKeyTo;
    }

    // Getters and setters

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }

    public boolean isPK() {
        return isPK;
    }

    public void setPK(boolean PK) {
        isPK = PK;
    }

    public String getForeignKeyTo() {
        return foreignKeyTo;
    }

    public void setForeignKeyTo(String foreignKeyTo) {
        this.foreignKeyTo = foreignKeyTo;
    }
}
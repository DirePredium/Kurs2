package com.my.db;

public enum AuxiliaryTables {
	USER_TARIFF("user_tariff","id_user","id_tariff"),
	USER_EQUIPMENT("user_equipment","id_user","id_equipment");
	private String table;
    private String field1;
	private String field2;
	AuxiliaryTables(String table, String field1, String field2){
		this.table = table;
        this.field1 = field1;
        this.field2 = field2;
    }
	public String getTable(){ return table;}
    public String getField1(){ return field1;}
    public String getField2(){ return field2;}
}

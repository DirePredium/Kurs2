package com.my.db.Abstract;

import java.sql.SQLException;
import java.util.List;

public interface IEntityDao<T> {
	List<T> findAllRecords() throws SQLException;
	
	boolean addRecord(T record);

    boolean updateRecord(T record) ;

    boolean deleteRecord(T record);
    
    T findRecordById(Integer id) throws SQLException;
}

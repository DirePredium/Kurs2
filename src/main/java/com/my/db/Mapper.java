package com.my.db;

import java.sql.SQLException;

@FunctionalInterface
public interface Mapper <S, D> {
    void map(S source, D destination) throws SQLException;
}
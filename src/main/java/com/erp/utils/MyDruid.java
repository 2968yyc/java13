package com.erp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class MyDruid {

    @Autowired
    static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

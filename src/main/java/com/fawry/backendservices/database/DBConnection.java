package com.fawry.backendservices.database;


import com.fawry.constants.DatabaseConstants;
import com.fawry.constants.GeneralConstants;
import com.fawry.utilities.PropertiesReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;



public class DBConnection {

    public  Connection openConnection(String dbName) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        // Create Connection to DataBase
        String dbUrl =null;
        String username =null;
        String password =null;
        PropertiesReader propHandler = new PropertiesReader();
        Properties prop = propHandler.loadPropertiesFromFile(DatabaseConstants.DB_CONFIG_FILE_NAME);
        if (dbName.equalsIgnoreCase(DatabaseConstants.BUSINESS_ENTITY_DB_NAME)) {
            dbUrl = prop.getProperty(DatabaseConstants.BUSINESS_ENTITY_DB_URL_KEY);
            username = prop.getProperty(DatabaseConstants.BUSINESS_ENTITY_DB_USERNAME_KEY);
            password = prop.getProperty(DatabaseConstants.BUSINESS_ENTITY_DB_PASSWORD_KEY);
        }
        else if (dbName.equalsIgnoreCase(DatabaseConstants.USERS_DB_NAME)) {
            dbUrl = prop.getProperty(DatabaseConstants.USERS_DB_URL_KEY);
            username = prop.getProperty(DatabaseConstants.USERS_DB_USERNAME_KEY);
            password = prop.getProperty(DatabaseConstants.USERS_DB_PASSWORD_KEY);
        }
//        else if (dbName.equalsIgnoreCase(DatabaseConstants.SOF_DB_NAME)) {
//            dbUrl = prop.getProperty(DatabaseConstants.SOF_DB_URL_KEY);
//            username = prop.getProperty(DatabaseConstants.SOF_DB_USERNAME_KEY);
//            password = prop.getProperty(DatabaseConstants.SOF_DB_PASSWORD_KEY);
//        }
        else if (dbName.equalsIgnoreCase(DatabaseConstants.CORE_DB_NAME)) {
            dbUrl = prop.getProperty(DatabaseConstants.CORE_DB_URL_KEY);
            username = prop.getProperty(DatabaseConstants.CORE_DB_USERNAME_KEY);
            password = prop.getProperty(DatabaseConstants.CORE_DB_PASSWORD_KEY);
      }
//        else if (dbName.equalsIgnoreCase(DatabaseConstants.DELTA_FAWRYPAY_DB_NAME)) {
//            dbUrl = prop.getProperty(DatabaseConstants.DELTA_FAWRYPAY_DB_URL_KEY);
//            username = prop.getProperty(DatabaseConstants.DELTA_FAWRYPAY_DB_USERNAME_KEY);
//            password = prop.getProperty(DatabaseConstants.DELTA_FAWRYPAY_DB_PASSWORD_KEY);
//        }
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(dbUrl, username, password);
        return con;
    }
    public ArrayList<ArrayList<Object>> executeQuery(Connection conn, String sqlQuery) throws SQLException
    {
        ArrayList<ArrayList<Object>> queryResults = new ArrayList<ArrayList<Object>>();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while(rs.next()) {
            ArrayList<Object> row = new ArrayList<Object>();
            for (int i = 1; i < columnsNumber + 1; i++) {
                Object columnValue = new Object();
                columnValue = rs.getObject(i);
                row.add(columnValue);
            }
            queryResults.add(row);
        }

        return queryResults;
    }
    public ResultSet executeQueryAndGetRS(Connection conn, String sqlQuery) throws SQLException
    {
        Statement statement = conn.createStatement();;
        ResultSet rs =  statement.executeQuery(sqlQuery);
        return rs;
    }

    public int executeUpdateQuery(Connection conn, String sqlQuery ,int id) throws SQLException
    {
        Statement statement = conn.createStatement();
        String[] splitedQuery = sqlQuery.split(";");
        for (int i = 0 ; i<splitedQuery.length ; i++  )
        {
            id++;
            splitedQuery[i] =  splitedQuery[i].replaceAll("ID_VALUE", String.valueOf((id)));
            System.out.println("########" +splitedQuery[i]);
            statement.addBatch(splitedQuery[i]);
            int[] result = statement.executeBatch();
        }

        commit(conn);
        return 1;
    }
    public void commit(Connection conn) throws SQLException
    {
        conn.commit();
    }

    public  void closeDBConnection(Connection con) throws SQLException {
        con.close();
    }

}


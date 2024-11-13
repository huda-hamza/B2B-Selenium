//package com.fawry.backendservices.database.services;
//
//import com.fawry.backendservices.database.DBConnection;
//import com.fawry.backendservices.database.daos.BranchesListDAO;
//import com.fawry.constants.database.DatabaseConstants;
//import com.fawry.datamodels.moduleone.ClassOneDM;
//import com.fawry.utilities.Log;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class BranchesListService {
//
//    public ArrayList<ClassOneDM> getBranchList()throws SQLException, ClassNotFoundException {
//        //Open connection to users database
//        DBConnection conn = new DBConnection();
//        Connection connection = conn.openConnection(DatabaseConstants.BUSINESS_ENTITY_DB_NAME);
//        Log.info("Database connected successfully");
//        BranchesListDAO branchesListDAO = new BranchesListDAO();
//        ArrayList<ClassOneDM> classOneDM = branchesListDAO.getBranchList(connection);
//        //close db connection
//        conn.closeDBConnection(connection);
//        return classOneDM;
//    }
//}

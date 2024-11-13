//package com.fawry.backendservices.database.daos;
//
//import com.fawry.utilities.Log;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class BranchesListDAO extends MainDAO {
//
//    public ArrayList<BranchesListDM> getBranchList(Connection dbConn) throws SQLException {
//
//        //Create DB Query to selected added/updated offer
//        StringBuilder query = new StringBuilder();
//        query.append("select NAME_EN from BRANCH where ADDRESS = '" + branchAddress + "'");
//        System.out.println("QUERY :: "+"select NAME_EN from BRANCH where ADDRESS = '" + branchAddress + "'");
//
//
//        // Execute query
//        DBConnection conn = new DBConnection();
//        ResultSet branchRS = conn.executeQueryAndGetRS(dbConn, query.toString());
//        Log.info("query run successfully");
//
//        // fill data returned from DB into data model
//        ClassOneDM ClassOneDM;
//        ArrayList<ClassOneDM> branchListDMList = new ArrayList<>();
//
//        while (branchRS.next()) {
//            ClassOneDM = new ClassOneDM();
//            ClassOneDM.setName(branchRS.getString(BranchTable.NAME_EN) == null ? "" : branchRS.getString(BranchTable.NAME_EN));
//            branchListDMList.add(ClassOneDM);
//        }
//        return branchListDMList;
//    }
//
//
//}

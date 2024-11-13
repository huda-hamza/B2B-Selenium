//package com.fawry.backendservices;
//
//import com.fawry.backendservices.database.services.BranchesListService;
//import com.fawry.datamodels.moduleone.ClassOneDM;
//import com.fawry.utilities.Log;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class ServicesDelegate {
//
//    public ArrayList<ClassOneDM> getBranchList() throws SQLException, ClassNotFoundException {
//
//        ArrayList<ClassOneDM> classOneDMS = new ArrayList<>();
//        try {
//            BranchesListService OrdersService = new BranchesListService();
//            classOneDMS = OrdersService.getBranchList();
//        } catch (Exception e) {
//            Log.error("ERROR occurred in " + new Object() {
//            }
//                    .getClass().getName() + "." + new Object() {
//            }
//                    .getClass()
//                    .getEnclosingMethod()
//                    .getName(), e);
//        }
//        return classOneDMS;
//    }
//}

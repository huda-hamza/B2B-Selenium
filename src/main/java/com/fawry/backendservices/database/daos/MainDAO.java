package com.fawry.backendservices.database.daos;

import com.fawry.constants.B2BMerchantFile_NameConstants;
import com.fawry.constants.GeneralConstants;
import com.fawry.utilities.PropertiesReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MainDAO {
    PropertiesReader propHandler = new PropertiesReader();
    Properties branchManagementTDProperties = propHandler.loadPropertiesFromFile(GeneralConstants.GENERAL_CONFIGURATION_FILE_NAME);
    String branchAddress = branchManagementTDProperties.getProperty(B2BMerchantFile_NameConstants.ADDRESS);


    public String getTodaysDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String todayDate= dateFormat.format(date);
        return todayDate;
    }


    public String getTomorrowsDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        Date tomorrow = new Date(date.getTime() + (1000 * 60 * 60 * 24));
        String tomorrowDate= dateFormat.format(tomorrow);
        return tomorrowDate;
    }

    public String getAfterFiveDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        Date tomorrow = new Date(date.getTime() + (1000 * 60 * 60 * 600));
        String tomorrowDate= dateFormat.format(tomorrow);
        return tomorrowDate;
    }

}

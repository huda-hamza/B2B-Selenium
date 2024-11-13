package com.fawry.utilities;

import com.fawry.constants.GeneralConstants;

public class skipException extends org.testng.SkipException {

    public skipException(String message, Exception exception) {
        super(message);
        Log.error(GeneralConstants.SKIPPED_MESSAGE, exception);
    }

    public skipException(String message) {
        super(message);
        Log.error(GeneralConstants.SKIPPED_MESSAGE);
    }

}
package com.s22010170.heathtrakerapp;

import android.app.Application;

public class MyApplication extends Application {
    // Global Variables
    private String globalVariableEmail = "";
    // get the global variable
    public String getGlobalVariableEmail() {
        return globalVariableEmail;
    }
    // set the global variable
    public void setGlobalVariableEmail(String email) {
        globalVariableEmail = email;
    }
    // clear the global variable
    public void clearGlobalVariableEmail() {
        globalVariableEmail = "";
    }
}

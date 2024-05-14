package com.s22010170.heathtrakerapp;

import android.app.Application;

public class MyApplication extends Application {
    // Global Variables
    private String globalVariableEmail = "";
    private String globalVariableName = "";
    // get the global variable
    public String getGlobalVariableEmail() {
        return globalVariableEmail;
    }
    public String getGlobalVariableName() {
        return globalVariableName;
    }
    // set the global variable
    public void setGlobalVariableEmail(String email) {
        globalVariableEmail = email;
    }
    public void setGlobalVariableName(String name) {
        globalVariableName = name;
    }
    // clear the global variable
    public void clearGlobalVariableEmail() {
        globalVariableEmail = "";
    }
    public void clearGlobalVariableName() {
        globalVariableName = "";
    }
}

package com.s22010170.heathtrakerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    DataBaseHelper authDataBaseHelper;
    ShowMessage showMessage;
    TextView email, username;

    Button goToListButton, logOutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        email = rootView.findViewById(R.id.show_email);
        username = rootView.findViewById(R.id.show_username);
        goToListButton = rootView.findViewById(R.id.go_to_list_button);
        logOutButton = rootView.findViewById(R.id.log_out_button);

        // TODO: create database
        authDataBaseHelper = new DataBaseHelper(getActivity());

        // TODO: create show message object
        showMessage = new ShowMessage();

        // TODO: get the global variable
        String globalVariableEmail = ((MyApplication) requireActivity().getApplication()).getGlobalVariableEmail();

        // TODO: get the data from the database and display it on the screen
        if(!globalVariableEmail.isEmpty()){
            // TODO: get the data from the database
            Cursor cursor = authDataBaseHelper.getUserData(globalVariableEmail);
            if(cursor.getCount() != 0){
                //TODO: display the data on the screen
                while (cursor.moveToNext()) {
                    username.setText(cursor.getString(1));
                    email.setText(cursor.getString(2));
                    String isLoggedIn = cursor.getString(5);
                    //TODO: check if the user is logged in
                    if(isLoggedIn.equals("true")){
                        showMessage.show("success", "User is logged in. value is: " + isLoggedIn, getActivity());
                    }
                }
            }else{
                showMessage.show("Error", "Unable to get the data from database.", getActivity());
            }
        }
        else {
            showMessage.show("Error", "Global Variable Email is empty.", getActivity());
        }


        return rootView;
    }
}
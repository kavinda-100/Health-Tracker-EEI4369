package com.s22010170.heathtrakerapp;

import android.annotation.SuppressLint;
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

    TextView greeting;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // TODO:define variables
        greeting = rootView.findViewById(R.id.greet_user_text);
        // TODO: create database
        authDataBaseHelper = new DataBaseHelper(getActivity());
        // TODO: create show message object
        showMessage = new ShowMessage();
        // TODO: get the global variable
        String globalVariableName = ((MyApplication) requireActivity().getApplication()).getGlobalVariableName();
        if(globalVariableName != null) {
            greeting.setText("Hello, " + globalVariableName);
        }

        return rootView;
    }
}
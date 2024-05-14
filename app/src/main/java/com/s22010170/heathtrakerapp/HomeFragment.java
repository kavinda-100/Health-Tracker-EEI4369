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

        // create database
        authDataBaseHelper = new DataBaseHelper(getActivity());
        // create show message object
        showMessage = new ShowMessage();

        Intent intent = getActivity().getIntent();
        // check if the intent is not null
        if(intent != null){
            String emailText = intent.getStringExtra("email");
            // get the data from the database
            Cursor cursor = authDataBaseHelper.getUserData(emailText);
            if(cursor.getCount() != 0){
                //showMessage.show("success", "able to get the data from database.", getActivity());
                while (cursor.moveToNext()) {
                    username.setText(cursor.getString(1));
                    email.setText(cursor.getString(2));
                    String isLoggedIn = cursor.getString(5);
                    if(isLoggedIn.equals("true")){
                        showMessage.show("success", "User is logged in." + isLoggedIn, getActivity());
                    }
                }
            }else{
                showMessage.show("Error", "Unable to get the data from database.", getActivity());
            }
        }

        goToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("email", email.getText().toString());
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
                getParentFragmentManager().beginTransaction().replace(R.id.home_container, listFragment).commit();
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });


        return rootView;
    }
}
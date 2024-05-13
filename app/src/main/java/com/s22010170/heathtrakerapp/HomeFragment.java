package com.s22010170.heathtrakerapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    DataBaseHelper authDataBaseHelper;
    ShowMessage showMessage;
    TextView email, username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        email = rootView.findViewById(R.id.show_email);
        username = rootView.findViewById(R.id.show_username);

        // create database
        authDataBaseHelper = new DataBaseHelper(getActivity());
        // create show message object
        showMessage = new ShowMessage();

        // get the email from the sign in activity via intent
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
                }
            }else{
                showMessage.show("Error", "Unable to get the data from database.", getActivity());
            }
        }

        Bundle bundle = new Bundle();
        bundle.putString("email", email.getText().toString());
        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(bundle);


        return rootView;
    }
}
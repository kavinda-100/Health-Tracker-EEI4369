package com.s22010170.heathtrakerapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    DataBaseHelper authDataBaseHelper;
    SharedPrefsManager prefsManager;
    ShowMessage showMessage;
    ImageView userImage;
    TextView greeting;
    RelativeLayout medicationListItem;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // TODO:define variables
        greeting = rootView.findViewById(R.id.greet_user_text);
        medicationListItem = rootView.findViewById(R.id.medication_list_item_home);
        userImage = rootView.findViewById(R.id.user_avtar_img_home);

        // create database
        authDataBaseHelper = new DataBaseHelper(getActivity());

        // create shared preferences manager
        prefsManager = new SharedPrefsManager(requireActivity());

        // create show message object
        showMessage = new ShowMessage();

        // get the global variable
        String sharedPreferencesName = prefsManager.getString("name", null);
        // get the global image avatar
        byte[] globalVariableImageAvatar = ((MyApplication) requireActivity().getApplication()).getGlobalVariableImageAvatar();
        // check if the global image avatar is not null
        if(globalVariableImageAvatar != null) {
            Bitmap ImageAvatar = DbBitmapUtility.getImage(globalVariableImageAvatar);
            userImage.setImageBitmap(ImageAvatar);
        }
        else {
            userImage.setImageResource(R.drawable.avatarface);
        }
        // check if the global variable is not null
        if(sharedPreferencesName != null) {
            greeting.setText("Hello, " + sharedPreferencesName);
        }else{
            greeting.setText("Hello, User");
        }
        // navigate to the medication details/about fragment
        medicationListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutMedicationFragment aboutMedicationFragment = new AboutMedicationFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.home_container, aboutMedicationFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("fromHomeFragment")
                        .commit();
            }
        });

        return rootView;
    }
}
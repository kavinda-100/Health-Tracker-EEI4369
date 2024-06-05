package com.s22010170.heathtrakerapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.s22010170.heathtrakerapp.utils.DataBaseHelper;
import com.s22010170.heathtrakerapp.utils.DbBitmapUtility;
import com.s22010170.heathtrakerapp.utils.SharedPrefsManager;
import com.s22010170.heathtrakerapp.utils.ShowMessage;

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
        String sharedPreferencesUserEmail = prefsManager.getString("email", null);

        if(sharedPreferencesUserEmail != null) {
            // get the data from the database
            Cursor cursor = authDataBaseHelper.getUserData(sharedPreferencesUserEmail);
            if(cursor.getCount() != 0){
                while(cursor.moveToNext()){
                    byte[] imgAvatar = cursor.getBlob(4);
                    if(imgAvatar != null) {
                        userImage.setImageBitmap(DbBitmapUtility.getImage(imgAvatar));
                    }else{
                        userImage.setImageResource(R.drawable.avatarface);
                    }
                }
            }else{
                userImage.setImageResource(R.drawable.avatarface);
            }
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
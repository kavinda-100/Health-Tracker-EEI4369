package com.s22010170.heathtrakerapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

public class Tempo {

//    TODO: 1. This is for HomeFragment. It is used to get the data from the database and display it on the screen. It get extra data from the intent (from sign-in activity) and use it to get the data from the database.
//    Intent intent = getActivity().getIntent();
//    // check if the intent is not null
//        if(intent != null){
//        String emailText = intent.getStringExtra("email");
//        // get the data from the database
//        Cursor cursor = authDataBaseHelper.getUserData(emailText);
//        if(cursor.getCount() != 0){
//            //showMessage.show("success", "able to get the data from database.", getActivity());
//            while (cursor.moveToNext()) {
//                username.setText(cursor.getString(1));
//                email.setText(cursor.getString(2));
//                String isLoggedIn = cursor.getString(5);
//                if(isLoggedIn.equals("true")){
//                    showMessage.show("success", "User is logged in." + isLoggedIn, getActivity());
//                }
//            }
//        }else{
//            showMessage.show("Error", "Unable to get the data from database.", getActivity());
//        }
//    }

//    TODO: 2. This is for ListFragment. It is used to send the email from HomeFragment and display the email on the screen(ListFragment). It get the email from the bundle and display it on the screen.
//    goToListButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Bundle bundle = new Bundle();
//            bundle.putString("email", email.getText().toString());
//            ListFragment listFragment = new ListFragment();
//            listFragment.setArguments(bundle);
//            getParentFragmentManager().beginTransaction().replace(R.id.home_container, listFragment).commit();
//        }
//    });

//    TODO: 3. This is for ListFragment. It is used to get the email from the bundle and display it on the screen.
//    Bundle bundle = this.getArguments();
//        if(bundle != null){
//        String emailText = bundle.getString("email");
//        email.setText(emailText);
//    }
}

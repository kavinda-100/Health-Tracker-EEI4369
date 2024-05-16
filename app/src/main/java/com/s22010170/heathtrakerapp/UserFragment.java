package com.s22010170.heathtrakerapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class UserFragment extends Fragment {
    DataBaseHelper authDataBaseHelper;
    ShowMessage showMessage;
    EditText email, username, password, confirmPassword;
    Button updateButton, deleteButton, logoutButton;
    ImageView profileImage, backgroundImage;
    TextView greetingText, emailText;
    String oldPassword, newPassword;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);
        // create database
        authDataBaseHelper = new DataBaseHelper(getActivity());

        // create show message object
        showMessage = new ShowMessage();

        // TODO:define variables
        email = rootView.findViewById(R.id.update_user_email);
        username = rootView.findViewById(R.id.update_user_name);
        password = rootView.findViewById(R.id.update_user_password);
        confirmPassword = rootView.findViewById(R.id.update_user_confirm_password);
        updateButton = rootView.findViewById(R.id.update_button);
        deleteButton = rootView.findViewById(R.id.delete_button);
        logoutButton = rootView.findViewById(R.id.logout_button);
        profileImage = rootView.findViewById(R.id.user_avtar_img);
        backgroundImage = rootView.findViewById(R.id.user_background_img);
        greetingText = rootView.findViewById(R.id.user_greet_text);
        emailText = rootView.findViewById(R.id.user_email_text);

        // get the global variable
        String globalVariableEmail = ((MyApplication) requireActivity().getApplication()).getGlobalVariableEmail();

        // get the data from the database and display it on the screen
        if(!globalVariableEmail.isEmpty()){
            // get the data from the database
            Cursor cursor = authDataBaseHelper.getUserData(globalVariableEmail);
            if(cursor.getCount() != 0){
                //display the data on the screen
                while (cursor.moveToNext()) {
                    username.setText(cursor.getString(1));
                    email.setText(cursor.getString(2));
                    //password.setText(cursor.getString(3));
                    oldPassword = cursor.getString(3);
                    profileImage.setImageURI(Uri.parse(cursor.getString(4)));
                    backgroundImage.setImageURI(Uri.parse(cursor.getString(4)));
                    greetingText.setText("Hello, " + cursor.getString(1));
                    emailText.setText(cursor.getString(2));
                }
            }else{
                showMessage.show("Error", "Unable to get the data from database.", getActivity());
            }
        }
        else {
            showMessage.show("Error", "Global Variable Email is empty.", getActivity());
        }

        // TODO: update user details
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if password not provided then use the old password
                if(password.getText().toString().isEmpty() && confirmPassword.getText().toString().isEmpty()) {
                    newPassword = oldPassword;
                    updateUserData(globalVariableEmail, email.getText().toString(), username.getText().toString(), newPassword, String.valueOf(profileImage));
                    ((MyApplication) requireActivity().getApplication()).setGlobalVariableEmail(email.getText().toString());
                }
                else{
                    // if password provided then use the new password
                    newPassword = password.getText().toString();
                    // check if the password is correct and password and confirm password are same
                    if(newPassword.equals(confirmPassword.getText().toString()) && newPassword.length() < 6){
                        updateUserData(globalVariableEmail, email.getText().toString(), username.getText().toString(), newPassword, String.valueOf(profileImage));
                        ((MyApplication) requireActivity().getApplication()).setGlobalVariableEmail(email.getText().toString());
                    }else{
                        showMessage.show("Error", "Password and confirm password should be same and password should be at least 6 characters long.", getActivity());
                    }
                }
                return;

            }
        });

        // TODO:delete user account
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show confirm dialog
                new AlertDialog.Builder(requireContext())
                        .setTitle("Delete Account")
                        .setMessage("Are you sure you want to delete this account?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // delete the user account
                                boolean isDeleted = authDataBaseHelper.deleteAccount(globalVariableEmail);
                                if(isDeleted){
                                    showMessage.show("Success", "User account deleted successfully.", getActivity());
                                    ((MyApplication) requireActivity().getApplication()).clearGlobalVariableEmail();
                                    ((MyApplication) requireActivity().getApplication()).clearGlobalVariableName();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    showMessage.show("Error", "Unable to delete the user account.", getActivity());
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Handle the negative button action
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });

        // TODO:logout user
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show confirm dialog

                // update the login status
                boolean isUpdateLoginStatus = authDataBaseHelper.updateLoginStatus(globalVariableEmail, "false");
                if(isUpdateLoginStatus){
                    showMessage.show("Success", "User logged out successfully.", getActivity());
                    ((MyApplication) requireActivity().getApplication()).clearGlobalVariableEmail();
                    ((MyApplication) requireActivity().getApplication()).clearGlobalVariableName();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }else{
                    showMessage.show("Error", "Unable to logout the user.", getActivity());
                }
            }
        });



        return rootView;
    }
    // TODO:update user data method
    public void updateUserData(String oldEmail, String email, String username, String password, String imgURL){
        // update the user data
        boolean isUpdated = authDataBaseHelper.updateUserData(oldEmail, email, username, password, imgURL);
        if(isUpdated){
            showMessage.show("Success", "User data updated successfully.", getActivity());
        }else{
            showMessage.show("Error", "Unable to update the user data.", getActivity());
        }
    }
}
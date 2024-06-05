package com.s22010170.heathtrakerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.s22010170.heathtrakerapp.utils.DataBaseHelper;
import com.s22010170.heathtrakerapp.utils.ShowMessage;

public class ResetPasswordActivity extends AppCompatActivity {
    DataBaseHelper authDataBaseHelper;
    ShowMessage showMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // create database
        authDataBaseHelper = new DataBaseHelper(this);
        // create show message object
        showMessage = new ShowMessage();
        // methods
        backToResetFromToSingUp();
        reset();
    }
    public void reset(){
        Button resetButton = findViewById(R.id.reset_button);
        EditText email = findViewById(R.id.reset_email);
        EditText password = findViewById(R.id.reset_password);
        EditText confirmPassword = findViewById(R.id.reset_Confirm_password);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if the email, password and confirm password are empty
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty()) {
                    // show toast message
                    showMessage.show("Error", "Please fill all the fields", ResetPasswordActivity.this);
                    return;
                }
                // check if the password and confirm password are not the same
                if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    // show toast message
                    showMessage.show("Error", "Password and confirm password are not the same", ResetPasswordActivity.this);
                    return;
                }
                // reset password
                boolean isResetPassword = authDataBaseHelper.resetPassword(email.getText().toString(), password.getText().toString());
                // check if the password is reset
                if (isResetPassword) {
                    // show toast message
                    Toast.makeText(ResetPasswordActivity.this, "Password reset successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ResetPasswordActivity.this, SignInActivity.class);
                    startActivity(intent);
                }else{
                    // show toast message
                    showMessage.show("Error", "Invalid email", ResetPasswordActivity.this);
                }

            }
        });
    }

    private void backToResetFromToSingUp() {
        LinearLayout backToSignInButton = findViewById(R.id.Back_button_area_reset);
        backToSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPasswordActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }

}
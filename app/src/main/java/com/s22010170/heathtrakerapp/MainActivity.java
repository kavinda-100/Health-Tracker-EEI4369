package com.s22010170.heathtrakerapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    SharedPrefsManager prefsManager;
    private static final int PERMISSION_CODE = 1001;
    private String sharedPreferencesEmail;
    ShowMessage showMessage;
    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // create shared preferences manager
        prefsManager = new SharedPrefsManager(this);
        // get the email from shared preferences
        sharedPreferencesEmail = prefsManager.getString("email", "");
        // create show message object
        showMessage = new ShowMessage();

        welcome();

    }
    public void welcome(){
        Button welcomeButton = findViewById(R.id.welcome_button);
        welcomeButton.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
            @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
            @Override
            public void onClick(View v) {
                // check runtime permission
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_DENIED) {
                        // permission not granted, request it
                        String[] permissions = {android.Manifest.permission.READ_MEDIA_IMAGES, android.Manifest.permission.READ_MEDIA_VIDEO};
                        // show popup for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);
                    }else{
                        // permission already granted
                        // check if the user is already signed in
                        Intent intent;
                        if (!sharedPreferencesEmail.isEmpty()) {
                            intent = new Intent(MainActivity.this, HomeActivity.class);
                        } else {
                            intent = new Intent(MainActivity.this, SignInActivity.class);
                        }
                        startActivity(intent);
                    }
                }else{
                    // system os is less than marshmallow
                    return;
                }
            }
        });

    }
    // handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted
                // check if the user is already signed in
                Intent intent;
                if (!sharedPreferencesEmail.isEmpty()) {
                    intent = new Intent(MainActivity.this, HomeActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, SignInActivity.class);
                }
                startActivity(intent);
            } else {
                // permission was denied
                showMessage.show("Permission denied", "Permission denied. You Have Give the Permission to access the phone gallery to continue with app.", MainActivity.this);
            }
        }
    }
}
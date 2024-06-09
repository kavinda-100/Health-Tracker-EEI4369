package com.s22010170.heathtrakerapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.s22010170.heathtrakerapp.utils.AlarmReserve;
import com.s22010170.heathtrakerapp.utils.DataBaseHelper;
import com.s22010170.heathtrakerapp.utils.DbBitmapUtility;
import com.s22010170.heathtrakerapp.utils.SharedPrefsManager;
import com.s22010170.heathtrakerapp.utils.ShowMessage;

import java.io.InputStream;
import java.util.Calendar;

public class EditMedicationFragment extends Fragment {
    EditText medicationName, medicationDescription, medicationDosage, medicationRepeatTime;
    Button editMedicationButton, cancelMedicationButton, addMedicationImageButton;
    SwitchCompat medicationNotificationSwitch;
    ImageView medicationDeleteImage, medicationImagePreview;
    TextView medicationTime, imageOverviewText;
    RelativeLayout addMedicationArea;
    LinearLayout medicationTimeSetupButton;
    boolean isUserSetTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_medication, container, false);

        medicationName = rootView.findViewById(R.id.edit_medication_name);
        medicationDescription = rootView.findViewById(R.id.edit_medication_description);
        medicationDosage = rootView.findViewById(R.id.edit_medication_dosage);
        addMedicationImageButton = rootView.findViewById(R.id.edit_medication_picture_button);
        medicationImagePreview = rootView.findViewById(R.id.edit_medication_picture_preview);
        imageOverviewText = rootView.findViewById(R.id.edit_medication_picture_overview);
        medicationNotificationSwitch = rootView.findViewById(R.id.edit_notification_switch);
        addMedicationArea = rootView.findViewById(R.id.edit_medication_frequency_area);
        medicationTimeSetupButton = rootView.findViewById(R.id.edit_medication_frequency_button);
        medicationTime = rootView.findViewById(R.id.edit_medication_frequency_time);
        medicationDeleteImage = rootView.findViewById(R.id.edit_delete_time);
        medicationRepeatTime = rootView.findViewById(R.id.edit_medication_hours);
        editMedicationButton = rootView.findViewById(R.id.edit_medication_button);
        cancelMedicationButton = rootView.findViewById(R.id.edit_cancel_medication_button);

        //TODO: add switch listener
        medicationNotificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                addMedicationArea.setVisibility(View.VISIBLE);
            } else {
                addMedicationArea.setVisibility(View.GONE);
            }
        });


        // cancel medication
        cancelMedicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go back to medication list
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_container, new ListFragment()).commit();
            }
        });

        return rootView;
    }
}
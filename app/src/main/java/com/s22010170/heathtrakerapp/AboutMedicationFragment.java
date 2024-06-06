package com.s22010170.heathtrakerapp;

import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.s22010170.heathtrakerapp.utils.DataBaseHelper;
import com.s22010170.heathtrakerapp.utils.DbBitmapUtility;
import com.s22010170.heathtrakerapp.utils.ShowMessage;

public class AboutMedicationFragment extends Fragment {
    DataBaseHelper dataBaseHelper;
    ShowMessage showMessage;
    FloatingActionButton fab;
    Button editButton, deleteButton;
    TextView medicationName, medicationDosage, medicationDescription, medicationTime, medicationFrequency;
    ImageView medicationImage;
    byte[] medicationImageByteArray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_about_medication, container, false);
        // create an instance of the ShowMessage class
        dataBaseHelper = new DataBaseHelper(requireContext());
        showMessage = new ShowMessage();
        // define variables
        fab = rootView.findViewById(R.id.go_to_adu_button);
        editButton = rootView.findViewById(R.id.about_medication_edit_button);
        deleteButton = rootView.findViewById(R.id.about_medication_delete_button);
        medicationName = rootView.findViewById(R.id.about_medication_name_of_medicine_value);
        medicationDosage = rootView.findViewById(R.id.about_medication_dosage_value);
        medicationDescription = rootView.findViewById(R.id.about_medication_description_value);
        medicationImage = rootView.findViewById(R.id.about_medication_image);
        medicationTime = rootView.findViewById(R.id.about_medication_time_of_taking_value);
        medicationFrequency = rootView.findViewById(R.id.about_medication_time_of_frequency_value);

        // Get the medication id from the arguments
        Bundle args = getArguments();
        int medicationId = -1; // Default value

        if (args != null) medicationId = args.getInt("medicationId", -1);
        if(medicationId != -1){
            getMedicationDataAndPopulate(medicationId);
        }
        else{
            showMessage.show("Error", "No medication found", requireContext());
        }

        // set on click listener for the floating action button for navigating to the add Educational Resources fragment
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EducationalResourcesFragment educationalResourcesFragment = new EducationalResourcesFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.home_container, educationalResourcesFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("fromAboutMedicationFragment")
                        .commit();
            }
        });

        // set on click listener for the edit button for navigating to the edit medication fragment
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditMedicationFragment editMedicationFragment = new EditMedicationFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.home_container, editMedicationFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("fromAboutMedicationFragment")
                        .commit();
            }
        });

        // set on click listener for the delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Delete Medication")
                        .setMessage("Are you sure you want to delete this medication?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Handle the positive button action (e.g., perform the action)
                                showMessage.show("Medication Deleted", "The medication has been deleted successfully", requireContext());
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

        return rootView;
    }

    //TODO: add the method to get the medication data from the database
    public void getMedicationDataAndPopulate(int medicationId) {
        Cursor medicationData = dataBaseHelper.getMedicationById(medicationId);
        if (medicationData.getCount() == 0) {
            showMessage.show("Error", "No medication found", requireContext());
        } else {
            while (medicationData.moveToNext()) {
                medicationName.setText(medicationData.getString(1));
                medicationDescription.setText(medicationData.getString(2));
                medicationDosage.setText(medicationData.getString(3));
                medicationImageByteArray = medicationData.getBlob(4);
                medicationTime.setText(medicationData.getString(5));
                medicationFrequency.setText(medicationData.getString(6));
            }
            if(medicationImageByteArray != null) {
                medicationImage.setImageBitmap(DbBitmapUtility.getImage(medicationImageByteArray));
            }
            else{
                medicationImage.setImageResource(R.drawable.avatarbg);
            }
        }
    }

}
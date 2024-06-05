package com.s22010170.heathtrakerapp;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.s22010170.heathtrakerapp.utils.ShowMessage;

public class AboutMedicationFragment extends Fragment {

    FloatingActionButton fab;
    Button editButton, deleteButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_about_medication, container, false);
        // create an instance of the ShowMessage class
        ShowMessage showMessage = new ShowMessage();
        // define variables
        fab = rootView.findViewById(R.id.go_to_adu_button);
        editButton = rootView.findViewById(R.id.about_medication_edit_button);
        deleteButton = rootView.findViewById(R.id.about_medication_delete_button);

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
}
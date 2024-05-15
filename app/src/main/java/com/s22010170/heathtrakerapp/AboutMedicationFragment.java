package com.s22010170.heathtrakerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AboutMedicationFragment extends Fragment {

    FloatingActionButton fab;
    Button editButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_about_medication, container, false);
        // define variables
        fab = rootView.findViewById(R.id.go_to_adu_button);
        editButton = rootView.findViewById(R.id.about_medication_edit_button);

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

        return rootView;
    }
}
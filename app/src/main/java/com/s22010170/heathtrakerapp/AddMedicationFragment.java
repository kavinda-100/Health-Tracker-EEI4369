package com.s22010170.heathtrakerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AddMedicationFragment extends Fragment {

    Spinner spinner;
    Button addMedicationButton, cancelMedicationButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_medication, container, false);

        // define am_pm array
        String[] am_pm = {"AM", "PM"};

        // define variables
        spinner = rootView.findViewById(R.id.add_medication_frequency_input_am_pm);
        addMedicationButton = rootView.findViewById(R.id.add_medication_button);
        cancelMedicationButton = rootView.findViewById(R.id.add_cancel_medication_button);

        // set spinner adapter to am_pm array
        spinner.setAdapter(new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, am_pm));

        // cancel button click listener
        cancelMedicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go back to medication list
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_container, new HomeFragment()).commit();
            }
        });

        return rootView;
    }
}
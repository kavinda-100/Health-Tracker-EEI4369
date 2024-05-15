package com.s22010170.heathtrakerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class EditMedicationFragment extends Fragment {

    Spinner spinner;
    Button editMedicationButton, cancelEditMedicationButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_edit_medication, container, false);
        // a.m. or p.m. spinner
        String[] am_pm = {"a.m", "p.m"};
        // define variables
        spinner = rootView.findViewById(R.id.edit_medication_frequency_input_am_pm);
        editMedicationButton = rootView.findViewById(R.id.edit_medication_button);
        cancelEditMedicationButton = rootView.findViewById(R.id.edit_cancel_medication_button);

        // set spinner adapter
        spinner.setAdapter(new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, am_pm));

        // cancel button
        cancelEditMedicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go back to medication list
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_container, new HomeFragment()).commit();
            }
        });


        return rootView;
    }
}
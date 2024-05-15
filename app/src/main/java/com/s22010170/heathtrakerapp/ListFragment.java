package com.s22010170.heathtrakerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListFragment extends Fragment {

    FloatingActionButton fab;
    RelativeLayout medicationListItem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        // TODO: get the global variable
        String globalVariableEmail = ((MyApplication) requireActivity().getApplication()).getGlobalVariableEmail();

        fab = rootView.findViewById(R.id.add_list_button);
        medicationListItem = rootView.findViewById(R.id.medication_list_item_one);

        // navigate to the add list fragment
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMedicationFragment addMedicationFragment = new AddMedicationFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.home_container, addMedicationFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("fromListFragment")
                        .commit();
            }
        });

        // navigate to the medication details/about fragment
        medicationListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutMedicationFragment medicationDetailsFragment = new AboutMedicationFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.home_container, medicationDetailsFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("fromListFragment")
                        .commit();
            }
        });

        return rootView;
    }
}
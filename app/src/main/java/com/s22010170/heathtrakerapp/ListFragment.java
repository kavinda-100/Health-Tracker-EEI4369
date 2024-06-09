package com.s22010170.heathtrakerapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.s22010170.heathtrakerapp.utils.DataBaseHelper;
import com.s22010170.heathtrakerapp.utils.MedicationListModel;
import com.s22010170.heathtrakerapp.utils.MedicationListRecyclerViewInterface;
import com.s22010170.heathtrakerapp.utils.MedicationListRecyclerviewAdapter;
import com.s22010170.heathtrakerapp.utils.ShowMessage;

import java.util.ArrayList;

public class ListFragment extends Fragment implements MedicationListRecyclerViewInterface {
    FloatingActionButton fab;
    ArrayList<MedicationListModel> medicationList = new ArrayList<MedicationListModel>();
    RecyclerView medicationListRecyclerView;
    DataBaseHelper dataBaseHelper;
    ShowMessage showMessage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        dataBaseHelper = new DataBaseHelper(requireContext());
        showMessage = new ShowMessage();

        fab = rootView.findViewById(R.id.add_list_button);
        medicationListRecyclerView = rootView.findViewById(R.id.medication_list_recycler_view);

        //TODO: populate the medicationList Array with medication list data
        getMedicationListData();
        //TODO: NOTE- set the recycler view adapter after getMedicationListData() method called
        MedicationListRecyclerviewAdapter medicationListRecyclerviewAdapter = new MedicationListRecyclerviewAdapter(requireContext(),
                medicationList,true, this);
        medicationListRecyclerView.setAdapter(medicationListRecyclerviewAdapter);
        medicationListRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        //TODO: navigate to the add list fragment
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

        return rootView;
    }

    // get the all medication list data
    public void getMedicationListData() {
        Cursor cursor = dataBaseHelper.getAllMedications();
        if(cursor.getCount() == 0) {
//            Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show();
            medicationListRecyclerView.setVisibility(View.GONE);
//            showMessage.show("Message", "No data found", requireContext());
        } else {
            while(cursor.moveToNext()) {
                medicationListRecyclerView.setVisibility(View.VISIBLE);
                int medicationId = cursor.getInt(0);
                String medicationName = cursor.getString(1);
                String medicationDosage = cursor.getString(3);
                String medicationTime = cursor.getString(5);
                String medicationFrequency = cursor.getString(6);
                MedicationListModel medicationListModel = new MedicationListModel(medicationId, medicationName, medicationDosage, medicationTime, medicationFrequency);
                medicationList.add(medicationListModel);
            }
        }
    }

    @Override
    public void onMedicationItemClick(int position) {
        AboutMedicationFragment aboutMedicationFragment = new AboutMedicationFragment();
        // Create a new Bundle to hold arguments
        Bundle args = new Bundle();
        // Put the medication id into the Bundle
        args.putInt("medicationId", medicationList.get(position).getMedicationId());
        // Set the Bundle as arguments for the fragment
        aboutMedicationFragment.setArguments(args);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_container, aboutMedicationFragment)
                .setReorderingAllowed(true)
                .addToBackStack("fromListFragment")
                .commit();

    }
}
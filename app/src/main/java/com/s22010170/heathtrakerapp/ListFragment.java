package com.s22010170.heathtrakerapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListFragment extends Fragment {
    TextView email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        email = rootView.findViewById(R.id.show_list_email);

        // TODO: get the global variable
        String globalVariableEmail = ((MyApplication) requireActivity().getApplication()).getGlobalVariableEmail();
        email.setText(globalVariableEmail);


        return rootView;
    }
}
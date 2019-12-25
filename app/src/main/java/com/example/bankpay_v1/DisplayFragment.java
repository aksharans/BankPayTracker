package com.example.bankpay_v1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Fragment Display class which is used when displaying the accounts
 */

public class DisplayFragment extends Fragment {

    TextView t1;
    Button b;

    public DisplayFragment() {
        // Required empty public constructor
    }

    //displays the data recieved through the Bundles
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_display, container, false);


        Bundle b3 = getArguments();
        String name = b3.getString("name");

        t1 = v.findViewById(R.id.text);
        t1.setText(name);

        b = v.findViewById(R.id.exit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });

        return v;
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

}
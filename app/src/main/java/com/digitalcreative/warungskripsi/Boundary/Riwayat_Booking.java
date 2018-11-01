package com.digitalcreative.warungskripsi.Boundary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.digitalcreative.warungskripsi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Riwayat_Booking extends Fragment {
    Button back;

    public Riwayat_Booking() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_riwayat__booking, container, false);
        //Init
        sayHelloboys(view);

        //Set Value
        //talkTothem();

        //Get Value
       // getFromThem();

        //Actions
        doitBoys();

        return view;
    }

    private void doitBoys() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new BookingHere())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void sayHelloboys(View view) {
        //Button
        back = view.findViewById(R.id.btn_back);
    }

}

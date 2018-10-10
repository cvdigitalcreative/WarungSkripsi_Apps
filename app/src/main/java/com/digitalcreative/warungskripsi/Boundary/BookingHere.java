package com.digitalcreative.warungskripsi.Boundary;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.digitalcreative.warungskripsi.R;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingHere extends Fragment {
    LinearLayout linearLayout;
    TextView tv_changetime;
    Spinner sp_subbidang, sp_bagian, sp_durationtime;
    String date, pembimbing, jam;

    public BookingHere() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking_calender, container, false);

        //Init
        descTheComponent(view);


        //Actions
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invokeDialogbox();
            }
        });
        return view;
    }

    private void descTheComponent(View view) {
        //TextView
        tv_changetime =  view.findViewById(R.id.change_time);

        //LinearLayout
        linearLayout = view.findViewById(R.id.layoutthis);
        linearLayout.setClickable(true);

        //Spinner
        sp_bagian = view.findViewById(R.id.spinner_bagian);
        sp_subbidang = view.findViewById(R.id.spinner_sub_bidang);
        sp_durationtime = view.findViewById(R.id.duration_time);

        //Spinner Adapter
        ArrayAdapter<String> sub_bidang_asp = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.subbidang));
        ArrayAdapter<String> bagian_asp = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.bagian));
        ArrayAdapter<String> duration_asp = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.durasi));

        //Set Adapter
        sp_subbidang.setAdapter(sub_bidang_asp);
        sp_bagian.setAdapter(bagian_asp);
        sp_durationtime.setAdapter(duration_asp);

        //get The Value
        sp_subbidang.getSelectedItemPosition();
        sp_bagian.getSelectedItemPosition();
        sp_durationtime.getSelectedItemPosition();

    }



    private void invokeDialogbox() {
        //Get Target Data
        GetDate_BookingHere getData = new GetDate_BookingHere();
        getData.setTargetFragment(BookingHere.this, 3);

        //Set Fragment
       FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
       fragmentTransaction.replace(R.id.container, getData)
               .addToBackStack(null)
               .commit();
        System.out.println("masuk sini");

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == 3){
                date = data.getStringExtra("tanggal");
                pembimbing = data.getStringExtra("pembimbing");
                jam = data.getStringExtra("jam");

                System.out.println("ini jam berapa ya?" +jam);

            }
        }
    }
}

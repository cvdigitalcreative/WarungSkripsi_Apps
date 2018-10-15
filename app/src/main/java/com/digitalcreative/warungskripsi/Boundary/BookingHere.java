package com.digitalcreative.warungskripsi.Boundary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.digitalcreative.warungskripsi.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingHere extends Fragment {
    LinearLayout linearLayout, expand_linear;
    TextView tv_changetime, jam_view, nama_pembimbing;
    EditText def_detail_skripsi;
    Spinner sp_subbidang, sp_bagian;
    String detail_skripsi_s, getPembimbing, getTanggal, getJam;
    int subbidangs, bagians, getSubbidang, getBagian;

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

        //getValue
        goWithit();

        //Actions
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment_Calender();
            }
        });
        return view;
    }


    private void descTheComponent(View view) {
        //TextView
        tv_changetime = view.findViewById(R.id.change_time);
        jam_view = view.findViewById(R.id.jadwal_jam_expandview);
        nama_pembimbing = view.findViewById(R.id.nama_pembimbing_expandview);

        //LinearLayout
        linearLayout = view.findViewById(R.id.layoutthis);
        linearLayout.setClickable(true);
        expand_linear = view.findViewById(R.id.expand_view);

        //Spinner
        sp_bagian = view.findViewById(R.id.spinner_bagian);
        sp_subbidang = view.findViewById(R.id.spinner_sub_bidang);

        //Editext
        def_detail_skripsi = view.findViewById(R.id.detail_skripsi);
    }

    private void goWithit() {
        //Spinner Adapter
        final ArrayAdapter<String> sub_bidang_asp = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.subbidang));
        ArrayAdapter<String> bagian_asp = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.bagian));

        //getBundle
        try {
            getSubbidang = getArguments().getInt("subbidang_final");
            getBagian = getArguments().getInt("bagian_final");
            getPembimbing = getArguments().getString("pembimbing");
            getTanggal = getArguments().getString("tanggal");
            getJam = getArguments().getString("jam");

            //SetExpandableView Linear
            if (getPembimbing != null) {
                expand_linear.setVisibility(View.VISIBLE);

                jam_view.setText("Jadwal : " + getJam);
                nama_pembimbing.setText("Nama Pembimbing : " + getPembimbing);
            }

        } catch (Exception e) {
            System.out.println("Null gan");
            getTanggal = getCurrentdate();
        }


        //Set Adapter
        sp_subbidang.setAdapter(sub_bidang_asp);
        sp_bagian.setAdapter(bagian_asp);

        //Set state
        sp_subbidang.setSelection(getSubbidang);
        sp_bagian.setSelection(getBagian);

        //get Spinner Value
        sp_subbidang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subbidangs = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_bagian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("ini coy2 " + i);
                bagians = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //get detail skripsi
        detail_skripsi_s = def_detail_skripsi.getText().toString();

        //getTextview
        tv_changetime.setText(getTanggal);

    }

    private String getCurrentdate() {
        String finaledate;
        int dayofWeek;
        Calendar calendar = Calendar.getInstance();
        Date cal = calendar.getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");

        dayofWeek = calendar.get(Calendar.DAY_OF_WEEK);

        finaledate = generateDays(dayofWeek) +"," +df.format(cal);
        return finaledate;
    }

    private String generateDays(int day_of_week) {
        String hasil = null;
        switch (day_of_week){
            case 1 : hasil = "Minggu";break;
            case 2 : hasil = "Senin";break;
            case 3 : hasil = "Selasa";break;
            case 4 : hasil = "Rabu";break;
            case 5 : hasil = "Kamis";break;
            case 6 : hasil = "Jum'at";break;
            case 7 : hasil = "Sabtu";break;
            default:
                System.out.println("Hari Tidak Ditemukan");break;
        }
        return hasil;
    }

    public void callFragment_Calender() {
        //GetTheValue SubBidang and Bagian
        Bundle bundle = new Bundle();
        bundle.putInt("subidang_kirim", subbidangs);
        bundle.putInt("bagian_kirim", bagians);

        GetDate_BookingHere getData = new GetDate_BookingHere();
        getData.setArguments(bundle);

        //Set Fragment
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, getData)
                .addToBackStack(null)
                .commit();

    }
}

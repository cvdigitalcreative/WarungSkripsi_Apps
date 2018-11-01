package com.digitalcreative.warungskripsi.Boundary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.digitalcreative.warungskripsi.Controllers.s.Animations.ExpandCollapse;
import com.digitalcreative.warungskripsi.ModelData.Transaksi;
import com.digitalcreative.warungskripsi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingHere extends Fragment {
    DatabaseReference traxRef;
    LinearLayout linearLayout, expand_linear, jadwal_expandview, jadwal_detail_expand;
    TextView tv_changetime, jam_view, nama_pembimbing, jadwalkonsultasi, tanggal_pesan, tampilkan;
    EditText def_detail_skripsi;
    Spinner sp_subbidang, sp_bagian;
    Button btnKonfirmasi;
    String detail_skripsi_s, getPembimbing, getTanggal, getJam, getSubbidang, getBagian, getIdKonsultan, jadwalkonsultasi_ada = "tidak";
    int subbidangs, bagians;

    ExpandCollapse animation;

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

        //SetValue
        jadwal_detail_expand.setVisibility(View.GONE);

        //getValue
        goWithit();

        //Actions
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment_Calender();
            }
        });
            if (jadwalkonsultasi_ada.contains("ada")){
                jadwal_expandview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (jadwal_detail_expand.isShown()){
                            animation.collapse(jadwal_detail_expand);
                            jadwal_detail_expand.setVisibility(View.VISIBLE);
                            tampilkan.setText("Lihat Detail");
                            tampilkan.setClickable(false);
                        } else {
                            animation.expand(jadwal_detail_expand);
                            jadwal_detail_expand.setVisibility(View.VISIBLE);
                            tampilkan.setText("Lihat Riwayat");
                            tampilkan.setClickable(true);
                            tampilkan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                    fragmentTransaction.replace(R.id.container, new Riwayat_Booking())
                                            .addToBackStack(null)
                                            .commit();
                                }
                            });
                        }
                    }
                });
            } else {
                jadwalkonsultasi.setText("Tidak Ada Jadwal Konsultasi");
                jadwal_detail_expand.setVisibility(View.GONE);
                tanggal_pesan.setVisibility(View.GONE);
                tampilkan.setText("Lihat Riwayat");
                tampilkan.setClickable(true);
                tampilkan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new Riwayat_Booking())
                                .addToBackStack(null)
                                .commit();
                    }
                });
            }
        goKonfirmasi();
        return view;
    }

    private void goKonfirmasi() {
        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getBundle
                try {
                    getIdKonsultan = getArguments().getString("id_konsultan");
                    getSubbidang = getArguments().getString("subbidang_final");
                    getBagian = getArguments().getString("bagian_final");
                    getPembimbing = getArguments().getString("pembimbing");
                    getTanggal = getArguments().getString("tanggal");
                    getJam = getArguments().getString("jam");

                    //SetExpandableView Linear
                    if (getPembimbing != null) {
                        traxRef = FirebaseDatabase.getInstance().getReference();
                        String key = traxRef.push().getKey();

                        System.out.println("Id Konsultan : "+getIdKonsultan);
                        Transaksi transaksi = new Transaksi();
                        transaksi.setId_konsultasi(getIdKonsultan);
                        transaksi.setId_mahasiswa("m-1");
                        transaksi.setTanggal(getTanggal);
                        transaksi.setSesi(getJam);

                        traxRef.child("transaksi").child(key).setValue(transaksi);
                        traxRef.child("konsultan").child(getIdKonsultan).child("histori_trax").child(getTanggal).child("id_trax").setValue(key);
                        traxRef.child("mahasiswa").child("m-1").child("histori_trax").child(getTanggal).child("id_trax").setValue(key);

                    }

                } catch (Exception e) {
                    System.out.println("Konfirmasi Gagal");
                }
            }
        });
    }


    private void descTheComponent(View view) {
        //TextView
        btnKonfirmasi = view.findViewById(R.id.btn_konfirmasi);
        tv_changetime = view.findViewById(R.id.change_time);
        jam_view = view.findViewById(R.id.jadwal_jam_expandview);
        nama_pembimbing = view.findViewById(R.id.nama_pembimbing_expandview);
        jadwalkonsultasi = view.findViewById(R.id.textview_konsultasi);
        tanggal_pesan = view.findViewById(R.id.tanggal_pesan);
        tampilkan = view.findViewById(R.id.tampilkan);

        //LinearLayout
        linearLayout = view.findViewById(R.id.layoutthis);
        linearLayout.setClickable(true);
        expand_linear = view.findViewById(R.id.expand_view);
        jadwal_expandview = view.findViewById(R.id.collipse_show);
        jadwal_detail_expand = view.findViewById(R.id.text_collispe_detail);

        //Spinner
        sp_bagian = view.findViewById(R.id.spinner_bagian);
        sp_subbidang = view.findViewById(R.id.spinner_sub_bidang);

        //Editext
        def_detail_skripsi = view.findViewById(R.id.detail_skripsi);

        //Animasi
        animation =  new ExpandCollapse();
    }

    private void goWithit() {
        ArrayList<String> sub_bidang_list, bagian_list;
        sub_bidang_list = new ArrayList<>();
        bagian_list = new ArrayList<>();

        for(int i=0; i<getResources().getStringArray(R.array.subbidang).length; i++){
            sub_bidang_list.add(getResources().getStringArray(R.array.subbidang)[i]);
        }

        for(int i=0; i<getResources().getStringArray(R.array.bagian).length; i++){
            bagian_list.add(getResources().getStringArray(R.array.bagian)[i]);
        }

        //Spinner Adapter
        final ArrayAdapter<String> sub_bidang_asp = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.subbidang));
        ArrayAdapter<String> bagian_asp = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.bagian));

        //getBundle
        try {
            getSubbidang = getArguments().getString("subbidang_final");
            getBagian = getArguments().getString("bagian_final");
            getPembimbing = getArguments().getString("pembimbing");
            getTanggal = getArguments().getString("tanggal");
            getJam = getArguments().getString("jam");

            //SetExpandableView Linear
            if (getPembimbing != null) {
                expand_linear.setVisibility(View.VISIBLE);

                jam_view.setText("Jadwal : " + getJam);
                nama_pembimbing.setText("Nama Pembimbing : " + getPembimbing);

                //Set state
                for(int i=0; i<getResources().getStringArray(R.array.subbidang).length; i++){
                    if(sub_bidang_list.get(i).equals(getSubbidang)){
                        sp_subbidang.setSelection(i);
                    }
                }

                for(int i=0; i<getResources().getStringArray(R.array.bagian).length; i++){
                    if(sub_bidang_list.get(i).equals(getBagian)){
                        sp_bagian.setSelection(i);
                    }
                }

                //Set Adapter
                sp_subbidang.setAdapter(sub_bidang_asp);
                sp_bagian.setAdapter(bagian_asp);
            }

        } catch (Exception e) {
            System.out.println("Null gan");
            getTanggal = getCurrentdate();

            //Set Adapter
            sp_subbidang.setAdapter(sub_bidang_asp);
            sp_bagian.setAdapter(bagian_asp);
        }

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
        bundle.putString("subidang_kirim", sp_subbidang.getSelectedItem().toString());
        bundle.putString("bagian_kirim", sp_bagian.getSelectedItem().toString());

        GetDate_BookingHere getData = new GetDate_BookingHere();
        getData.setArguments(bundle);

        //Set Fragment
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, getData)
                .addToBackStack(null)
                .commit();

    }
}

package com.digitalcreative.warungskripsi.Boundary;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.digitalcreative.warungskripsi.Controllers.Adapters.List_Pembimbing_RecycleView;
import com.digitalcreative.warungskripsi.ModelData.InformasiKonsultan;
import com.digitalcreative.warungskripsi.ModelData.JadwalKonsultan;
import com.digitalcreative.warungskripsi.ModelData.Konsultan;
import com.digitalcreative.warungskripsi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetDate_BookingHere extends Fragment {
    ArrayList<Konsultan> list =  new ArrayList<>();
    ArrayList<String> sesiList = new ArrayList<>();
    List_Pembimbing_RecycleView listPembimbingRecycleView;
    RecyclerView recyclerView;
    DatabaseReference konsultanRef;
    TextView keterangan;
    String subbidang, bagian;
    CalendarView calendarView;
    Context context;
    String getDate, getJam, getPembimbing, tanggal;

    public GetDate_BookingHere() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_date__booking_here, container, false);

        //Init
        descTheComponent(view);

        //getValue
         getThisValue();

        //Actions
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                //Ambil data tanggal bulan hari
                getDate = processDate(year, month+1, day);

                tanggal = String.valueOf(day)+"-"+String.valueOf(month+1)+"-"+String.valueOf(year);

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date();
                String current_date = dateFormat.format(date);

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                try {
                    Date date1 = sdf.parse(current_date);
                    Date date2 = sdf.parse(tanggal);

                    if(date2.compareTo(date1) > 0){
                        //Show list pembimbing
                        showNgetListPembimbing(getDate);
                    }
                    else if(date2.compareTo(date1) == 0){
                        list = new ArrayList<>();
                        sesiList = new ArrayList<>();

                        if(!list.isEmpty()){
                            keterangan.setVisibility(View.INVISIBLE);
                        }
                        else{
                            keterangan.setText(R.string.keterangan_lewat);
                            keterangan.setVisibility(View.VISIBLE);
                        }

                        listPembimbingRecycleView = new List_Pembimbing_RecycleView(list, sesiList, context);
                        recyclerView.setAdapter(listPembimbingRecycleView);
                    }
                    else{
                        list = new ArrayList<>();
                        sesiList = new ArrayList<>();

                        if(!list.isEmpty()){
                            keterangan.setVisibility(View.INVISIBLE);
                        }
                        else{
                            keterangan.setText(R.string.keterangan_lewat);
                            keterangan.setVisibility(View.VISIBLE);
                        }

                        listPembimbingRecycleView = new List_Pembimbing_RecycleView(list, sesiList, context);
                        recyclerView.setAdapter(listPembimbingRecycleView);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    public void getThisValue() {
        subbidang = getArguments().getString("subidang_kirim");
        bagian = getArguments().getString("bagian_kirim");

    }

    private void showNgetListPembimbing(String getDate) {
        list = new ArrayList<>();
        sesiList = new ArrayList<>();
        konsultanRef = FirebaseDatabase.getInstance().getReference();

        konsultanRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot traxSnapshot : dataSnapshot.child("transaksi").getChildren()){
                    if(traxSnapshot.child("tanggal").getValue().toString().equals(tanggal)){
                        String sesi = traxSnapshot.child("sesi").getValue().toString();
                        sesiList.add(sesi);
                    }
                }

                for(DataSnapshot konsultanSnapshot : dataSnapshot.child("konsultan").getChildren()){
                    String subbidang_db = konsultanSnapshot.child("informasi").child("subbidang").getValue().toString();
                    String bagian_db = konsultanSnapshot.child("informasi").child("bagian").getValue().toString();

                    if(subbidang_db.equals(subbidang) && bagian_db.equals(bagian)){
                        for(DataSnapshot tanggalSnapshot : konsultanSnapshot.child("jadwal").getChildren()){
                            String tanggal_database;
                            int jadwal_awal, jadwal_akhir;
                            tanggal_database = tanggalSnapshot.getKey();

                            if(tanggal_database.equals(tanggal)){
                                InformasiKonsultan info_konsultan = konsultanSnapshot.child("informasi").getValue(InformasiKonsultan.class);
                                JadwalKonsultan jadwal_konsultan = new JadwalKonsultan();

                                jadwal_awal = Integer.valueOf(tanggalSnapshot.child("jadwal_awal").getValue().toString());
                                jadwal_akhir = Integer.valueOf(tanggalSnapshot.child("jadwal_akhir").getValue().toString());

                                jadwal_konsultan.setTanggal(tanggal);
                                jadwal_konsultan.setJadwal_awal(jadwal_awal);
                                jadwal_konsultan.setJadwal_akhir(jadwal_akhir);

                                Konsultan konsultan = new Konsultan(konsultanSnapshot.getKey(), info_konsultan, jadwal_konsultan);
                                list.add(konsultan);
                            }
                        }
                    }
                }

                if(!list.isEmpty()){
                    keterangan.setVisibility(View.INVISIBLE);
                }
                else{
                    keterangan.setText(R.string.keterangan);
                    keterangan.setVisibility(View.VISIBLE);
                }

                listPembimbingRecycleView = new List_Pembimbing_RecycleView(list, sesiList, context);
                recyclerView.setAdapter(listPembimbingRecycleView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void descTheComponent(View view) {
        calendarView = view.findViewById(R.id.calendeView);
        keterangan = view.findViewById(R.id.keterangan);
        recyclerView = view.findViewById(R.id.recycler);
        context = getActivity().getApplicationContext();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private String processDate(int year, int i, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, i, day);
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
        int months =  calendar.get(Calendar.MONTH);
        String day_of_month = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String years = String.valueOf(calendar.get(Calendar.YEAR));

        String hari = generateDays(day_of_week);
        String bulan = generateMonth(months);

        String date = hari +", " +day_of_month +" " +bulan +" " +years;

        return date;
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

    private String generateMonth(int month) {
        String hasil = null;
        switch (month) {
            case 1:
                hasil = "Januari";
                break;
            case 2:
                hasil = "Februari";
                break;
            case 3:
                hasil = "Maret";
                break;
            case 4:
                hasil = "April";
                break;
            case 5:
                hasil = "Mei";
                break;
            case 6:
                hasil = "Juni";
                break;
            case 7:
                hasil = "Juli";
                break;
            case 8:
                hasil = "Agustus";
                break;
            case 9:
                hasil = "September";
                break;
            case 10:
                hasil = "Oktober";
                break;
            case 11:
                hasil = "November";
                break;
            case 12:
                hasil = "Desember";
                break;
            default:
                System.out.println("Hari Tidak Ditemukan");
                break;
        }
        return hasil;
    }
}

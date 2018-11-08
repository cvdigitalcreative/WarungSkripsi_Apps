package com.digitalcreative.warungskripsi.Boundary;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.digitalcreative.warungskripsi.Controllers.s.Adapter.List_Pembimbing_RecycleView;
import com.digitalcreative.warungskripsi.Controllers.s.Adapter.List_Riwayat_RecycleView;
import com.digitalcreative.warungskripsi.ModelData.InformasiKonsultan;
import com.digitalcreative.warungskripsi.ModelData.JadwalKonsultan;
import com.digitalcreative.warungskripsi.ModelData.Konsultan;
import com.digitalcreative.warungskripsi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Riwayat_Booking extends Fragment {
    Button back;
    String getIdMahasiswa;
    List_Riwayat_RecycleView listRiwayatRecycleView;
    ArrayList<String[]> list =  new ArrayList<>();
    DatabaseReference riwayatRef;
    RecyclerView recyclerView;
    Context context;

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
        talkTothem();

        //Get Value
        //getFromThem();

        //Actions
        doitBoys();

        return view;
    }

    private void doitBoys() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getIdMahasiswa = getArguments().getString("id_mahasiswa");

                    Bundle bundle = new Bundle();
                    bundle.putString("id_mahasiswa", getIdMahasiswa);

                    BookingHere getData = new BookingHere();
                    getData.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, getData)
                            .addToBackStack(null)
                            .commit();

                } catch (Exception e) {
                    System.out.println("Data akun tidak ada");
                }

            }
        });
    }

    private void talkTothem() {
        //get Database
        try {
            getIdMahasiswa = getArguments().getString("id_mahasiswa");

            riwayatRef = FirebaseDatabase.getInstance().getReference();

            riwayatRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot mahasiswaSnapshot : dataSnapshot.child("mahasiswa").getChildren()){
                        if(mahasiswaSnapshot.getKey().equals(getIdMahasiswa)){
                            for(DataSnapshot riwayatSnapshot : mahasiswaSnapshot.child("histori_trax").getChildren()){
                                String tanggal = riwayatSnapshot.getKey();

                                for(DataSnapshot traxSnapshot : dataSnapshot.child("transaksi").getChildren()){
                                    for(DataSnapshot konsultanSnapshot : dataSnapshot.child("konsultan").getChildren()){
                                        if(konsultanSnapshot.getKey().equals(traxSnapshot.child("id_konsultasi").getValue().toString())){
                                            String nama = konsultanSnapshot.child("informasi").child("nama").getValue().toString();
                                            String sesi = traxSnapshot.child("sesi").getValue().toString();

                                            String[] riwayat = new String[3];
                                            riwayat[0] = tanggal;
                                            riwayat[1] = nama;
                                            riwayat[2] = sesi;

                                            list.add(riwayat);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    listRiwayatRecycleView = new List_Riwayat_RecycleView(list, context);
                    recyclerView.setAdapter(listRiwayatRecycleView);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            System.out.println("Data akun tidak ada");
        }
    }

    private void sayHelloboys(View view) {
        //Button
        back = view.findViewById(R.id.btn_back);
        recyclerView = view.findViewById(R.id.recycler);
        context = getActivity().getApplicationContext();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}

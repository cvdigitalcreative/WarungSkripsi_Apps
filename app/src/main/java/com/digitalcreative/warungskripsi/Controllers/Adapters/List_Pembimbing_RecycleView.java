package com.digitalcreative.warungskripsi.Controllers.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.digitalcreative.warungskripsi.Boundary.BookingHere;
import com.digitalcreative.warungskripsi.ModelData.Pembimbing_Model;
import com.digitalcreative.warungskripsi.R;

import java.util.ArrayList;
import java.util.List;

public class List_Pembimbing_RecycleView extends RecyclerView.Adapter<List_Pembimbing_RecycleView.ViewHolder>{
    List<Pembimbing_Model> list;
    Context context;
    String nama_pembimbing, tanggal, getGetShowText_jam;
    int bagian, subbidang;

    public List_Pembimbing_RecycleView(ArrayList<Pembimbing_Model> list, Context c) {
        this.list = list;
        this.context = c;
    }

    @NonNull
    @Override
    public List_Pembimbing_RecycleView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final List_Pembimbing_RecycleView.ViewHolder holder, int pos) {
        //init
        final Pembimbing_Model model = list.get(pos);
        nama_pembimbing = model.getNamaPembimbing();
        tanggal = model.getTanggal();
        subbidang = model.getSubbidang();
        bagian = model.getBagian();

        //set
        holder.namaPembimbing.setText(nama_pembimbing);
        holder.statusPembimbing.setText(model.getStatusPembimbing());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gambarPembimbing;
        TextView namaPembimbing, statusPembimbing, namaPembimbing_dialog;
        Button jadwal_oke;
        RadioGroup radioGroup;
        String[] showjam;
        Context c1;
        String getText_jamToFirebase;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Init
            descTheComponent(itemView);

            //Actions
            addToClick(itemView);
        }

        private void addToClick(View itemView) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Custom Dialog
                    final Dialog dialog = new Dialog(c1);
                    dialog.setContentView(R.layout.dialogalert_custom);
                    dialog.setTitle("Pilih Jadwal");

                    //Init
                    descTheComponent_dialog(dialog);

                    //Set Radio Button
                    setRadioButton();

                    //set to Dialog
                    namaPembimbing_dialog.setText(nama_pembimbing);
                    jadwal_oke.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sendTheData();
                            dialog.dismiss();

                        }
                    });
                    dialog.show();
                }
            });
        }

        private void sendTheData(){
            Bundle bundle = new Bundle();

            bundle.putString("jam", getGetShowText_jam);
            bundle.putString("tanggal", tanggal);
            bundle.putString("pembimbing", nama_pembimbing);
            bundle.putInt("bagian_final", bagian);
            bundle.putInt("subbidang_final", subbidang);

            BookingHere bookingHere =  new BookingHere();
            bookingHere.setArguments(bundle);

            AppCompatActivity appCompatActivity = (AppCompatActivity) itemView.getContext();
            appCompatActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, bookingHere)
                    .commit();
        }

        private void setRadioButton() {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i){
                        case R.id.rbtn_jam9:
                            getText_jamToFirebase = showjam[0];
                            getGetShowText_jam = "09.00 s/d 10.00";
                            break;
                        case R.id.rbtn_jam10:
                            getText_jamToFirebase = showjam[1];
                            getGetShowText_jam = "10.00 s/d 11.00";
                            break;
                        case R.id.rbtn_jam11:
                            getText_jamToFirebase = showjam[2];
                            getGetShowText_jam = "11.00 s/d 12.00";
                            break;
                        case R.id.rbtn_jam12:
                            getText_jamToFirebase = showjam[3];
                            getGetShowText_jam = "12.00 s/d 13.00";
                            break;
                        case R.id.rbtn_jam13:
                            getText_jamToFirebase = showjam[4];
                            getGetShowText_jam = "13.00 s/d 14.00";
                            break;
                        case R.id.rbtn_jam14:
                            getText_jamToFirebase = showjam[5];
                            getGetShowText_jam = "14.00 s/d 15.00";
                            break;
                        case R.id.rbtn_jam15:
                            getText_jamToFirebase = showjam[6];
                            getGetShowText_jam = "15.00 s/d 16.00";
                            break;
                        case R.id.rbtn_jam16:
                            getText_jamToFirebase = showjam[7];
                            getGetShowText_jam = "17.00 s/d 18.00";
                            break;
                    }
                }
            });
        }

        private void descTheComponent_dialog(Dialog dialog) {
            //TextVIew
            namaPembimbing_dialog = dialog.findViewById(R.id.nama_pembimbing_customdialog);

            //Button
            jadwal_oke = dialog.findViewById(R.id.btn_booking_ok);

            //RadioGroup
            radioGroup = dialog.findViewById(R.id.radiogroup);
        }

        private void descTheComponent(View itemView) {
            //Cardview
            gambarPembimbing = itemView.findViewById(R.id.image_cardview);
            namaPembimbing = itemView.findViewById(R.id.namapembimbing_cardview);
            statusPembimbing = itemView.findViewById(R.id.status_pembimbing_cardview);

            //isi array showjam
            showjam = itemView.getResources().getStringArray(R.array.jam);

            //Context
            c1 = itemView.getContext();
        }
    }
}

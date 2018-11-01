package com.digitalcreative.warungskripsi.Controllers.s.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.digitalcreative.warungskripsi.Boundary.BookingHere;
import com.digitalcreative.warungskripsi.ModelData.InformasiKonsultan;
import com.digitalcreative.warungskripsi.ModelData.JadwalKonsultan;
import com.digitalcreative.warungskripsi.ModelData.Konsultan;
import com.digitalcreative.warungskripsi.R;

import java.util.ArrayList;
import java.util.List;

public class List_Pembimbing_RecycleView extends RecyclerView.Adapter<List_Pembimbing_RecycleView.ViewHolder>{
    List<Konsultan> list;
    List<String> sesi_list;
    Context context;
    String nama_pembimbing, tanggal, getGetShowText_jam, bagian, subbidang, id_konsultan;
    int kuota;

    public List_Pembimbing_RecycleView(ArrayList<Konsultan> list, List<String> sesi_list, Context c) {
        this.list = list;
        this.sesi_list = sesi_list;
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
        final Konsultan konsultan = list.get(pos);

        InformasiKonsultan info = konsultan.getInformasi();
        JadwalKonsultan jadwal = konsultan.getJadwal();

        nama_pembimbing = info.getNama();
        tanggal = jadwal.getTanggal();
        subbidang = info.getSubbidang();
        bagian = info.getBagian();

        kuota = Math.abs(jadwal.getJadwal_akhir()-jadwal.getJadwal_awal());

        //set
        holder.namaPembimbing.setText(nama_pembimbing);
        holder.statusPembimbing.setText(String.valueOf(kuota));

        //Actions
        holder.addToClick(konsultan);
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
        RadioButton radioJam9, radioJam10, radioJam11, radioJam12, radioJam13, radioJam14, radioJam15, radioJam16;
        String[] showjam;
        Context c1;
        String getText_jamToFirebase;
        CardView cv_konsultan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Init
            descTheComponent(itemView);
        }

        private void addToClick(final Konsultan konsultan) {
            cv_konsultan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Custom Dialog
                    final Dialog dialog = new Dialog(c1);
                    dialog.setContentView(R.layout.dialogalert_custom);
                    dialog.setTitle("Pilih Jadwal");

                    //Init
                    descTheComponent_dialog(dialog);

                    //Set Radio Button
                    setRadioButton(konsultan);

                    //set to Dialog
                    namaPembimbing_dialog.setText(konsultan.getInformasi().getNama());
                    jadwal_oke.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sendTheData(konsultan);
                            dialog.dismiss();

                        }
                    });
                    dialog.show();
                }
            });
        }

        private void sendTheData(Konsultan konsultan){
            Bundle bundle = new Bundle();

            bundle.putString("id_konsultan", konsultan.getId_konsultan());
            bundle.putString("jam", getGetShowText_jam);
            bundle.putString("tanggal", konsultan.getJadwal().getTanggal());
            bundle.putString("pembimbing", konsultan.getInformasi().getNama());
            bundle.putString("bagian_final", konsultan.getInformasi().getBagian());
            bundle.putString("subbidang_final", konsultan.getInformasi().getSubbidang());

            BookingHere bookingHere =  new BookingHere();
            bookingHere.setArguments(bundle);

            AppCompatActivity appCompatActivity = (AppCompatActivity) itemView.getContext();
            appCompatActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, bookingHere)
                    .commit();
        }

        private void setRadioButton(Konsultan konsultan) {
            //disable for radiobutton konsultan
            for(int i=0; i<8; i++){
                if(i == 0){
                    if(9 < konsultan.getJadwal().getJadwal_awal() || 10 > konsultan.getJadwal().getJadwal_akhir()){
                        radioJam9.setEnabled(false);
                    }
                }
                else if(i == 1){
                    if(10 < konsultan.getJadwal().getJadwal_awal() || 11 > konsultan.getJadwal().getJadwal_akhir()){
                        radioJam10.setEnabled(false);
                    }
                }
                else if(i == 2){
                    if(11 < konsultan.getJadwal().getJadwal_awal() || 12 > konsultan.getJadwal().getJadwal_akhir()){
                        radioJam11.setEnabled(false);
                    }
                }
                else if(i == 3){
                    if(12 < konsultan.getJadwal().getJadwal_awal() || 13 > konsultan.getJadwal().getJadwal_akhir()){
                        radioJam12.setEnabled(false);
                    }
                }
                else if(i == 4){
                    if(13 < konsultan.getJadwal().getJadwal_awal() || 14 > konsultan.getJadwal().getJadwal_akhir()){
                        radioJam13.setEnabled(false);
                    }
                }
                else if(i == 5){
                    if(14 < konsultan.getJadwal().getJadwal_awal() || 15 > konsultan.getJadwal().getJadwal_akhir()){
                        radioJam14.setEnabled(false);
                    }
                }
                else if(i == 6){
                    if(15 < konsultan.getJadwal().getJadwal_awal() || 16 > konsultan.getJadwal().getJadwal_akhir()){
                        radioJam15.setEnabled(false);
                    }
                }
                else if(i == 7){
                    if(16 < konsultan.getJadwal().getJadwal_awal() || 17 > konsultan.getJadwal().getJadwal_akhir()){
                        radioJam16.setEnabled(false);
                    }
                }
            }

            //disable for radiobutton transaksi
            for(int i=0; i<sesi_list.size(); i++){
                if(sesi_list.get(i).equals("09.00 s/d 10.00")){
                    radioJam9.setEnabled(false);
                }
                else if(sesi_list.get(i).equals("10.00 s/d 11.00")){
                    radioJam10.setEnabled(false);
                }
                else if(sesi_list.get(i).equals("11.00 s/d 12.00")){
                    radioJam11.setEnabled(false);
                }
                else if(sesi_list.get(i).equals("12.00 s/d 13.00")){
                    radioJam12.setEnabled(false);
                }
                else if(sesi_list.get(i).equals("13.00 s/d 14.00")){
                    radioJam13.setEnabled(false);
                }
                else if(sesi_list.get(i).equals("14.00 s/d 15.00")){
                    radioJam14.setEnabled(false);
                }
                else if(sesi_list.get(i).equals("15.00 s/d 16.00")){
                    radioJam15.setEnabled(false);
                }
                else if(sesi_list.get(i).equals("16.00 s/d 17.00")){
                    radioJam16.setEnabled(false);
                }
            }

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
                            getGetShowText_jam = "16.00 s/d 17.00";
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

            //RadioButton
            radioJam9 = dialog.findViewById(R.id.rbtn_jam9);
            radioJam10 = dialog.findViewById(R.id.rbtn_jam10);
            radioJam11 = dialog.findViewById(R.id.rbtn_jam11);
            radioJam12 = dialog.findViewById(R.id.rbtn_jam12);
            radioJam13 = dialog.findViewById(R.id.rbtn_jam13);
            radioJam14 = dialog.findViewById(R.id.rbtn_jam14);
            radioJam15 = dialog.findViewById(R.id.rbtn_jam15);
            radioJam16 = dialog.findViewById(R.id.rbtn_jam16);
        }

        private void descTheComponent(View itemView) {
            //Cardview
            cv_konsultan = itemView.findViewById(R.id.cardview_konsultan);
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

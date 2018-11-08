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
import com.digitalcreative.warungskripsi.ModelData.Konsultan;
import com.digitalcreative.warungskripsi.R;

import java.util.ArrayList;
import java.util.List;

public class List_Riwayat_RecycleView extends RecyclerView.Adapter<List_Riwayat_RecycleView.ViewHolder> {
    List<String[]> list;
    Context context;

    public List_Riwayat_RecycleView(ArrayList<String[]> list, Context c) {
        this.list = list;
        this.context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new List_Riwayat_RecycleView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String[] riwayat = list.get(position);

        //set
        holder.tanggal_riwayat.setText(riwayat[0]);
        holder.nama_konsultan_riwayat.setText(riwayat[1]);
        holder.sesi_riwayat.setText(riwayat[2]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tanggal_riwayat, nama_konsultan_riwayat, sesi_riwayat;
        CardView cv_riwayat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Init
            descTheComponent(itemView);
        }

        private void descTheComponent(View itemView) {
            //Cardview
            cv_riwayat = itemView.findViewById(R.id.cardview_riwayat);
            tanggal_riwayat = itemView.findViewById(R.id.tanggal_riwayat);
            nama_konsultan_riwayat = itemView.findViewById(R.id.nama_konsultan_riwayat);
            sesi_riwayat = itemView.findViewById(R.id.sesi_riwayat);
        }
    }
}

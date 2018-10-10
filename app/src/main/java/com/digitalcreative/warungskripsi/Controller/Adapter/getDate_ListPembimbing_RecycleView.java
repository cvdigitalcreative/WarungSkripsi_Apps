package com.digitalcreative.warungskripsi.Controller.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalcreative.warungskripsi.ModelData.Pembimbing_Model;
import com.digitalcreative.warungskripsi.R;

import java.lang.reflect.Modifier;
import java.util.List;

import static android.view.View.inflate;

public class getDate_ListPembimbing_RecycleView extends RecyclerView.Adapter<getDate_ListPembimbing_RecycleView.ViewHolder>{
    List<Pembimbing_Model> list;

    @NonNull
    @Override
    public getDate_ListPembimbing_RecycleView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull getDate_ListPembimbing_RecycleView.ViewHolder viewHolder, int pos) {
        final Pembimbing_Model model = list.get(pos);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gambarPembimbing;
        TextView namaPembimbing, statusPembimbing;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gambarPembimbing = itemView.findViewById(R.id.image_cardview);
            namaPembimbing = itemView.findViewById(R.id.namapembimbing_cardview);
            statusPembimbing = itemView.findViewById(R.id.status_pembimbing_cardview);
        }
    }
}

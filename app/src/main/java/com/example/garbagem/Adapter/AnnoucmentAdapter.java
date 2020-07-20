package com.example.garbagem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.garbagem.R;
import com.example.garbagem.model.AnnnoucmentModel;

import java.util.List;

public class AnnoucmentAdapter extends RecyclerView.Adapter<AnnoucmentAdapter.ViewHolder> {

    List<AnnnoucmentModel> annnoucmentModelList;
    Context context;

    public AnnoucmentAdapter(List<AnnnoucmentModel> annnoucmentModelList, Context context) {
        this.annnoucmentModelList = annnoucmentModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public AnnoucmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View superView = LayoutInflater.from(context).inflate(R.layout.item_annoucment,parent,false);
        return new AnnoucmentAdapter.ViewHolder(superView);

    }

    @Override
    public void onBindViewHolder(@NonNull AnnoucmentAdapter.ViewHolder holder, int position) {
        if(position<annnoucmentModelList.size()){
            AnnnoucmentModel annnoucment = annnoucmentModelList.get(position);

            if (annnoucment!=null){
                holder.tvName.setText(annnoucment.getName());
                holder.tvDate.setText(annnoucment.getDate());
            }


        }

    }

    @Override
    public int getItemCount() {
        return annnoucmentModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDate;


        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDate = itemView.findViewById(R.id.tvDate);


        }
    }
}
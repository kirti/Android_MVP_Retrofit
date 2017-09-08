package com.kkmvp.kkmvp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kkmvp.kkmvp.Model.obj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KK00120638 on 9/7/2017.
 */

public class DataSource extends RecyclerView.Adapter<DataSource.ViewHolder> {
    private List<obj> objCountry;

    public DataSource(List<obj> objCountry) {
        this.objCountry = objCountry;
    }

    @Override
    public DataSource.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row,viewGroup,false);
        //return new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataSource.ViewHolder viewHolder, int position) {
            viewHolder.country_name.setText(objCountry.get(position).getName());
            viewHolder.getAlpha2_code.setText(objCountry.get(position).getAlpha2_code());
            viewHolder.getAlpha3_code.setText(objCountry.get(position).getAlpha3_code());

    }

    @Override
    public int getItemCount() {
        return objCountry.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView country_name,getAlpha2_code,getAlpha3_code;
        public ViewHolder(View itemView) {
            super(itemView);
            country_name=(TextView)itemView.findViewById(R.id.country_name);
             getAlpha2_code=(TextView)itemView.findViewById(R.id.getAlpha2_code);
            getAlpha3_code=(TextView)itemView.findViewById(R.id.getAlpha3_code);


        }
    }
}


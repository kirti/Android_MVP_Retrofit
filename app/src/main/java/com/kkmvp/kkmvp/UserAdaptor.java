package com.kkmvp.kkmvp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kkmvp.kkmvp.Model.UserResponse;

import java.util.List;

/**
 * Created by KK00120638 on 9/8/2017.
 * this is adaptor for recycler view for users
 */

public class UserAdaptor extends RecyclerView.Adapter<UserAdaptor.ViewHolder>{

    private List<UserResponse> listOfUserResponse;

    public UserAdaptor(List<UserResponse> listOfUserResponse) {
        this.listOfUserResponse = listOfUserResponse;
    }

    @Override
    public UserAdaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_row,viewGroup,false);
        return new UserAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdaptor.ViewHolder holder, int position) {
      // holder.UserId.setText(listOfUserResponse.get(position).getUserId());
        holder.UserBody.setText(listOfUserResponse.get(position).getBody());
        holder.UserTitle.setText(listOfUserResponse.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return listOfUserResponse.size();
    }

    @Override
    public void onBindViewHolder(UserAdaptor.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView UserId,UserTitle,UserBody;
        public ViewHolder(View itemView) {
            super(itemView);
            //UserId =(TextView)itemView.findViewById(R.id.UserId);
            UserTitle =(TextView)itemView.findViewById(R.id.UserTitle);
            UserBody =(TextView)itemView.findViewById(R.id.UserBody);
        }
    }

}

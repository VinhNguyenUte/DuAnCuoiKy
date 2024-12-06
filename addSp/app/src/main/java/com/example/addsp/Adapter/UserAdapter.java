package com.example.addsp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.addsp.Model.UserModel;
import com.example.addsp.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private ArrayList<UserModel> userList;
    private OnUserActionListener listener;

    public UserAdapter(Context context, ArrayList<UserModel> userList, OnUserActionListener listener) {
        this.context = context;
        this.userList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserModel user = userList.get(position);

        holder.userName.setText(user.getName());
        holder.userEmail.setText(user.getEmail());

        Glide.with(holder.itemView.getContext())
                .load(user.getAvatar())
                .into(holder.avatarImage);


        // Sự kiện chỉnh sửa
        holder.editButton.setOnClickListener(v -> listener.onEditClick(position));

        // Sự kiện xóa
        holder.deleteButton.setOnClickListener(v -> listener.onDeleteClick(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userEmail;
        ImageView avatarImage;
        Button editButton, deleteButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userEmail = itemView.findViewById(R.id.userEmail);
            avatarImage = itemView.findViewById(R.id.avatarImage);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    public interface OnUserActionListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }
}

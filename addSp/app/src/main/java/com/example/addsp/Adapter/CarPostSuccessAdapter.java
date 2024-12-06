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

import com.bumptech.glide.Glide;
import com.example.addsp.Model.Car;
import com.example.addsp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CarPostSuccessAdapter extends RecyclerView.Adapter<CarPostSuccessAdapter.CarPostViewHolder> {

    private Context context;
    private ArrayList<Car> carPostList;


    public CarPostSuccessAdapter(Context context, ArrayList<Car> carPostList) {
        this.context = context;
        this.carPostList = carPostList;

    }

    @NonNull
    @Override
    public CarPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId =R.layout.car_post_item_approved ;
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new CarPostViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull CarPostViewHolder holder, int position) {

        if (carPostList == null || carPostList.isEmpty() || position >= carPostList.size()) {
            return;
        }
        Car carPost = carPostList.get(position);

        holder.carBrandName.setText(carPost.getBrand() + " " + carPost.getName());
        holder.carDescription.setText(carPost.getDescription());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedAmount = decimalFormat.format(carPost.getPrice());

        holder.carPrice.setText(formattedAmount + " VND");

        holder.carStatus.setText("Success");
        holder.carCreatedAt.setText("Created At: " + carPost.getCreatedAt());
        Glide.with(holder.itemView.getContext())
                .load(carPost.getImages().get(0).getUrl())
                .into(holder.imageCarSuccess);
    }

    @Override
    public int getItemCount() {
        return carPostList.size();
    }

    public static class CarPostViewHolder extends RecyclerView.ViewHolder {
        TextView carBrandName, carDescription, carPrice, carStatus, carCreatedAt;
        Button btnApprove, btnReject;
        ImageView imageCarSuccess;
        public CarPostViewHolder(@NonNull View itemView) {
            super(itemView);
            carBrandName = itemView.findViewById(R.id.carBrandName);
            carDescription = itemView.findViewById(R.id.carDescription);
            carPrice = itemView.findViewById(R.id.carPrice);
            carStatus = itemView.findViewById(R.id.carStatus);
            carCreatedAt = itemView.findViewById(R.id.carCreatedAt);
            btnReject = itemView.findViewById(R.id.btnReject);
            btnApprove = itemView.findViewById(R.id.btnApprove);
            imageCarSuccess = itemView.findViewById(R.id.imageCarSuccess);
        }
    }


}

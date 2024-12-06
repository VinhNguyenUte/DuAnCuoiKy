package com.example.addsp.Adapter;

import android.content.Context;
import android.util.Log;
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

public class CarPostPendingAdapter extends RecyclerView.Adapter<CarPostPendingAdapter.CarPostViewHolder> {

    private Context context;
    private ArrayList<Car> carPostList;
    private OnCarPostActionListener listener;

    public CarPostPendingAdapter(Context context, ArrayList<Car> carPostList, OnCarPostActionListener listener) {
        this.context = context;
        this.carPostList = carPostList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId =R.layout.car_post_item ;
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new CarPostViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull CarPostViewHolder holder, int position) {
        if (position >= 0 && position < carPostList.size() && carPostList.get(position)!=null) {
            Car carPost = carPostList.get(position);

            holder.carBrandName.setText(carPost.getBrand() + " " + carPost.getName());
            holder.carDescription.setText(carPost.getDescription());
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedAmount = decimalFormat.format(carPost.getPrice());

            if (carPost.getImages() == null) {
                Log.e("Adapter", "Images list is null at position: " + position);
            } else if (carPost.getImages().isEmpty()) {
                Log.e("Adapter", "Images list is empty at position: " + position);
            }
            holder.carPrice.setText(formattedAmount + " VND");
            if (carPost.getImages() != null && !carPost.getImages().isEmpty() && carPost.getImages().get(0).getUrl() != null) {
                Glide.with(holder.itemView.getContext())
                        .load(carPost.getImages().get(0).getUrl())
                        .into(holder.imageCar);
            }

            holder.carStatus.setText("Pending");
            holder.carCreatedAt.setText("Created At: " + carPost.getCreatedAt());
            holder.btnApprove.setOnClickListener(v -> listener.onCarApproveClick(position,carPost));
            holder.btnReject.setOnClickListener(v -> listener.onCarRejectClick(position,carPost));
        }else{
            Log.e("Adapter", "Vị trí ngoài giới hạn: " + position);
        }

    }

    @Override
    public int getItemCount() {
        return carPostList != null ? carPostList.size() : 0;
    }

    public static class CarPostViewHolder extends RecyclerView.ViewHolder {
        TextView carBrandName, carDescription, carPrice, carStatus, carCreatedAt;
        Button btnApprove, btnReject;
        ImageView imageCar;
        public CarPostViewHolder(@NonNull View itemView) {
            super(itemView);
            carBrandName = itemView.findViewById(R.id.carBrandName);
            carDescription = itemView.findViewById(R.id.carDescription);
            carPrice = itemView.findViewById(R.id.carPrice);
            carStatus = itemView.findViewById(R.id.carStatus);
            carCreatedAt = itemView.findViewById(R.id.carCreatedAt);
            btnReject = itemView.findViewById(R.id.btnReject);
            btnApprove = itemView.findViewById(R.id.btnApprove);
            imageCar = itemView.findViewById(R.id.imageCar);
        }
    }

    public interface OnCarPostActionListener {
        void onCarApproveClick(int position,Car car);
        void onCarRejectClick(int position, Car car);
    }
}

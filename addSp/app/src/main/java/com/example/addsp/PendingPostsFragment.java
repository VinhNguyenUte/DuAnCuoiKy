package com.example.addsp;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.addsp.Adapter.CarPostPendingAdapter;
import com.example.addsp.ApiService.CarPostServiceApi;
import com.example.addsp.Model.Car;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingPostsFragment extends Fragment implements CarPostPendingAdapter.OnCarPostActionListener {

    private RecyclerView recyclerView;
    private CarPostPendingAdapter carPostAdapter;
    private ArrayList<Car> carPostList;
    String token;
    boolean isFetch = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_posts, container, false);
        token = "Bearer " + getTokenFromPrefs();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        carPostList = new ArrayList<>();
//        carPostList.add(new CarPostModel(1, 101, "Honda", "Civic", "Good condition, low mileage", "$15,000", "Pending", "2024-11-24"));

        carPostAdapter = new CarPostPendingAdapter(requireContext(), carPostList, this);
        recyclerView.setAdapter(carPostAdapter);
        CallApi(token);

        return view;
    }


    private void CallApi(String token){
        CarPostServiceApi.carPostServiceApi.getListCarPostPending(token).enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if(response.body()!=null&&response.isSuccessful()){
                    carPostList.clear();
                    for(Car car : response.body()){
                        carPostList.add(car);
                    }
                    isFetch = true;
                    carPostAdapter.notifyDataSetChanged();
                }
                else{
                    Log.e("API Error", "Response error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Log.e("API_FAILURE", t.getMessage());
            }
        });
    }

    private String getTokenFromPrefs() {
        return requireActivity().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
                .getString("token", "");
    }

    @Override
    public void onCarApproveClick(int position, Car car) {
        CarPostServiceApi.carPostServiceApi.handleCarPost(token,car.getCarId(),true).enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Call<Car> call, Response<Car> response) {
                if(response.isSuccessful() && response.body()!=null){
                    carPostList.remove(position);
                    carPostAdapter.notifyItemRemoved(position);
                    Toast.makeText(requireActivity(), "Thay đổi trạng thái thành công!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(requireActivity(), "Thay dổi trạng thái không thành công", Toast.LENGTH_SHORT).show();
                    Log.e("API Error", "Response error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Car> call, Throwable t) {
                Log.e("API_FAILURE", t.getMessage());
            }
        });
    }

    @Override
    public void onCarRejectClick(int position, Car car) {
        CarPostServiceApi.carPostServiceApi.handleCarPost(token,car.getCarId(),false).enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Call<Car> call, Response<Car> response) {
                if(response.isSuccessful() && response.body()!=null){
                    carPostList.remove(position);
                    carPostAdapter.notifyItemRemoved(position);
                    Toast.makeText(requireActivity(), "Thay đổi trạng thái thành công!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(requireActivity(), "Thay dổi trạng thái không thành công", Toast.LENGTH_SHORT).show();
                    Log.e("API Error", "Response error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Car> call, Throwable t) {
                Log.e("API_FAILURE", t.getMessage());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isFetch){
            CallApi(token);
        }
    }
}

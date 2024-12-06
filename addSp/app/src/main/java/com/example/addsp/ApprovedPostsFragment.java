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

import com.example.addsp.Adapter.CarPostPendingAdapter;
import com.example.addsp.Adapter.CarPostSuccessAdapter;
import com.example.addsp.ApiService.CarPostServiceApi;
import com.example.addsp.Model.Car;
import com.example.addsp.Model.CarPostModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovedPostsFragment extends Fragment  {

    private RecyclerView recyclerView;
    private CarPostSuccessAdapter carPostAdapter;
    private ArrayList<Car> carPostList;
    String token;
    boolean isFetch = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_approved_posts, container, false);
        token = "Bearer " + getTokenFromPrefs();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Dữ liệu mẫu cho bài đăng đã duyệt
        carPostList = new ArrayList<>();
//

        // Gắn adapter cho RecyclerView
        carPostAdapter = new CarPostSuccessAdapter(requireContext(), carPostList);
        recyclerView.setAdapter(carPostAdapter);
        CallAPi(token);
        return view;
    }

    private String getTokenFromPrefs() {
        return requireActivity().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
                .getString("token", "");
    }

    private void CallAPi(String token){
        CarPostServiceApi.carPostServiceApi.getListCarSuccess(token,null).enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if(response.isSuccessful() && response.body()!=null){
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

    @Override
    public void onResume() {
        super.onResume();
        if(isFetch){
            CallAPi(token);
        }
    }
}

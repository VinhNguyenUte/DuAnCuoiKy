package com.example.addsp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.addsp.Adapter.CarPostPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CarPostApproval_Fragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private CarPostPagerAdapter pagerAdapter;
    Button btn_logout_car;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout chứa TabLayout và ViewPager2
        return inflater.inflate(R.layout.fragment_car_post_approval_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        btn_logout_car = view.findViewById(R.id.btn_logout_car);
        btn_logout_car.setOnClickListener(v->{
            SharedPreferences sharedPreferences =requireContext().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("token");
            editor.apply();

            Intent intent = new Intent(requireActivity(), SignIn.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            requireActivity().finish();
        });
        // Tạo adapter cho ViewPager2
        pagerAdapter = new CarPostPagerAdapter(this);
        pagerAdapter.addFragment(new PendingPostsFragment(), "Chưa duyệt");
        pagerAdapter.addFragment(new ApprovedPostsFragment(), "Đã duyệt");

        viewPager.setAdapter(pagerAdapter);

        // Liên kết TabLayout với ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(pagerAdapter.getFragmentTitle(position));
        }).attach();
    }
}

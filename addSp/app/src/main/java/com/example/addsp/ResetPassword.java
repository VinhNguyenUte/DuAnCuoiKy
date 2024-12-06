package com.example.addsp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.addsp.ApiRequest.ResetPasswordRequest;
import com.example.addsp.ApiResponse.ResetPasswordResponse;
import com.example.addsp.ApiService.AuthServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView textView = findViewById(R.id.textView3);
        String tmpcolortext = "<font color=#000000>Please Enter Your </font>"
                + "<font color=#F2C620>UserName</font>"
                + "<font color=#000000>, we can send </font>"
                + "<font color=#F2C620>new Password</font>"
                + "<font color=#000000>, to your </font>"
                + "<font color=#F2C620>Phone number</font>";
        textView.setText(Html.fromHtml(tmpcolortext));


        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = findViewById(R.id.inputEmail);
                String inputEmail = email.getText().toString();
                if(inputEmail.isEmpty()) {
                    Toast.makeText(ResetPassword.this, "Please fill in the email field", Toast.LENGTH_SHORT).show();
                    return;
                }
                ResetPasswordRequest resetPassword = new ResetPasswordRequest(inputEmail);
                AuthServiceApi.authServiceApi.resetPassword(resetPassword).enqueue(new Callback<ResetPasswordResponse>() {
                    @Override
                    public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                        if (response.isSuccessful()) {

                            startActivity(new Intent(ResetPassword.this, ResetPassword_True.class));
                        } else {

                            Toast.makeText(ResetPassword.this, "Failed to send OTP. Please check your email.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                        Toast.makeText(ResetPassword.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
                        Log.e("ResetPaswordError", t.getMessage());
                    }
                });
            }
        });




    }


}
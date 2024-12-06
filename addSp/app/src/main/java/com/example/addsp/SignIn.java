package com.example.addsp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.addsp.ApiRequest.LoginRequest;
import com.example.addsp.ApiResponse.LoginResponse;
import com.example.addsp.ApiService.AuthServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CheckBox rememberMeCheckBox = findViewById(R.id.checkBox);
        EditText eTxtPass = findViewById(R.id.editTextText2);
        String tmp = eTxtPass.getText().toString().trim();
        EditText eTxtMail = findViewById(R.id.editTextText);
        String email = eTxtMail.getText().toString().trim();
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);

        if (token != null) {

            Intent intent = new Intent(SignIn.this, MainActivity.class);
            startActivity(intent);
            finish();
        }



        SharedPreferences references = getSharedPreferences("UserPrefs", MODE_PRIVATE);


        boolean isRemembered = references.getBoolean("isRemembered", false);
        if (isRemembered) {

            String savedEmail = references.getString("email", "");
            String savedPassword = references.getString("password", "");


            eTxtMail.setText(savedEmail);
            eTxtPass.setText(savedPassword);


            rememberMeCheckBox.setChecked(true);
        }


        Button btnLogin = findViewById(R.id.btnSignIn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp = eTxtPass.getText().toString().trim();
                String email = eTxtMail.getText().toString().trim();

                if (!tmp.isEmpty() && !email.isEmpty()) {
                    LoginRequest req = new LoginRequest(email, tmp);

                    AuthServiceApi.authServiceApi.login(req).enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful() && response.body() != null && response.body().getRole().equals("ROLE_ADMIN")) {
                                String token = response.body().getJwt();
                                Long userId = response.body().getUserId();
                                SharedPreferences sharedPreferences = getSharedPreferences("AuthPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("token", token);
                                editor.putLong("userId",userId);
                                editor.apply();

                                SharedPreferences references = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor edit = references.edit();
                                if (rememberMeCheckBox.isChecked()) {
                                    editor.putBoolean("isRemembered", true);
                                    editor.putString("email", email);
                                    editor.putString("password", tmp);
                                } else {
                                    // Xóa thông tin nếu checkbox không được chọn
                                    editor.putBoolean("isRemembered", false);
                                    editor.remove("email");
                                    editor.remove("password");
                                }
                                edit.apply();

                                Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(SignIn.this, MainActivity.class));
                            } else {
                                Toast.makeText(SignIn.this, "Login failed! " + response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(SignIn.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("MainActivity", "Login failed", t);
                        }
                    });
                } else {
                    Toast.makeText(SignIn.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        TextView tviewHOSPass = findViewById(R.id.textView7);
        tviewHOSPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edt = findViewById(R.id.editTextText2);
                TextView txtv = findViewById(R.id.textView7);
                int tmp = edt.getInputType();
                if ((tmp & InputType.TYPE_TEXT_VARIATION_PASSWORD) == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    txtv.setText("See");
                    Drawable vectorDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.n_svg_see_pass);
                    txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(vectorDrawable, null, null, null);
                    edt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edt.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    txtv.setText("Hide");
                    Drawable vectorDrawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.n_svg_hide_pass);
                    txtv.setCompoundDrawablesRelativeWithIntrinsicBounds(vectorDrawable, null, null, null);
                    edt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        TextView tviewRP = findViewById(R.id.textView5);
        tviewRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this, ResetPassword.class));
            }
        });
    }

}

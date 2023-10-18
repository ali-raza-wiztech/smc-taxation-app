package com.wiztech.taxation_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.wiztech.taxation_app.Entities.User;
import com.wiztech.taxation_app.databinding.ActivityLoginBinding;
import com.wiztech.taxation_app.databinding.ActivityMainBinding;
import com.wiztech.taxation_app.networking.APIClient;
import com.wiztech.taxation_app.networking.ApiEndpointInterface;
import com.wiztech.taxation_app.networking.responses.LoginResponse;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private ApiEndpointInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = APIClient.getClient().create(ApiEndpointInterface.class);
        //binding.etUsername.setText("masterwiztech");
        //binding.etPassword.setText("WizTech123321aa");
        binding.btnLogin.setOnClickListener(v -> {


            String username=binding.etUsername.getText().toString();
            String password=binding.etPassword.getText().toString();


            if (!username.isEmpty() && !password.isEmpty()){
                hitLogin(username,password);
            }else{
                Snackbar.make(v, "Fill Empty Field!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

    }

    private void hitLogin(String username, String password){
        HashMap<String,String> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);

        Call<LoginResponse> call=apiInterface.login(map);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().getUser()!=null){
                    if(response.body()!=null && response.body().getStatus().equalsIgnoreCase("success")){
                        User user=response.body().getUser();
                        user.setToken(response.body().getToken());

                        LoginSession.setUserData(getApplicationContext(),user);
                        LoginSession.setLoggedIn(getApplicationContext(),true);
                        finish();
                        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Snackbar.make(binding.relativeLayout, "Fill Empty Field!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }else{
                    Snackbar.make(binding.relativeLayout, ""+response.body().getErrorMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println("");
            }
        });

    }
}
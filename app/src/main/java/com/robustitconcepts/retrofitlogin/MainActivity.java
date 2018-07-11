package com.robustitconcepts.retrofitlogin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.robustitconcepts.retrofitlogin.interfaces.ApiInterface;
import com.robustitconcepts.retrofitlogin.model.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
    }

    public void onLoginClicked(View view) {

        getUserData();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<LoginModel.response> call = apiInterface.performLogin(username,password);

        call.enqueue(new Callback<LoginModel.response>() {
            @Override
            public void onResponse( Call<LoginModel.response> call, Response<LoginModel.response> res) {
                if(res.isSuccessful()) {
                    LoginModel.response response = res.body();
                    Toast.makeText(MainActivity.this,response.getStatus()+response.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel.response> call, Throwable t) {

            }
        });
    }

    private void getUserData() {
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
    }
}

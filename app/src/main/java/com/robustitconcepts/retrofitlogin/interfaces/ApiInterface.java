package com.robustitconcepts.retrofitlogin.interfaces;

import com.robustitconcepts.retrofitlogin.model.LoginModel;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("login")
    Call<LoginModel.response> performLogin(@Query("email") String email,@Query("password") String password);
}

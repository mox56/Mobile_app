package com.example.myapplication;


import android.database.Observable;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("student/")
    Call<List<StudentList>> loadStudentList(@Query("q")String status);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8000/api/students/?format=api")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    @GET("users")
    Observable<List<User>> getUsers();

}


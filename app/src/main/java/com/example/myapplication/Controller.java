package com.example.myapplication;

import android.os.Build;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<StudentList>> {

    static final String BASE_URL = "https://git.eclipse.org/r/";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<List<StudentList>> call = apiInterface.loadStudentList("status:open");
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<StudentList>> call, Response<List<StudentList>> response) {
        if(response.isSuccessful()) {
            List<StudentList> changesList = response.body();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                changesList.forEach(studentList -> System.out.println(studentList.student));
            }
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<StudentList>> call, Throwable t) {
        t.printStackTrace();
    }
}


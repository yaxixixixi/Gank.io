package com.example.yaxi.gankio.http;

import android.util.Log;

import com.example.yaxi.gankio.Data;
import com.example.yaxi.gankio.utils.Const;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by icursoft on 2017/4/11.
 */

public class Request {

    private static final String TAG = Request.class.getSimpleName();
    private static final ApiService service;
    private static final Retrofit retrofit;

    static {
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }


    public static Call<Data> getDataWithCategory(String category,int count,int page){
        return service.getData(category, count, page);
    }

}

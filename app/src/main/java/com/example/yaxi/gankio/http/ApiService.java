package com.example.yaxi.gankio.http;

import com.example.yaxi.gankio.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by icursoft on 2017/4/11.
 */

public interface ApiService {

    @GET("data/{category}/{count}/{page}")
    Call<Data> getData(@Path("category") String category,@Path("count") int count,@Path("page") int page);
}

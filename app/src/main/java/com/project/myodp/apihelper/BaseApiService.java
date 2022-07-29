package com.project.myodp.apihelper;

import com.project.myodp.TableResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BaseApiService {
    // Fungsi ini untuk memanggil API http://192.168.43.247/android/_login.php
    @FormUrlEncoded
    @POST("login.php")
    public Call<ResponseBody> loginRequest(@Field("userid") String userid,
                                           @Field("password") String password);
    @GET("dataTables.php")
    public Call<List<TableResponse>> getDataTables(@Query("page") long page, @Query("perpage") int perpage, @Query("filter") String filter, @Query("order") String order);

}

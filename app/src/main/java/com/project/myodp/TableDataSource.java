package com.project.myodp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import androidx.recyclerview.widget.RecyclerView;

import com.project.myodp.adapter.Tables_GetSet;
import com.project.myodp.apihelper.BaseApiService;
import com.project.myodp.apihelper.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.supercharge.shimmerlayout.ShimmerLayout;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableDataSource extends PageKeyedDataSource<Long, TableResponse> {
    public static int PAGE_SIZE = 6;
    public static long FIRST_PAGE = 1;
    public static String ORDER = "asc";
    BaseApiService mApiService = UtilsApi.getAPIService();
    Application application;
    String filter;
    public TableDataSource(Application application, String filter){
        this.application = application;
        this.filter = filter;
    }
//    List<Tables_GetSet> tables_list;
//    TablesAPI tablesAPI;
//
//    public void setTablesAPI(TablesAPI tablesAPI) {
//        this.tablesAPI = tablesAPI;
//    }

    @Override
    public void loadInitial(@NonNull PageKeyedDataSource.LoadInitialParams<Long> params, @NonNull PageKeyedDataSource.LoadInitialCallback<Long, TableResponse> callback) {
        mApiService.getDataTables(FIRST_PAGE,PAGE_SIZE,filter,ORDER).enqueue(new Callback<List<TableResponse>>() {
            @Override
            public void onResponse(Call<List<TableResponse>> call, Response<List<TableResponse>> response) {
                if (response.isSuccessful()){
                    try {
                        List<TableResponse> listTable = new ArrayList<>();
                        listTable = response.body();
//                        Toast.makeText(application, "Test Filter Data : "+ filter, Toast.LENGTH_SHORT).show();

                            callback.onResult(listTable,null,FIRST_PAGE+1);
//                        Toast.makeText(application, "loadInitial Sukses", Toast.LENGTH_SHORT).show();

//                                Log.e("LIST", "LIST_TEST > " + getDatasensor_list().get(1).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
//                                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(application, "Gagal Dimuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TableResponse>> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }



    @Override
    public void loadBefore(@NonNull PageKeyedDataSource.LoadParams<Long> params, @NonNull PageKeyedDataSource.LoadCallback<Long, TableResponse> callback) {
        mApiService.getDataTables(params.key, PAGE_SIZE,filter,ORDER).enqueue(new Callback<List<TableResponse>>() {
            @Override
            public void onResponse(Call<List<TableResponse>> call, Response<List<TableResponse>> response) {
                if (response.isSuccessful()){
                    try {
                        List<TableResponse> listTable = new ArrayList<>();
                        listTable = response.body();
                            long key;
                            if (params.key > 1){
                                key = params.key - 1;
                            }else {
                                key = 0;
                            }
                            callback.onResult(listTable,key);
//                        Toast.makeText(application, "loadBefore Sukses", Toast.LENGTH_SHORT).show();
//                                Log.e("LIST", "LIST_TEST > " + getDatasensor_list().get(1).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
//                                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(application, "Gagal Dimuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TableResponse>> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }

    @Override
    public void loadAfter(@NonNull PageKeyedDataSource.LoadParams<Long> params, @NonNull PageKeyedDataSource.LoadCallback<Long, TableResponse> callback) {
        mApiService.getDataTables(params.key, PAGE_SIZE,filter,ORDER).enqueue(new Callback<List<TableResponse>>() {
            @Override
            public void onResponse(Call<List<TableResponse>> call, Response<List<TableResponse>> response) {
                if (response.isSuccessful()){
                    try {
                        List<TableResponse> listTable = new ArrayList<>();
                        listTable = response.body();
                            callback.onResult((List<TableResponse>) listTable,params.key + 1);
//                        Toast.makeText(application, "loadAfter Sukses", Toast.LENGTH_SHORT).show();
//                                Log.e("LIST", "LIST_TEST > " + getDatasensor_list().get(1).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
//                                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(application, "Gagal Dimuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TableResponse>> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
    }
}

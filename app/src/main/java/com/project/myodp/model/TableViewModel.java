package com.project.myodp.model;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.project.myodp.TableDataSource;
import com.project.myodp.TableDataSourceFactory;
import com.project.myodp.TableResponse;
import com.project.myodp.adapter.Tables_GetSet;

import java.lang.reflect.Field;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TableViewModel extends AndroidViewModel {
    public LiveData<PagedList<TableResponse>> tablePagedList;
    public LiveData<TableDataSource> tableDataSourceLiveData;
    public TableDataSourceFactory itemDataSourceFactory;
    private Executor executor;
    SharedPreferences pref;

    public TableViewModel(Application application){
        super(application);
        init();
    }

    private void init() {
        pref  = getApplication().getSharedPreferences("MyPref",0);
        String filter = pref.getString("filter","");
        itemDataSourceFactory = new TableDataSourceFactory(getApplication(), filter);
        tableDataSourceLiveData = itemDataSourceFactory.getMutableLiveData();
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(TableDataSource.PAGE_SIZE)
                .build();

        executor = Executors.newFixedThreadPool(5);
        tablePagedList = (new LivePagedListBuilder<Long, TableResponse>(itemDataSourceFactory, config)).setFetchExecutor(executor).build();
    }

    public LiveData<PagedList<TableResponse>> getTablePagedList(){
        return tablePagedList;
    }

    public void refresh(){
        itemDataSourceFactory.getMutableLiveData().getValue().invalidate();
    }

    public void setOrderDesc(){
        itemDataSourceFactory.setOrderTableDataSource("desc");
        refresh();
    }

    public void setOrderAsc(){
        itemDataSourceFactory.setOrderTableDataSource("asc");
        refresh();
    }

}

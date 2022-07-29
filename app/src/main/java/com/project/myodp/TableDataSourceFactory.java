package com.project.myodp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.project.myodp.adapter.Tables_GetSet;

public class TableDataSourceFactory extends DataSource.Factory<Long, TableResponse> {

    public MutableLiveData<TableDataSource> tableDataSourceMutableLiveData = new MutableLiveData<>();
    TableDataSource tableDataSource;
    Application application;
    String filter;

    public TableDataSourceFactory (Application application,String filter){
        this.application = application;
        this.filter = filter;
    }

    @NonNull
    @Override
    public DataSource<Long, TableResponse> create() {
        tableDataSource = new TableDataSource(application,filter);
        tableDataSourceMutableLiveData.postValue(tableDataSource);
        return tableDataSource;
    }

    public MutableLiveData<TableDataSource> getMutableLiveData(){
        return tableDataSourceMutableLiveData;
    }

    public void setOrderTableDataSource(String order) {
        tableDataSource.ORDER = order;
    }
}

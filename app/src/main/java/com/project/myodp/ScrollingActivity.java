package com.project.myodp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.project.myodp.adapter.Tables_Adapter;
import com.project.myodp.adapter.Tables_GetSet;
import com.project.myodp.apihelper.BaseApiService;
import com.project.myodp.apihelper.UtilsApi;
import com.project.myodp.databinding.ActivityScrollingBinding;
import com.project.myodp.model.TableViewModel;

import io.supercharge.shimmerlayout.ShimmerLayout;

public class ScrollingActivity extends AppCompatActivity {

    private ActivityScrollingBinding binding;
    Context context;
    ShimmerLayout shimmerLayout;
    RecyclerView recyclerView;
    BaseApiService mApiService;
    Tables_Adapter tables_adapter;
    TableViewModel itemViewModel;
    PagedList<TableResponse> pagedListTable;
//    SharedPreferences pref  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        pref  = getSharedPreferences("MyPref",0);
//        String FILTER = pref.getString("filter","");
//        Toast.makeText(this, FILTER, Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shimmerLayout = findViewById(R.id.shimmerLayout);
//        tables_adapter = new Tables_Adapter(this);

//        recyclerView.setAdapter(tables_adapter);

        getData();
//        tables_adapter.notifyDataSetChanged();

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());


        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shimmerLayout.startShimmerAnimation();
                shimmerLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                itemViewModel.refresh();
                Snackbar.make(view, "Refresh Table !!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.filter_asc) {
            shimmerLayout.startShimmerAnimation();
            shimmerLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            itemViewModel.setOrderAsc();
            return true;
        }
        if (id == R.id.filter_desc){
            shimmerLayout.startShimmerAnimation();
            shimmerLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            itemViewModel.setOrderDesc();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void getData(){

        shimmerLayout.startShimmerAnimation();
        shimmerLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        tables_adapter = new Tables_Adapter(this);
        recyclerView.setAdapter(tables_adapter);

        itemViewModel = new ViewModelProvider(this).get(TableViewModel.class);
        itemViewModel.getTablePagedList().observe(this, new Observer<PagedList<TableResponse>>() {
            @Override
            public void onChanged(PagedList<TableResponse> tableResponses) {
                pagedListTable = tableResponses;
                tables_adapter.submitList(pagedListTable);

                try {
                    Thread.sleep(500);
                    shimmerLayout.stopShimmerAnimation();
                    shimmerLayout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        tables_adapter.notifyDataSetChanged();

    }

//    public LiveData<PagedList<Tables_GetSet>> getTabelPagedList(){
//        return
//    }
}
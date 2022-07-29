package com.project.myodp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.project.myodp.R;
import com.project.myodp.TableResponse;

import java.util.List;

public class Tables_Adapter extends PagedListAdapter<TableResponse,Tables_Adapter.TablesViewHolder> {
    Context context;
//List<Tables_GetSet> tables_list;

public Tables_Adapter(Context context){
    super(CALLBACK);
    this.context = context;
}


    @NonNull
    @Override
    public TablesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.table_design,null);
        return new TablesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TablesViewHolder holder, int position) {
        TableResponse tableResponse = getItem(position);

        holder.txtNo.setText(tableResponse.getNo().toString());
        holder.txtOdpName.setText(tableResponse.getOdpName());
        holder.txtLatitude.setText(tableResponse.getLatitude());
        holder.txtLongitude.setText(tableResponse.getLongitude());
        String latLng = tableResponse.getLatitude()+","+tableResponse.getLongitude();
        holder.btnLink.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/?api=1&destination="+latLng+"&travelmode=driving"));
            context.startActivity(intent);
        });
    }

    @Nullable
    @Override
    protected TableResponse getItem(int position) {
        return super.getItem(position);
    }

    public static class TablesViewHolder extends RecyclerView.ViewHolder {
        TextView txtNo;
        TextView txtOdpName;
        TextView txtLatitude;
        TextView txtLongitude;
        Button btnLink;


        public TablesViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtNo = itemView.findViewById(R.id.txtNo);
            this.txtOdpName = itemView.findViewById(R.id.txtOdpName);
            this.txtLatitude = itemView.findViewById(R.id.txtLatitude);
            this.txtLongitude = itemView.findViewById(R.id.txtLongitude);
            this.btnLink = itemView.findViewById(R.id.btnLink);
        }
    }

    private static final DiffUtil.ItemCallback<TableResponse> CALLBACK = new DiffUtil.ItemCallback<TableResponse>() {
        @Override
        public boolean areItemsTheSame(@NonNull TableResponse oldItem, @NonNull TableResponse newItem) {
            return oldItem.getNo() == newItem.getNo();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull TableResponse oldItem, @NonNull TableResponse newItem) {
            return oldItem.equals(newItem);
        }
    };
}

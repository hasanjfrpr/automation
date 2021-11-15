package com.dayrayaneh.automation.adapter.pishkhan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishKhanModel.PishKhanModel;
import com.dayrayaneh.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class PishKhanAdapter extends RecyclerView.Adapter<PishKhanAdapter.PishKhanViewHolder> {

    private Context context;
    private List<PishKhanModel> pishKhanModelList = new ArrayList<>();
    public PishKhanItemEvent event;
    private int positions;

    public PishKhanAdapter(Context context, List<PishKhanModel> pishKhanModelList) {
        this.context = context;
        this.pishKhanModelList = pishKhanModelList;
    }

    @NonNull
    @Override
    public PishKhanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan,parent,false);
        return new PishKhanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PishKhanViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.icon.setImageResource(pishKhanModelList.get(position).getIcon());
        holder.title.setText(pishKhanModelList.get(position).getTitle());


        holder.itemView.setOnClickListener(v->{
            Utils.setAnimationClick(v,context);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    event.onclickItemPishKhan(position);
                }
            },400);

        });

    }

    @Override
    public int getItemCount() {
        return pishKhanModelList.size();
    }

    public class PishKhanViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView title;


        public PishKhanViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.TV_item_mainList);
            icon = itemView.findViewById(R.id.IV_icon_mainList);
        }
    }

    public interface PishKhanItemEvent{
        void onclickItemPishKhan(int position);
    }
}

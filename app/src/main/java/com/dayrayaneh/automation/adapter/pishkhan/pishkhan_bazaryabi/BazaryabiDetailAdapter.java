package com.dayrayaneh.automation.adapter.pishkhan.pishkhan_bazaryabi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.detail.DataItem;

import java.util.ArrayList;
import java.util.List;

public class BazaryabiDetailAdapter extends RecyclerView.Adapter<BazaryabiDetailAdapter.BazaryabiDetailViewHolder> {

    private Context context;
    private List<DataItem> bazaryabiDetailModelList = new ArrayList<>();
    private boolean isShow = false;

    public BazaryabiDetailAdapter(Context context, List<DataItem> bazaryabiDetailModelList) {
        this.context = context;
        this.bazaryabiDetailModelList = bazaryabiDetailModelList;
    }

    @NonNull
    @Override
    public BazaryabiDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bazaryabi_detail_list,parent,false);
        return new BazaryabiDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BazaryabiDetailViewHolder holder, int position) {

        holder.productType.setText(bazaryabiDetailModelList.get(position).getNueMahsol());
        holder.clientName.setText(bazaryabiDetailModelList.get(position).getMoshtari());
        holder.description.setText(bazaryabiDetailModelList.get(position).getDescription());
        holder.status.setText(bazaryabiDetailModelList.get(position).getVaziat());
        holder.phoneNumber.setText(bazaryabiDetailModelList.get(position).getTelNumber());
        holder.serviceType.setText(bazaryabiDetailModelList.get(position).getKhadamatType());





            holder.showMore.setVisibility(View.VISIBLE);
            holder.showMore.setOnClickListener(v -> {
                if (!isShow) {
                    holder.description.setVisibility(View.VISIBLE);
                    holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                    isShow = true;
                } else {
                    holder.description.setVisibility(View.GONE);
                    holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
                    isShow = false;
                }

            });


    }

    @Override
    public int getItemCount() {
        return bazaryabiDetailModelList.size();
    }

    public class BazaryabiDetailViewHolder extends RecyclerView.ViewHolder{

        TextView serviceType , clientName , phoneNumber , status , description , productType;
        ImageView showMore;

        public BazaryabiDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            serviceType = itemView.findViewById(R.id.TV_item_bazaryabi_detail_serviceType);
            clientName = itemView.findViewById(R.id.TV_item_bazaryabi_detail_clientName);
            phoneNumber = itemView.findViewById(R.id.TV_item_bazaryabi_detail_phoneNumber);
            status = itemView.findViewById(R.id.TV_item_bazaryabi_detail_status);
            description = itemView.findViewById(R.id.TV_item_bazaryabi_detail_description);
            productType = itemView.findViewById(R.id.TV_item_bazaryabi_detail_productType);
            showMore = itemView.findViewById(R.id.IV_item_bazaryabi_detail_showMore);

        }
    }

}

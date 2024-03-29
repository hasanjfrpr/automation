package com.dayrayaneh.automation.adapter.pishkhan.pishkhan_bazaryabi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.detail.DataItem;

import java.util.ArrayList;
import java.util.List;

public class BazaryabiDetailAdapter extends RecyclerView.Adapter<BazaryabiDetailAdapter.BazaryabiDetailViewHolder> {

    private Context context;
    private List<DataItem> bazaryabiDetailModelList = new ArrayList<>();
    public EventClickPygiri eventClickPygiri;

    public BazaryabiDetailAdapter(Context context, List<DataItem> bazaryabiDetailModelList) {
        this.context = context;
        this.bazaryabiDetailModelList = bazaryabiDetailModelList;
    }
    public BazaryabiDetailAdapter() {

    }

    @NonNull
    @Override
    public BazaryabiDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bazaryabi_detail_list,parent,false);
        return new BazaryabiDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BazaryabiDetailViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final boolean[] isOpen = new boolean[bazaryabiDetailModelList.size()];

        for (int i = 0; i < bazaryabiDetailModelList.size(); i++) {
            isOpen[i] = true;

        }



        holder.productType.setText(bazaryabiDetailModelList.get(position).getNueMahsol());
        holder.clientName.setText(bazaryabiDetailModelList.get(position).getMoshtari());

        holder.status.setText(bazaryabiDetailModelList.get(position).getVaziat());
        holder.phoneNumber.setText(bazaryabiDetailModelList.get(position).getTelNumber());
        holder.serviceType.setText(bazaryabiDetailModelList.get(position).getKhadamatType());
        holder.paygiri.setText(String.valueOf(bazaryabiDetailModelList.get(position).getDetailsCount()));
        holder.insertDate.setText(bazaryabiDetailModelList.get(position).getDate());


        holder.itemPaygiriClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventClickPygiri.onClick(bazaryabiDetailModelList.get(position).getProformaCode());
            }
        });

        if(bazaryabiDetailModelList.get(position).isToday()){
            holder.linear_back_date.setBackgroundColor(context.getResources().getColor(R.color.greenLighter));
        }else {
            holder.linear_back_date.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        if (bazaryabiDetailModelList.get(position).isGolden()){
            holder.goldenStar.setVisibility(View.VISIBLE);
        }else {
            holder.goldenStar.setVisibility(View.INVISIBLE);
        }
        if (bazaryabiDetailModelList.get(position).isNew_()){
            holder.cardView_new.setVisibility(View.VISIBLE);
        }else {
            holder.cardView_new.setVisibility(View.INVISIBLE);
        }



        holder.description.setVisibility(View.VISIBLE);
        holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
        if (bazaryabiDetailModelList.get(position).getDescription() == null){
            holder.description.setText(" ندارد ");
            holder.description.setTextColor(context.getResources().getColor(R.color.redlight));
        }else{
            holder.description.setText(bazaryabiDetailModelList.get(position).getDescription());
        }


        if (bazaryabiDetailModelList.get(position).getVaziat() == null){
            holder.status.setText("این آیتم پر نشده است ");
            holder.status.setTextColor(context.getResources().getColor(R.color.redlight));

        }



            holder.showMore.setVisibility(View.VISIBLE);
            holder.showMore.setOnClickListener(v -> {
                if (!isOpen[position]) {
                    holder.description.setVisibility(View.VISIBLE);
                    holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                   if (bazaryabiDetailModelList.get(position).getDescription() == null){
                       holder.description.setText(" ندارد ");
                       holder.description.setTextColor(context.getResources().getColor(R.color.redlight));
                   }else{
                       holder.description.setText(bazaryabiDetailModelList.get(position).getDescription());
                   }
                    isOpen[position] = true;
                } else {
                    holder.description.setVisibility(View.GONE);
                    holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
                    isOpen[position] = false;
                }

            });


    }

    @Override
    public int getItemCount() {
        return bazaryabiDetailModelList.size();
    }

    public class BazaryabiDetailViewHolder extends RecyclerView.ViewHolder{

        TextView serviceType , clientName , phoneNumber , status , description , productType,paygiri , new_ , insertDate;
        ImageView showMore, goldenStar;
        CardView itemPaygiriClick,cardView_new;
        LinearLayout linear_back_date;

        public BazaryabiDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            serviceType = itemView.findViewById(R.id.TV_item_bazaryabi_detail_serviceType);
            clientName = itemView.findViewById(R.id.TV_item_bazaryabi_detail_clientName);
            phoneNumber = itemView.findViewById(R.id.TV_item_bazaryabi_detail_phoneNumber);
            status = itemView.findViewById(R.id.TV_item_bazaryabi_detail_status);
            description = itemView.findViewById(R.id.TV_item_bazaryabi_detail_description);
            productType = itemView.findViewById(R.id.TV_item_bazaryabi_detail_productType);
            showMore = itemView.findViewById(R.id.IV_item_bazaryabi_detail_showMore);
            paygiri = itemView.findViewById(R.id.TV_item_bazaryabi_detail_paygiri);
            itemPaygiriClick = itemView.findViewById(R.id.linear_paygiri_item);
            cardView_new = itemView.findViewById(R.id.cardView_new);
            linear_back_date = itemView.findViewById(R.id.linear_back_date);
            new_ = itemView.findViewById(R.id.TV_item_bazaryabi_detail_new);
            insertDate = itemView.findViewById(R.id.TV_item_bazaryabi_detail_insertDate);
            goldenStar = itemView.findViewById(R.id.IV_bazaryabi_detail_goldenStar);


        }
    }

    public interface EventClickPygiri{
        void onClick(int id);
    }

}

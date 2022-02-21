package com.dayrayaneh.automation.adapter.pishkhan.ticket.details;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.tickets.detilas.DataItem;

import java.util.ArrayList;
import java.util.List;

public class TicketDetailsAdapter extends RecyclerView.Adapter<TicketDetailsAdapter.TicketDetailsViewHolder> {

    private Context context;
    private List<DataItem> dataItemList = new ArrayList<>();

    public TicketDetailsAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public TicketDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TicketDetailsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ticket_details,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TicketDetailsViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.lightYellow));
        } else {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }
        
          holder.user.setText(dataItemList.get(position).getUserName());
        holder.time.setText(dataItemList.get(position).getDateShamsi());
        holder.status.setText(dataItemList.get(position).getStatus());
        holder.description.setText(dataItemList.get(position).getDescription());

         boolean[] isShow = new boolean[dataItemList.size()];
        for (int i = 0; i < dataItemList.size(); i++) {
            isShow[i] = true;
        }
        holder.showMore.setOnClickListener(v->{
            if (isShow[position]){
                holder.frameLayout.setVisibility(View.GONE);
                holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
                isShow[position]=false;
            }else {
                holder.frameLayout.setVisibility(View.VISIBLE);
                holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                isShow[position]=true;
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    class TicketDetailsViewHolder extends RecyclerView.ViewHolder{

        TextView user , time , status  , description;
        ImageView showMore;
        FrameLayout frameLayout;
        LinearLayout linearLayout;
        public TicketDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.TV_item_pishkhan_ticket_details_user);
            time = itemView.findViewById(R.id.TV_item_pishkhan_ticket_details_time);
            status = itemView.findViewById(R.id.TV_item_pishkhan_ticket_details_status);
        description = itemView.findViewById(R.id.TV_item_pishkhan_ticket_details_description);
        frameLayout = itemView.findViewById(R.id.frame_ticketDetail_more);
        showMore = itemView.findViewById(R.id.IV_item_pishkhan_ticket_detail_showMore);
        linearLayout = itemView.findViewById(R.id.lin_item_ticket_detail);
        }
    }
}

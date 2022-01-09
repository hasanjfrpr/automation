package com.dayrayaneh.automation.adapter.pishkhan.ticket;

import android.content.Context;
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
import com.dayrayaneh.automation.model.pishkhan.tickets.DataItem;
import com.dayrayaneh.automation.model.pishkhan.tickets.TicketModel;

import java.util.ArrayList;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder>{

    private Context context;
    private List<DataItem> modelList = new ArrayList<>();

    public TicketItem ticketItem;


    public TicketAdapter(Context context, List<DataItem> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TicketViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ticket_main,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.lightYellow));
        } else {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }

        holder.registrar.setText(modelList.get(position).getFirstUser());
        holder.currentUser.setText(modelList.get(position).getLastUser());
        holder.status.setText(modelList.get(position).getStatus());
        holder.ticketType.setText(modelList.get(position).getType());
        holder.date.setText(modelList.get(position).getShamsi());
        holder.importance.setText(modelList.get(position).getImportance());
        holder.title.setText(modelList.get(position).getTitle());
        holder.description.setText(modelList.get(position).getDescription());

         boolean[] isShow = new boolean[modelList.size()];
        for (int i = 0; i < modelList.size(); i++) {
            isShow[i] = false;
        }
        holder.showMore.setOnClickListener(v->{
            if (isShow[position]){
                holder.frame_showMore.setVisibility(View.GONE);
                holder.showMore_Image.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
                isShow[position]=false;
            }else {
                holder.frame_showMore.setVisibility(View.VISIBLE);
                holder.showMore_Image.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                isShow[position]=true;
            }
        });

        holder.itemView.setOnClickListener(view -> {
            ticketItem.ticketItemClick(modelList.get(position).getId());
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder{
        TextView registrar , currentUser , status , ticketType , date , importance,title,description;
        FrameLayout frame_showMore , showMore;
        ImageView showMore_Image;
        LinearLayout linearLayout;
        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);

            registrar = itemView.findViewById(R.id.TV_registrar_ticket);
            currentUser = itemView.findViewById(R.id.TV_currentuser_ticket);
            status = itemView.findViewById(R.id.TV_status_ticket);
            ticketType = itemView.findViewById(R.id.TV_ticketType_ticket);
            date = itemView.findViewById(R.id.TV_date_ticket);
            importance = itemView.findViewById(R.id.TV_importance_ticket);
            title = itemView.findViewById(R.id.TV_title_ticket);
            description = itemView.findViewById(R.id.TV_item_ticket_description);
            showMore = itemView.findViewById(R.id.IV_item_ticket_description_showMore);
            showMore_Image = itemView.findViewById(R.id.IV_item_ticket_description_showMoreImage);
            frame_showMore = itemView.findViewById(R.id.frame_more_ticket_description);
            linearLayout = itemView.findViewById(R.id.ticket_item_root);
        }
    }

    public interface TicketItem{
        void ticketItemClick(int id);
    }
}

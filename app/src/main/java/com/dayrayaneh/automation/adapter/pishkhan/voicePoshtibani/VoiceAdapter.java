package com.dayrayaneh.automation.adapter.pishkhan.voicePoshtibani;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.voicePoshtibani.DataItem;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class VoiceAdapter extends RecyclerView.Adapter<VoiceAdapter.VoiceViewHolder> {

    private Context context;
    private List<DataItem> dataItemList = new ArrayList<>();
    public Events events;

    public VoiceAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public VoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_voice,parent,false);
        return new VoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoiceViewHolder holder, int position) {

        holder.shomareTamas.setText(dataItemList.get(position).getPhoneNum());
        holder.time.setText(dataItemList.get(position).getDateTimeShamsi());
        holder.serial.setText(dataItemList.get(position).getSerial());

        holder.play.setOnClickListener(v -> {
            events.itemEvents(dataItemList.get(position).getUniqid());
        });
    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class VoiceViewHolder extends RecyclerView.ViewHolder{

        TextView serial , time , shomareTamas;
        ImageView play;


        public VoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serial = itemView.findViewById(R.id.TV_item_pishkhan_voice_serial);
            time = itemView.findViewById(R.id.TV_item_pishkhan_voice_time);
            shomareTamas = itemView.findViewById(R.id.TV_item_pishkhan_voice_shomareTamas);
            play = itemView.findViewById(R.id.TV_item_pishkhan_voice_play);
        }
    }

    public interface Events{
        void itemEvents(String uniqueId);
    }
}

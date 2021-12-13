package com.dayrayaneh.automation.adapter.pishkhan.khadamatPoshtibani.detail;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.detail.DataItem;

import java.util.ArrayList;
import java.util.List;

public class KhadamatPoshtibaniDetailAdapter extends RecyclerView.Adapter<KhadamatPoshtibaniDetailAdapter.KhadamatPoshtianiDetailViewHolder> {


    private Context context;
    private List<DataItem>  dataItemList = new ArrayList<>();
    private boolean isOpen = true;
    public Events events;

    public KhadamatPoshtibaniDetailAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public KhadamatPoshtianiDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_khadamat_pohstibani_detail , parent, false);
        return new KhadamatPoshtianiDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhadamatPoshtianiDetailViewHolder holder, int position) {

        holder.nameMoshtari.setText(dataItemList.get(position).getMoshtariName());
        holder.nameTamasGirande.setText(dataItemList.get(position).getSpeak());
        holder.dalilTamasMoshtari.setText(dataItemList.get(position).getRquest());
        holder.emtiaz.setText(dataItemList.get(position).getPoint());
        holder.description.setText(dataItemList.get(position).getTozihBeMoshtari());
        holder.saat.setText(dataItemList.get(position).getTimeSupport());
        holder.serial.setText(dataItemList.get(position).getSerial());
        holder.nameMahsol.setText(dataItemList.get(position).getKindName());

        holder.frameLayout.setVisibility(View.VISIBLE);
        holder.showDes.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));

        holder.showDes.setOnClickListener(v -> {
            if (isOpen){
                holder.frameLayout.setVisibility(View.GONE);
                holder.showDes.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
                isOpen = false;


            }else {
                holder.frameLayout.setVisibility(View.VISIBLE);
                holder.showDes.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                isOpen = true;
            }
        });


        holder.playVoice.setOnClickListener(v -> {
            events.onclickVoicePlay(dataItemList.get(position).getFldMokalemeID());
        });





    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class KhadamatPoshtianiDetailViewHolder extends RecyclerView.ViewHolder{

        TextView nameMoshtari , serial , emtiaz , description , nameTamasGirande , dalilTamasMoshtari , saat , nameMahsol;
        ImageView showDes , playVoice;
        FrameLayout frameLayout;


        public KhadamatPoshtianiDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            nameMoshtari = itemView.findViewById(R.id.TV_item_pishkhan_khadamatPoshtibani_nameMoshtari);
            serial = itemView.findViewById(R.id.TV_item_pishkhan_khadamatPoshtibani_serial);
            emtiaz = itemView.findViewById(R.id.TV_item_pishkhan_khadamatPoshtibani_emtiaz);
            description = itemView.findViewById(R.id.TV_item_pishkhan_khadamatPoshtibani_description);
            nameTamasGirande = itemView.findViewById(R.id.TV_item_pishkhan_khadamatPoshtibani_nameTamasGirande);
            dalilTamasMoshtari = itemView.findViewById(R.id.TV_item_pishkhan_khadamatPoshtibani_dalilTamas);
            saat = itemView.findViewById(R.id.TV_item_pishkhan_khadamatPoshtibani_saat);
            nameMahsol = itemView.findViewById(R.id.TV_item_pishkhan_khadamatPoshtibani_nameMahsol);
            showDes = itemView.findViewById(R.id.IV_item_pishkhan_khadamatPoshtibani_showDes);
            frameLayout = itemView.findViewById(R.id.frame_show_khadamatPoshtibani_detail_des);
            playVoice = itemView.findViewById(R.id.IV_item_khadamatPoshtibani_playVoice);
        }
    }

    public interface Events{
        void onclickVoicePlay(String uniqueId);
    }

}

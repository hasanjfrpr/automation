package com.dayrayaneh.automation.adapter.pishkhan.lid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.lidModel.LidModel;

import java.util.List;

public class LidAdapter extends RecyclerView.Adapter<LidAdapter.LidViewHolder> {

    private LidModel lidModel;
    private Context context;

    public LidAdapter(LidModel lidModel , Context context){
        this.lidModel = lidModel;
        this.context = context;
    }


    @NonNull
    @Override
    public LidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LidViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lid_main , parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LidViewHolder holder, int position) {


        if(position % 2 ==0){
            holder.item_back_lid.setBackground(context.getDrawable(R.color.gray));
        }

        if(lidModel.getData().get(position).getFldProformaKindName().equals("مجموع") || lidModel.getData().get(position).getFldProformaKindName().equals("مجموع(vip)") ||
                lidModel.getData().get(position).getFldProformaKindName().equals("مجموع هردو")){
            holder.item_back_lid.setBackground(context.getDrawable(R.color.greenLighter));
        }



        if (lidModel.getData().get(position).getFldProformaKindName() != null)
        holder.nahveAshenaei.setText(lidModel.getData().get(position).getFldProformaKindName().toString());
        if (lidModel.getData().get(position).getEraeePishFaktor() != null)
        holder.areFactor.setText(lidModel.getData().get(position).getEraeePishFaktor().toString());
        if (lidModel.getData().get(position).getPerezent() != null)
        holder.present.setText(lidModel.getData().get(position).getPerezent().toString());
        if (lidModel.getData().get(position).getDarEntezarMoshtari() != null)
        holder.darEntezarTaeedMoshtari.setText(lidModel.getData().get(position).getDarEntezarMoshtari().toString());
        if (lidModel.getData().get(position).getPeygiriTelefoni() != null)
        holder.paygiryTelfoni.setText(lidModel.getData().get(position).getPeygiriTelefoni().toString());
        if (lidModel.getData().get(position).getPeygiriNashode() != null)
        holder.paygiryNashode.setText(lidModel.getData().get(position).getPeygiriNashode().toString());
        if (lidModel.getData().get(position).getKharidAzSherkatDigar() != null)
        holder.KharidAzsherkatDigar.setText(lidModel.getData().get(position).getKharidAzSherkatDigar().toString());
        if (lidModel.getData().get(position).getDemo() != null)
        holder.demo.setText(lidModel.getData().get(position).getDemo().toString());
        if (lidModel.getData().get(position).getAdamMojodi() != null)
        holder.adamMojoodi.setText(lidModel.getData().get(position).getAdamMojodi().toString());
        if (lidModel.getData().get(position).getMoshtariGheireMortabet() != null)
        holder.moshtariQayrMortabet.setText(lidModel.getData().get(position).getMoshtariGheireMortabet().toString());
        if (lidModel.getData().get(position).getMonjarBeForosh() != null)
        holder.monjarBeforoosh.setText(lidModel.getData().get(position).getMonjarBeForosh().toString());
        if (lidModel.getData().get(position).getMonsarefAzKharid() != null)
        holder.monsarefAzKharid.setText(lidModel.getData().get(position).getMonsarefAzKharid().toString());
        if (lidModel.getData().get(position).getTedadKol() != null)
        holder.tedadKol.setText(lidModel.getData().get(position).getTedadKol().toString());

    }

    @Override
    public int getItemCount() {
        return lidModel.getData().size();
    }

    class LidViewHolder extends RecyclerView.ViewHolder{

        TextView nahveAshenaei,areFactor,present,darEntezarTaeedMoshtari,paygiryTelfoni,paygiryNashode,KharidAzsherkatDigar,demo,adamMojoodi,moshtariQayrMortabet,
                monjarBeforoosh,monsarefAzKharid,tedadKol;
        LinearLayout item_back_lid;

        public LidViewHolder(@NonNull View itemView) {
            super(itemView);
            nahveAshenaei = itemView.findViewById(R.id.TV_lid_item_nahveAshenaei);
            areFactor = itemView.findViewById(R.id.TV_lid_item_areFactor);
            present = itemView.findViewById(R.id.TV_lid_item_present);
            darEntezarTaeedMoshtari = itemView.findViewById(R.id.TV_lid_item_darEntezarTaeedMoshtari);
            paygiryTelfoni = itemView.findViewById(R.id.TV_lid_item_paygiryTelfoni);
            paygiryNashode = itemView.findViewById(R.id.TV_lid_item_paygiryNashode);
            KharidAzsherkatDigar = itemView.findViewById(R.id.TV_lid_item_KharidAzsherkatDigar);
            demo = itemView.findViewById(R.id.TV_lid_item_demo);
            adamMojoodi = itemView.findViewById(R.id.TV_lid_item_adamMojoodi);
            moshtariQayrMortabet = itemView.findViewById(R.id.TV_lid_item_moshtariQayrMortabet);
            monjarBeforoosh = itemView.findViewById(R.id.TV_lid_item_monjarBeforoosh);
            monsarefAzKharid = itemView.findViewById(R.id.TV_lid_item_monsarefAzKharid);
            tedadKol = itemView.findViewById(R.id.TV_lid_item_tedadKol);


            item_back_lid = itemView.findViewById(R.id.item_back_lid);
        }
    }
}

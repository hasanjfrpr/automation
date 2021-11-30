package com.dayrayaneh.automation.adapter.pishkhan.VaziatSefaresh;



import android.content.ContentResolver;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.vaziatSefareshat.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class VaziatSefareshAdapter extends RecyclerView.Adapter<VaziatSefareshAdapter.VaziatSefareshViewHolder> {

    private Context context;
    private List<DataItem> dataItemList = new ArrayList<>();
    private boolean isOpen  = false;

    public VaziatSefareshAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public VaziatSefareshViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_vaziat_sefareshat,parent,false);
        return new VaziatSefareshViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VaziatSefareshViewHolder holder, int position) {

        holder.status.setText(dataItemList.get(position).getStatusOrder());
        holder.nameMoshtari.setText(dataItemList.get(position).getFullName());
        holder.codeHesab.setText(dataItemList.get(position).getFldMoshtarianCodeHesab());
        holder.shomareSefaresh.setText(Utils.formatPersianNumber(dataItemList.get(position).getFldOrderNumber()));
        holder.mablaqSefaresh.setText(Utils.formatPersianNumber(dataItemList.get(position).getFldOrderPrice()));
        holder.nahveAhsnaee.setText(dataItemList.get(position).getNahveAshnaye());


        if (dataItemList.get(position).getFldKindKhadamatNameL1().length() > 35){
            holder.shoMore.setVisibility(View.VISIBLE);
            holder.frameLayout.setVisibility(View.GONE);
            holder.noeSefaresh.setVisibility(View.GONE);
            holder.shoMore.setOnClickListener(v->{
                if (isOpen){
                    holder.frameLayout.setVisibility(View.GONE);
                    holder.noeSefaresh2.setText(dataItemList.get(position).getFldKindKhadamatNameL1());
                    holder.shoMore.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
                    isOpen = false;

                }else {
                    holder.frameLayout.setVisibility(View.VISIBLE);
                    holder.frameLayout.setBackgroundColor(context.getResources().getColor(R.color.graylight));
                    holder.noeSefaresh2.setText(dataItemList.get(position).getFldKindKhadamatNameL1());
                    holder.shoMore.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                    isOpen = true;

                }            });

        }else {
            holder.noeSefaresh.setVisibility(View.VISIBLE);
            holder.shoMore.setVisibility(View.GONE);
            holder.frameLayout.setVisibility(View.GONE);
            holder.noeSefaresh.setText(dataItemList.get(position).getFldKindKhadamatNameL1());
        }

    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }


    public class VaziatSefareshViewHolder extends RecyclerView.ViewHolder{

        TextView status , nahveAhsnaee , nameMoshtari , codeHesab , noeSefaresh , shomareSefaresh ,mablaqSefaresh , noeSefaresh2;
        FrameLayout frameLayout;
        ImageView shoMore;


        public VaziatSefareshViewHolder(@NonNull View itemView) {
            super(itemView);

            status = itemView.findViewById(R.id.TV_item_pishkhan_vazatSefaresh_status);
            nahveAhsnaee = itemView.findViewById(R.id.TV_item_pishkhan_vazatSefaresh_nahveAshnaee);
            nameMoshtari = itemView.findViewById(R.id.TV_item_pishkhan_vazatSefaresh_nameMoshtari);
            codeHesab = itemView.findViewById(R.id.TV_item_pishkhan_vazatSefaresh_codeHesabMoshtari );
            noeSefaresh = itemView.findViewById(R.id.TV_item_pishkhan_vazatSefaresh_noeSefaresh);
            noeSefaresh2 = itemView.findViewById(R.id.TV_item_pishkhan_vazatSefaresh_noeSefaresh2);
            shomareSefaresh=itemView.findViewById(R.id.TV_item_pishkhan_vazatSefaresh_shomareSefaresh);
            mablaqSefaresh = itemView.findViewById(R.id.TV_item_pishkhan_vazatSefaresh_mablaqSefaresh);
            frameLayout  = itemView.findViewById(R.id.frame_moreContent_noeSefaresh);
            shoMore = itemView.findViewById(R.id.IV_showMore_vaziatSefaresh);

        }
    }
}

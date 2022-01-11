package com.dayrayaneh.automation.adapter.mainListadapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.PishKhanAdapter;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishKhanModel.PishKhanModel;
import com.dayrayaneh.automation.model.mainListModel.MainListModel;
import com.dayrayaneh.automation.view.pishkhanItemView.VoicePoshtibani.VoicePoshtibaniActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.bazaryabi.BazaryabiActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.darsadKhardiMoshtari.DarsadKharidMoshtariActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.darsadKharidShahrestanha.DarsadKharidShahrestanActiviy;
import com.dayrayaneh.automation.view.pishkhanItemView.darsadSefareshat.DarsadSefareshatActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.darsadTakhfifAzHarSefaresh.DarsadTakhfifAzHarSefareshActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.foroshNarmAfZar.ForooshNarmAfzarActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.foroshSakhtAfzar.ForooshSakhtAfzarActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.gozareshKar.GozareshKarActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.hokmKarha.HokmKarhaActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.khadamatPoshtibani.KhadamatPoshtibaniActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.sefarashMoshtariJadid.SefareshMoshtariJadidActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.tamdidGharardad.TamdidGharardadActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.tedadHokmKarha.TedadHokmKarhaActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.ticket.TicketActivity;
import com.dayrayaneh.automation.view.pishkhanItemView.vaziatSefaresh.VaziatSefareshActivity;
import com.google.android.material.card.MaterialCardView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> implements PishKhanAdapter.PishKhanItemEvent {

    private List<MainListModel> mainListModelList = new ArrayList<>();
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private Context context;
    public OnclickItemSubRecycler onclickItemSubRecycler;



    public MainAdapter(List<MainListModel> mainListModelList, Context context) {
        this.mainListModelList = mainListModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_list, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.title.setText(mainListModelList.get(position).getTitle_Main());


        List<PishKhanModel> subList = new ArrayList<>();

        ////init subList for show in every item
        if (mainListModelList.get(position).getTitle_Main().equals("پیشخوان مدیریت")) {


            if (ConstValue.accessItemIdList.contains(1905) || ConstValue.isAdminLis.contains(1)) {
                subList.add(new PishKhanModel(context.getResources().getString(R.string.hokmKarha), R.mipmap.ic_sefaresh_jadid,1));
            }
            if (ConstValue.accessItemIdList.contains(3937) || ConstValue.isAdminLis.contains(1)) {
                subList.add(new PishKhanModel(context.getResources().getString(R.string.bazaryabi), R.mipmap.ic_marketing,2));
            }
            if (ConstValue.accessItemIdList.contains(1904) || ConstValue.isAdminLis.contains(1)) {
                subList.add(new PishKhanModel(context.getResources().getString(R.string.khadamatPoshtibani), R.mipmap.ic_poshtibani,3));
            }
            if (ConstValue.accessItemIdList.contains(1917) || ConstValue.isAdminLis.contains(1)) {
                subList.add(new PishKhanModel(context.getResources().getString(R.string.tedadHokmKarha), R.mipmap.ic_tedad_hokm_karha,4));
            }    if (ConstValue.accessItemIdList.contains(1912) || ConstValue.isAdminLis.contains(1)) {
                subList.add(new PishKhanModel(context.getResources().getString(R.string.gozareshKar), R.mipmap.ic_hokm_karha,5));
            }

            if (ConstValue.isAdminLis.contains(1)) {
                subList.add(new PishKhanModel(context.getResources().getString(R.string.softWareSell), R.mipmap.ic_software,6));
                subList.add(new PishKhanModel(context.getResources().getString(R.string.hardWareSell), R.mipmap.ic_hardware,7));
                subList.add(new PishKhanModel(context.getResources().getString(R.string.tamdidQarardad), R.mipmap.ic_tamdid_gharardad,8));
                subList.add(new PishKhanModel(context.getResources().getString(R.string.sefareshatMoshtariJadid), R.mipmap.ic_sefaresh,9));
                subList.add(new PishKhanModel(context.getResources().getString(R.string.vaziatSefaresh), R.mipmap.ic_darsad_khardi_shahrestan,10));
                subList.add(new PishKhanModel(context.getResources().getString(R.string.darsadKharidMoshtari), R.mipmap.ic_kharid,11));
                subList.add(new PishKhanModel(context.getResources().getString(R.string.darsadTakhfifAzHarsefaresh), R.mipmap.ic_kharid2,12));
                subList.add(new PishKhanModel(context.getResources().getString(R.string.darsadSefareshat), R.mipmap.ic_darsad_sefareshat,13));
                subList.add(new PishKhanModel(context.getResources().getString(R.string.darsadKharidShahrestan), R.mipmap.ic_darsad_thakfif,14));
                subList.add(new PishKhanModel(context.getResources().getString(R.string.voicePoshtibani), R.mipmap.ic_voice_item,15));
                subList.add(new PishKhanModel(context.getResources().getString(R.string.ticket), R.mipmap.ic_ticket,16));
                subList.add(new PishKhanModel(context.getResources().getString(R.string.unDoneHokmKar), R.mipmap.ic_undonehokmkar,17));
            }



        }


        ///setup subRecycler
        PishKhanAdapter pishKhanAdapter = new PishKhanAdapter(context, subList);
        holder.subRecyclerView.setLayoutManager(new GridLayoutManager(context, 2, RecyclerView.VERTICAL, false));
        holder.subRecyclerView.setAdapter(pishKhanAdapter);
        pishKhanAdapter.event = this;



        boolean[] isShow = new boolean[mainListModelList.size()];
        for (int i = 0; i < mainListModelList.size(); i++) {
            isShow[i]=true;
        }
        holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.whiteYellow));
        holder.materialCardView.setCardBackgroundColor(context.getResources().getColor(R.color.whiteYellow));
        holder.itemView.setOnClickListener(v -> {

//            checkAccess(position , holder);


            if (!isShow[position]) {
                holder.subRecyclerView.animate().scaleY(1f);
                holder.subRecyclerView.animate().scaleX(1f);
                holder.subRecyclerView.animate().alpha(1);
                holder.subRecyclerView.setVisibility(View.VISIBLE);


                isShow[position] = true;
                ConstValue.menuIsOpen = true;
                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.whiteYellow));
                holder.materialCardView.setCardBackgroundColor(context.getResources().getColor(R.color.whiteYellow));


            } else {
                holder.subRecyclerView.animate().scaleY(0f);
                holder.subRecyclerView.animate().scaleX(0f);
                holder.subRecyclerView.animate().alpha(0);
                holder.subRecyclerView.setVisibility(View.GONE);


                isShow[position] = false;
                ConstValue.menuIsOpen = false;
                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.whiteDark));
                holder.materialCardView.setCardBackgroundColor(context.getResources().getColor(R.color.whiteDark));


            }
        });

    }

    @Override
    public int getItemCount() {
        return mainListModelList.size();
    }


    public class MainViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        RecyclerView subRecyclerView;
        LinearLayout linearLayout;
        CardView materialCardView;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.TV_item_title_mainList);
            subRecyclerView = itemView.findViewById(R.id.RV_subRecycler);
            linearLayout = itemView.findViewById(R.id.parent_itemview_main_list);
            materialCardView = itemView.findViewById(R.id.McardView_item_main_list);

        }
    }


    @Override
    public void onclickItemPishKhan(int id) {
        onclickItemSubRecycler.onclick(id);

    }

    public interface  OnclickItemSubRecycler{
        void onclick(int id);
    }

    private void checkAccess(int position, MainViewHolder holder) {

    /*    if (ConstValue.isAdminLis.contains(1) || ConstValue.accessItemIdList.contains(ConstValue.containAccessList[position])) {

            if (!isShow) {
                holder.subRecyclerView.setVisibility(View.VISIBLE);
                isShow = true;
                ConstValue.menuIsOpen = true;
                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.whiteYellow));
                holder.materialCardView.setCardBackgroundColor(context.getResources().getColor(R.color.whiteYellow));


            } else {
                holder.subRecyclerView.setVisibility(View.GONE);
                isShow = false;
                ConstValue.menuIsOpen = false;
                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.whiteDark));
                holder.materialCardView.setCardBackgroundColor(context.getResources().getColor(R.color.whiteDark));

            }

        } else {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.redlight));
            holder.materialCardView.setCardBackgroundColor(context.getResources().getColor(R.color.redlight));
            EventBus.getDefault().post("unAccess");
        }*/


    }


}

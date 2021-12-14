package com.dayrayaneh.automation.adapter.mainListadapter;

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
import com.dayrayaneh.automation.view.pishkhanItemView.vaziatSefaresh.VaziatSefareshActivity;
import com.google.android.material.card.MaterialCardView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> implements PishKhanAdapter.PishKhanItemEvent {

    private List<MainListModel> mainListModelList = new ArrayList<>();
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private Context context;
    private Boolean isShow=false;



    public MainAdapter(List<MainListModel> mainListModelList, Context context) {
        this.mainListModelList = mainListModelList;
        this.context = context;
    }



    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_main_list,parent , false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.title.setText(mainListModelList.get(position).getTitle_Main());




         List<PishKhanModel> subList = new ArrayList<>();

         ////init subList for show in every item
        if (mainListModelList.get(position).getTitle_Main().equals("پیشخوان مدیریت")){
            subList.add(new PishKhanModel(context.getResources().getString(R.string.softWareSell),R.mipmap.ic_software));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.hardWareSell),R.mipmap.ic_hardware));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.tamdidQarardad),R.mipmap.ic_tamdid_gharardad));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.khadamatPoshtibani),R.mipmap.ic_poshtibani));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.hokmKarha),R.mipmap.ic_sefaresh_jadid));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.bazaryabi),R.mipmap.ic_marketing));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.sefareshatMoshtariJadid),R.mipmap.ic_sefaresh));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.vaziatSefaresh),R.mipmap.ic_darsad_khardi_shahrestan));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.darsadKharidMoshtari),R.mipmap.ic_kharid));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.gozareshKar),R.mipmap.ic_hokm_karha));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.darsadTakhfifAzHarsefaresh),R.mipmap.ic_kharid2));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.darsadSefareshat),R.mipmap.ic_darsad_sefareshat));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.tedadHokmKarha),R.mipmap.ic_tedad_hokm_karha));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.darsadKharidShahrestan),R.mipmap.ic_darsad_thakfif));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.voicePoshtibani),R.mipmap.ic_darsad_thakfif));

        }




        ///setup subRecycler
        PishKhanAdapter pishKhanAdapter = new PishKhanAdapter(context, subList);
        holder.subRecyclerView.setLayoutManager(new GridLayoutManager(context,2,RecyclerView.VERTICAL,false));
        holder.subRecyclerView.setAdapter(pishKhanAdapter);
        pishKhanAdapter.event = this;




        holder.itemView.setOnClickListener(v->{

            checkAccess(position , holder);


        });

    }

    @Override
    public int getItemCount() {
        return mainListModelList.size();
    }



    public class MainViewHolder extends RecyclerView.ViewHolder{

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
    public void onclickItemPishKhan(int position) {
        switch (position){
            case 0:
                context.startActivity(new Intent(context , ForooshNarmAfzarActivity.class));

                break;
            case 1:
                context.startActivity(new Intent(context , ForooshSakhtAfzarActivity.class));


                break;
            case 2:
                context.startActivity(new Intent(context , TamdidGharardadActivity.class));


                break;
            case 3:
                context.startActivity(new Intent(context , KhadamatPoshtibaniActivity.class));


                break;
            case 4:
                context.startActivity(new Intent(context , HokmKarhaActivity.class));


                break;
            case 5:
                context.startActivity(new Intent(context , BazaryabiActivity.class));


                break;
            case 6:
                context.startActivity(new Intent(context , SefareshMoshtariJadidActivity.class));


                break;
            case 7:
                context.startActivity(new Intent(context , VaziatSefareshActivity.class));


                break;
            case 8:
                context.startActivity(new Intent(context , DarsadKharidMoshtariActivity.class));


                break;
            case 9:
                context.startActivity(new Intent(context , GozareshKarActivity.class));


                break;
            case 10:
                context.startActivity(new Intent(context , DarsadTakhfifAzHarSefareshActivity.class));


                break;
            case 11:
                context.startActivity(new Intent(context , DarsadSefareshatActivity.class));

                break;
            case 12:
                context.startActivity(new Intent(context , TedadHokmKarhaActivity.class));

                break;
            case 13:
                context.startActivity(new Intent(context , DarsadKharidShahrestanActiviy.class));

                break;
            case 14:
                context.startActivity(new Intent(context , VoicePoshtibaniActivity.class));
                break;



        }

    }

    private void checkAccess(int position , MainViewHolder holder){

            if (ConstValue.isAdminLis.contains(1) || ConstValue.accessItemIdList.contains(ConstValue.containAccessList[position])){

                if (!isShow){
                    holder.subRecyclerView.setVisibility(View.VISIBLE);
                    isShow = true;
                    ConstValue.menuIsOpen=true;
                    holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.whiteYellow));
                    holder.materialCardView.setCardBackgroundColor(context.getResources().getColor(R.color.whiteYellow));


                }else {
                    holder.subRecyclerView.setVisibility(View.GONE);
                    isShow = false;
                    ConstValue.menuIsOpen = false;
                    holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.whiteDark));
                    holder.materialCardView.setCardBackgroundColor(context.getResources().getColor(R.color.whiteDark));

                }

            }else {
                holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.redlight));
                holder.materialCardView.setCardBackgroundColor(context.getResources().getColor(R.color.redlight));
                EventBus.getDefault().post("unAccess");
            }




    }




}

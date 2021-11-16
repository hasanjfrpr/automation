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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.PishKhanAdapter;
import com.dayrayaneh.automation.base.ConstValue;
import com.dayrayaneh.automation.model.pishKhanModel.PishKhanModel;
import com.dayrayaneh.automation.model.mainListModel.MainListModel;
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

        }

        else if (mainListModelList.get(position).getTitle_Main().equals("گزارش لیدها ")){
            subList.add(new PishKhanModel(context.getResources().getString(R.string.darsadSefareshat),R.drawable.ic_timer));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.tedadHokmKarha),R.drawable.ic_airplan));
            subList.add(new PishKhanModel(context.getResources().getString(R.string.darsadKharidShahrestan),R.drawable.ic_photo));
        }



        ///setup subRecycler
        PishKhanAdapter pishKhanAdapter = new PishKhanAdapter(context, subList);
        holder.subRecyclerView.setLayoutManager(new GridLayoutManager(context,2,RecyclerView.VERTICAL,false));
        holder.subRecyclerView.setAdapter(pishKhanAdapter);


        holder.itemView.setOnClickListener(v->{

            if (position ==0){
                if (ConstValue.isAdminLis.contains(1) || ConstValue.accessItemIdList.contains(1857)){

                    if (!isShow){
                        holder.subRecyclerView.setVisibility(View.VISIBLE);
                        isShow = true;
                        ConstValue.menuIsOpen=true;
                        holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.whiteYellow));
                        holder.materialCardView.setCardBackgroundColor(context.getResources().getColor(R.color.whiteYellow));

                        pishKhanAdapter.event = this;
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

                }


            }





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
        MaterialCardView materialCardView;

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
                Toast.makeText(context,context.getResources().getString(R.string.softWareSell), Toast.LENGTH_SHORT).show();
                break;
            case 1:
                context.startActivity(new Intent(context , ForooshSakhtAfzarActivity.class));
                Toast.makeText(context,context.getResources().getString(R.string.hardWareSell), Toast.LENGTH_SHORT).show();

                break;
            case 2:
                context.startActivity(new Intent(context , TamdidGharardadActivity.class));
                Toast.makeText(context,context.getResources().getString(R.string.tamdidQarardad), Toast.LENGTH_SHORT).show();

                break;
            case 3:
                context.startActivity(new Intent(context , KhadamatPoshtibaniActivity.class));
                Toast.makeText(context,context.getResources().getString(R.string.khadamatPoshtibani), Toast.LENGTH_SHORT).show();

                break;
            case 4:
                context.startActivity(new Intent(context , HokmKarhaActivity.class));
                Toast.makeText(context,context.getResources().getString(R.string.hokmKarha), Toast.LENGTH_SHORT).show();

                break;
            case 5:
                context.startActivity(new Intent(context , BazaryabiActivity.class));
                Toast.makeText(context,context.getResources().getString(R.string.bazaryabi), Toast.LENGTH_SHORT).show();

                break;
            case 6:
                context.startActivity(new Intent(context , SefareshMoshtariJadidActivity.class));
                Toast.makeText(context,context.getResources().getString(R.string.sefareshatMoshtariJadid), Toast.LENGTH_SHORT).show();

                break;
            case 7:
                context.startActivity(new Intent(context , VaziatSefareshActivity.class));
                Toast.makeText(context,context.getResources().getString(R.string.vaziatSefaresh), Toast.LENGTH_SHORT).show();

                break;
            case 8:
                context.startActivity(new Intent(context , DarsadKharidMoshtariActivity.class));
                Toast.makeText(context,context.getResources().getString(R.string.darsadKharidMoshtari), Toast.LENGTH_SHORT).show();

                break;
            case 9:
                context.startActivity(new Intent(context , GozareshKarActivity.class));
                Toast.makeText(context,context.getResources().getString(R.string.gozareshKar), Toast.LENGTH_SHORT).show();

                break;
            case 10:
                context.startActivity(new Intent(context , DarsadTakhfifAzHarSefareshActivity.class));
                Toast.makeText(context,context.getResources().getString(R.string.darsadTakhfifAzHarsefaresh), Toast.LENGTH_SHORT).show();

                break;
            case 11:
                context.startActivity(new Intent(context , DarsadSefareshatActivity.class));
                Toast.makeText(context,context.getResources().getString(R.string.darsadSefareshat), Toast.LENGTH_SHORT).show();

                break;
            case 12:
                context.startActivity(new Intent(context , TedadHokmKarhaActivity.class));
                Toast.makeText(context,context.getResources().getString(R.string.tedadHokmKarha), Toast.LENGTH_SHORT).show();

                break;
            case 13:
                context.startActivity(new Intent(context , DarsadKharidShahrestanActiviy.class));
                Toast.makeText(context,context.getResources().getString(R.string.darsadKharidShahrestan), Toast.LENGTH_SHORT).show();

                break;



        }

    }




}

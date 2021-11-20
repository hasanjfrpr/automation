package com.dayrayaneh.automation.adapter.pishkhan.forooshSakhtAzar.productCategories;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.forooshSakhtAzar.ForooshSakhtAfzarAdapter;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.productCategories.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoriesAdapter extends RecyclerView.Adapter<ProductCategoriesAdapter.ProductCategoriesViewHolder>{
    private Context context;
    private List<com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.productCategories.DataItem> dataItemList = new ArrayList<>();
    public productCategoriesId productCategoriesId;
    public List<Integer> ids = new ArrayList<>();

    public ProductCategoriesAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public ProductCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_categories_hardware_sell,parent , false);
        return new ProductCategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCategoriesViewHolder holder, int position) {
        holder.name.setText(dataItemList.get(position).getName());
        holder.checkBox.setChecked(true);
        ids.add(position  , dataItemList.get(position).getId());

        holder.checkBox.setOnClickListener(v -> {
            if (holder.checkBox.isChecked()){
                ids.add(position , dataItemList.get(position).getId());
            }else {
                ids.remove(position);
            }
        });

        productCategoriesId.getProductCategoriesID(ids);

    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }


    public class ProductCategoriesViewHolder extends RecyclerView.ViewHolder{
      TextView name;
      CheckBox checkBox;
        public ProductCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.TV_item_product_categories);
            checkBox= itemView.findViewById(R.id.checkbox_item_product_categories);
        }
    }

    public interface productCategoriesId{
        void getProductCategoriesID(List<Integer> ids);
    }
}

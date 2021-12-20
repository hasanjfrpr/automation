package com.dayrayaneh.automation.adapter.pishkhan.gozareshKar.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.count.DataItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GozareshKarMainAdapter extends RecyclerView.Adapter<GozareshKarMainAdapter.GozareshKarMainViewHolder> implements Filterable {


    private Context context;
    private List<DataItem>  dataItems = new ArrayList<>();
    private List<DataItem>  dataItemsfull;
    public ClickItemEvent event;

    public GozareshKarMainAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
        this.dataItemsfull =new ArrayList<>(dataItems);
    }

    @NonNull
    @Override
    public GozareshKarMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_gozareshkar_main,parent, false  );
        return new GozareshKarMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GozareshKarMainViewHolder holder, int position) {

        holder.name.setText(dataItems.get(position).getFullNameWork());
        holder.tedadGozaresh.setText(String.valueOf(dataItems.get(position).getCountReport()));
        holder.time.setText(String.valueOf(dataItems.get(position).getTotalTimeWorkReport()));

        holder.itemView.setOnClickListener(v -> {
            event.onClickItem(dataItems.get(position).getUserCode() , dataItems.get(position).getFullNameWork());
        });
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DataItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataItemsfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (DataItem item : dataItemsfull) {
                    if (item.getFullNameWork().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }


        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            dataItems.clear();
            dataItems.addAll((Collection<? extends DataItem>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class GozareshKarMainViewHolder extends RecyclerView.ViewHolder{
        TextView name , tedadGozaresh , time;
        public GozareshKarMainViewHolder(@NonNull View itemView) {
            super(itemView);

            tedadGozaresh = itemView.findViewById(R.id.TV_pishkhan_gozareshKar_count_tedadGozareshat);
            name =  itemView.findViewById(R.id.TV_pishkhan_gozareshKar_count_name);
            time = itemView.findViewById(R.id.TV_pishkhan_gozareshKar_count_majmoZaman);
        }
    }

    public interface ClickItemEvent {
        void onClickItem(int userCode , String name);
    }
}

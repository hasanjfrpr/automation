package com.dayrayaneh.automation.adapter.pishkhan.gozareshKar.spinnerAdapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.personalName.DataItem;
import com.github.bkhezry.searchablespinner.interfaces.ISpinnerSelectedView;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private List<DataItem> personList = new ArrayList<>();
    private PersonFilter personFilter = new PersonFilter();


    public SpinnerAdapter(Context context, List<DataItem> personList) {
        this.context = context;
        this.personList = personList;
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int position) {
        return personList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return personList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_spinner,null);
        TextView name = view.findViewById(R.id.display_name_text_view);
        name.setText(personList.get(position).getName());
        return view;
    }




    public class PersonFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults filterResults = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                filterResults.count = personList.size();
                filterResults.values = personList;
                return filterResults;
            }
            final ArrayList<DataItem> filterPersons = new ArrayList<>();
            for (DataItem person : personList) {
                if (person.getName().toLowerCase().contains(constraint)) {
                    filterPersons.add(person);
                }
            }
            filterResults.count = filterPersons.size();
            filterResults.values = filterPersons;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            personList = (ArrayList<DataItem>) results.values;
            notifyDataSetChanged();
        }


    }
    @Override
    public Filter getFilter() {
        return personFilter;
    }
}

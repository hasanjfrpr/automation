package com.dayrayaneh.automation.view.pishkhanItemView.unDoneHokmKar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.adapter.pishkhan.UndoneHokmKar.UnDoneHokmKarAdapter;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.view.pishkhanItemView.unDoneHokmKar.fragment.DetailsUnDoneHokmKarFragment;
import com.dayrayaneh.automation.view.pishkhanItemView.unDoneHokmKar.fragment.MainUnDoneHokmKarFragment;
import com.dayrayaneh.automation.viewModel.pishkhan.UndoneHokmKar.UnDoneHokmKarViewModel;
import com.google.android.material.button.MaterialButton;

public class UnDoneHokmKarActivity extends AppCompatActivity {

    private UnDoneHokmKarViewModel thisViewModel;
    private TextView startDate , endDate;
    private MaterialButton send;
    private View loadingView;
    private View empty_layout;
    private UnDoneHokmKarAdapter adapter;
    private Toolbar toolbar;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_done_hokm_kar);
        init();
        setDate();
        event();

    }


    private void init() {
        startDate =findViewById(R.id.TV_fromDate);
        endDate = findViewById(R.id.Tv_toDate);
        send=findViewById(R.id.Mbtn_pishKhan_UnDonehokmKar_sendInfo);
        loadingView = findViewById(R.id.loading_view_undone);

        empty_layout = findViewById(R.id.empty_undone);
        back = findViewById(R.id.IV_back_item_pishkhan);
        toolbar  = findViewById(R.id.toolbar_item_pishkhan);
        toolbar.setTitle(getResources().getString(R.string.unDoneHokmKar));
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_undone_hokmkar , new MainUnDoneHokmKarFragment() , "mainUndon").commit();
    }
    private void event() {
        send.setOnClickListener(view -> {
            MainUnDoneHokmKarFragment mainUndon = (MainUnDoneHokmKarFragment) getSupportFragmentManager().findFragmentByTag("mainUndon");
            mainUndon.viewModel();
        });
        back.setOnClickListener(v->{
            finish();
        });

        DetailsUnDoneHokmKarFragment.isHide.observe(this,isHide->{
            if (isHide){
                findViewById(R.id.include7).setVisibility(View.GONE);
                send.setVisibility(View.GONE);
            }else {
                findViewById(R.id.include7).setVisibility(View.VISIBLE);
                send.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setDate(){
        Utils.setDate(startDate , endDate , this);
    }

//    private void viewModel(){
//        loadingView.setVisibility(View.VISIBLE);
//        thisViewModel.getUnDoneHokmKar(ConstValue.startDate , ConstValue.endDate);
//        thisViewModel.unDoneHokmKarModelMutableLiveData.observe(this, unDoneHokmKarModel -> {
//            loadingView.setVisibility(View.GONE);
//            if (unDoneHokmKarModel.getData().size() <1){
//                empty_layout.setVisibility(View.VISIBLE);
//                recyclerView.setVisibility(View.INVISIBLE);
//            }else {
//                empty_layout.setVisibility(View.GONE);
//                setRecyclerView(unDoneHokmKarModel);
//            }
//        });
//    }
//
//    private void setRecyclerView(UnDoneHokmKarModel unDoneHokmKarModel) {
//
//        recyclerView.setVisibility(View.VISIBLE);
//        adapter = new UnDoneHokmKarAdapter(this,unDoneHokmKarModel.getData() , finterColor(unDoneHokmKarModel));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL , false));
//        recyclerView.setAdapter(adapter);
//    }
//
//    private HashMap<Double, Integer> finterColor(UnDoneHokmKarModel unDoneHokmKarModel) {
//         HashMap<Double , Integer> hashMap = new HashMap<>();
//        List<Double> hokmNumberList = new ArrayList<>();
//        HashSet<Double> hashSet = new HashSet<>();
//        Random random = new Random();
//        for (int i = 0; i < unDoneHokmKarModel.getData().size(); i++) {
//            hokmNumberList.add(unDoneHokmKarModel.getData().get(i).getHokmNumber());
//        }
//        hashSet.addAll(hokmNumberList);
//        hokmNumberList.clear();
//        hokmNumberList.addAll(hashSet);
//
//        for (int i = 0; i < hokmNumberList.size(); i++) {
//            hashMap.put(hokmNumberList.get(i) , Color.argb(30,100+random.nextInt(250),100+random.nextInt(250),random.nextInt(200)));
//        }
//
//        return hashMap;
//
//
//    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag("detailUndon") != null){
            getSupportFragmentManager().popBackStack();
        }else
        super.onBackPressed();
    }
}

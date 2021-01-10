package com.example.fikfishdriver.network.repository.history;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.adapter.AdapterHistoryDate;
import com.example.fikfishdriver.model.history.DataHistory;
import com.example.fikfishdriver.model.history.HistoryResponse;
import com.example.fikfishdriver.session.SystemDataLocal;
import com.example.fikfishdriver.ui.history.GetDataHistoryDetailViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

public class DetailHistoryActivity extends AppCompatActivity {


    RecyclerView rv_history;
    private SystemDataLocal systemDataLocal;
    private GetDataHistoryDetailViewModel getDataHistoryDetailViewModel;
    DataHistory dataHistory;
    TextView tv_detail;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        rv_history = findViewById(R.id.rv_history);
        tv_detail = findViewById(R.id.tv_detail);
        systemDataLocal = new SystemDataLocal(this);
        getDataHistoryDetailViewModel = ViewModelProviders.of(this).get(GetDataHistoryDetailViewModel.class);
        dataHistory = getIntent().getParcelableExtra("data");
        if(dataHistory != null){
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
            Locale id = new Locale("in","ID");
            String date = dataHistory.getDateShipping();
            try {
                String hari = format2.format(Objects.requireNonNull(format1.parse(date)));
                tv_detail.setText("Detail Riwayat "+hari);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView tv_title = findViewById(R.id.title);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        tv_title.setText("Detail Riwayat");
        loadData();

    }

    private void loadData() {
        String id_courier = systemDataLocal.getLoginData().getId_kurir();
        String date = dataHistory.getDateShipping();

        getDataHistoryDetailViewModel.getDataHistoryDetail(id_courier,date).observe(this, new Observer<HistoryResponse>() {
            @Override
            public void onChanged(HistoryResponse historyResponse) {
                if(historyResponse.getStatus()){
                    if(historyResponse.getDataHistory().size() > 0){
                        AdapterHistoryDate adapterHistoryDate = new AdapterHistoryDate(getApplicationContext(),historyResponse.getDataHistory());
                        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
                        rv_history.setAdapter(adapterHistoryDate);
                        rv_history.setLayoutManager(lm);
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}
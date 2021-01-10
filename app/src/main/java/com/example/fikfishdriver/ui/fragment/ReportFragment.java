package com.example.fikfishdriver.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fikfishdriver.R;
import com.example.fikfishdriver.adapter.AdapterHistory;
import com.example.fikfishdriver.model.history.HistoryResponse;
import com.example.fikfishdriver.session.SystemDataLocal;
import com.example.fikfishdriver.ui.history.GetDataHistoryViewModel;

public class ReportFragment extends Fragment {

    RecyclerView rv_history;
    private SystemDataLocal systemDataLocal;
    private GetDataHistoryViewModel getDataHistoryViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_history  = view.findViewById(R.id.rv_history);
        systemDataLocal = new SystemDataLocal(getContext());
        getDataHistoryViewModel = ViewModelProviders.of(this).get(GetDataHistoryViewModel.class);
        loadData();
    }

    private void loadData() {
        String id_courier = systemDataLocal.getLoginData().getId_kurir();
        getDataHistoryViewModel.getDataHistory(id_courier).observe(this, new Observer<HistoryResponse>() {
            @Override
            public void onChanged(HistoryResponse historyResponse) {
                if(historyResponse.getStatus()){
                    if(historyResponse.getDataHistory().size() > 0){
                        AdapterHistory adapterHistory = new AdapterHistory(getContext(),historyResponse.getDataHistory());
                        rv_history.setLayoutManager(new GridLayoutManager(getContext(),2));
                        rv_history.setAdapter(adapterHistory);
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
}
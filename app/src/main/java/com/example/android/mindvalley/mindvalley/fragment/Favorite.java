package com.example.android.mindvalley.mindvalley.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.mindvalley.R;
import com.example.android.mindvalley.mindvalley.adapter.FavoriteAdapter;
import com.example.android.mindvalley.mindvalley.room.PinDataBase;
import com.example.android.mindvalley.mindvalley.room.PinEntity;
import com.example.android.mindvalley.mindvalley.room.PinViewModel;

import java.util.List;

public class Favorite extends Fragment {

    private RecyclerView recyclerView;
    private PinDataBase pinDataBase;
    private FavoriteAdapter adapter;
    private ProgressBar progressBar;

    public Favorite(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pin_list, container, false);

        recyclerView = view.findViewById(R.id.recycle_view);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        pinDataBase = PinDataBase.getPinDataBase(getContext());

        PinViewModel viewModel = ViewModelProviders.of(this).get(PinViewModel.class);
        viewModel.getEntities().observe(this, new Observer<List<PinEntity>>() {
            @Override
            public void onChanged(@Nullable List<PinEntity> pinEntities) {
                adapter = new FavoriteAdapter(getContext(), pinEntities);
                recyclerView.setAdapter(adapter);

            }
        });

        return view;
    }
}

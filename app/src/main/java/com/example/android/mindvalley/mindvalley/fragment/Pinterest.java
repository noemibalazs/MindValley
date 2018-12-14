package com.example.android.mindvalley.mindvalley.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.mindvalley.R;
import com.example.android.mindvalley.mindvalley.adapter.PinRecycleAdapter;
import com.example.android.mindvalley.mindvalley.loader.PinLoader;
import com.example.android.mindvalley.mindvalley.model.Pin;

import java.util.ArrayList;
import java.util.List;

public class Pinterest extends Fragment implements LoaderManager.LoaderCallbacks<List<Pin>>{

    private PinRecycleAdapter recycleAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private String link;
    private static final int LOADER_ID = 9;

    private static final String TAG = Pinterest.class.getSimpleName();

    public Pinterest(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pin_list, container, false);

        recyclerView = view.findViewById(R.id.recycle_view);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        recycleAdapter = new PinRecycleAdapter(getContext(), new ArrayList<Pin>());
        recyclerView.setAdapter(recycleAdapter);

        progressBar = view.findViewById(R.id.progress_bar);

        ConnectivityManager connectivityManager = (ConnectivityManager) view.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()){
          LoaderManager loaderManager = getLoaderManager();
          loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
        }

        return view;
    }


    public boolean isChecked(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            return true;
        }

        return false;
    }

    @NonNull
    @Override
    public android.support.v4.content.Loader<List<Pin>> onCreateLoader(int id, @Nullable Bundle args) {
        link = "http://pastebin.com/raw/wgkJgazE";
        return new PinLoader(getContext(), link);
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<List<Pin>> loader, List<Pin> data) {
        progressBar.setVisibility(View.GONE);

        if (data !=null && !data.isEmpty()){
            recycleAdapter.bindData((ArrayList<Pin>) data);
        }else{
            Toast.makeText(getContext(), getString(R.string.sorry_no_pictures), Toast.LENGTH_SHORT).show();
        } if(!isChecked()){
            Toast.makeText(getContext(), getString(R.string.sorry_no_connection), Toast.LENGTH_SHORT).show();
        }else{
            Log.v(TAG, "Network is available!");       }

    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<List<Pin>> loader) {
        recycleAdapter.bindData(null);

    }
}

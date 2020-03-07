package com.paulo.mybeer.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.paulo.mybeer.R;
import com.paulo.mybeer.model.data.adapter.ItemBeerAdapterFavorite;
import com.paulo.mybeer.model.data.presenter.FavoriteActivityPresenter;
import com.paulo.mybeer.model.data.presenter.MVP;

public class FavoriteActivity extends AppCompatActivity implements MVP.FavoriteActivityView {

    private RecyclerView recyclerView;
    private ItemBeerAdapterFavorite mAdaper;
    private TextView warning;
    private FavoriteActivityPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        setUp();
    }
    private void setUp() {
        warning = findViewById(R.id.warning);
        recyclerView = findViewById(R.id.recycler);
        presenter = new FavoriteActivityPresenter(this);
        initAdapter();
    }
    private void initAdapter() {
        mAdaper = presenter.initAdapterOrShowWarning();
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(mAdaper);
    }
    @Override
    public Context getContext() {
        return this;
    }
    @Override
    public void updateList() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initAdapter();
            }
        });
    }
    @Override
    public void showWarning() {
        warning.setVisibility(View.VISIBLE);
    }
}

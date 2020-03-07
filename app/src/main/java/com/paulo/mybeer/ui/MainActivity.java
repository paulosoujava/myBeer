package com.paulo.mybeer.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.paulo.mybeer.R;
import com.paulo.mybeer.model.data.adapter.ItemBeerAdapterMain;
import com.paulo.mybeer.model.data.presenter.MVP;
import com.paulo.mybeer.model.data.presenter.MainActivityPresenter;


public class MainActivity extends AppCompatActivity implements MVP.MainActivityView {


    private RecyclerView recyclerView;
    private ItemBeerAdapterMain mAdaper;
    private FloatingActionButton fab;
    private FloatingActionButton back;
    private FrameLayout progress;
    private FrameLayout container_fab;
    private TextView warning;
    private  MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();
    }
    private void setUp() {
        fab = findViewById(R.id.fab);
        back = findViewById(R.id.back);
        container_fab = findViewById(R.id.container_fab);
        progress = findViewById(R.id.progress);
        warning = findViewById(R.id.warning);
        recyclerView = findViewById(R.id.recycler);
        presenter = new MainActivityPresenter(this);
        clickFab();
        initAdapter();
    }
    private void clickFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        startActivity(new Intent(getContext(), FavoriteActivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickBack();
            }
        });
    }
    public void initAdapter() {
        mAdaper = new ItemBeerAdapterMain(presenter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(mAdaper);
        hideFloat(recyclerView);
    }
    private void hideFloat(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    container_fab.setVisibility(View.GONE);
                }
                if (dy < 0)
                    container_fab.setVisibility(View.VISIBLE);
            }
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }
    @Override
    public Context getContext() {
        return this;
    }
    @Override
    public void updateList() {
        progress.setVisibility(View.GONE);
        initAdapter();
    }
    @Override
    public void showError() {
        progress.setVisibility(View.GONE);
        warning.setVisibility(View.VISIBLE);
    }
    @Override
    public void showBack( final boolean isVisible ) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if( isVisible ) {
                    back.show();
                    container_fab.setVisibility(View.VISIBLE);
                }else {
                    back.hide();
                }
            }
        });

    }
}

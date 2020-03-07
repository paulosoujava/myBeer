package com.paulo.mybeer.model.data.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.paulo.mybeer.model.data.adapter.ItemBeerAdapterFavorite;
import com.paulo.mybeer.model.data.entity.Beer;
import com.paulo.mybeer.ui.DetailActivity;

import java.util.List;

public class FavoriteActivityPresenter implements MVP.FavoriteActivityPresenterIpl {

    private MVP.FavoriteActivityView view;
    private Context context;


    public FavoriteActivityPresenter(MVP.FavoriteActivityView view) {
        this.view = view;
        context = view.getContext();
    }
    public List<Beer> getList() {
        return Beer.getBeers(view.getContext());
    }
    public ItemBeerAdapterFavorite initAdapterOrShowWarning(){
        if(getList().isEmpty())
            view.showWarning();
        return new ItemBeerAdapterFavorite( this );
    }
    public Context getContext() {
        return context;
    }

    @Override
    public void clickItemAdpaterFavoriteBeer(Beer beer) {
        Intent it = new Intent(getContext(), DetailActivity.class);
        it.putExtra(Beer.KEY_BEER, beer);
        getContext().startActivity( it );
    }
    @Override
    public boolean clickItemAdpaterFavoriteRemoveBeer(Beer beer) {
        List<Beer> list = Beer.getBeers(view.getContext());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == beer.getId()) {
                list.remove(i);
                Beer.save(view.getContext(), list);
                view.updateList();
                return true;
            }
        }
        return  false;
    }
}

package com.paulo.mybeer.model.data.presenter;

import android.content.Context;
import android.content.Intent;

import com.paulo.mybeer.model.data.entity.Beer;

import java.util.List;

public  interface MVP {

    //****************************************
    //VIEW's
    //****************************************
    interface MainActivityView{
        Context getContext();
        void updateList();
        void showError();
        void showBack(boolean isVisible );
    }
    interface FavoriteActivityView{
        Context getContext();
        void updateList();
        void showWarning();
    }
    interface  DetailActivityView{
        void updateFavorite( boolean isUpdate);
        Context getContext();
    }
    //****************************************
    //PRESENTER's
    //****************************************
    interface MainActivityPresenterIpl{
        void clickItemAdpaterBeer(Beer beer, boolean isFavorited);
        void clickItemAdpaterFavoriteBeer(Beer beer);

    }
    interface FavoriteActivityPresenterIpl {
        void clickItemAdpaterFavoriteBeer(Beer beer);
        boolean clickItemAdpaterFavoriteRemoveBeer(Beer beer);
    }
    interface DetailActivityPresenterIpl{
        Beer hasData(Intent intent);
    }
}

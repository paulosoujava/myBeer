package com.paulo.mybeer.model.data.presenter;

import android.content.Intent;
import com.paulo.mybeer.R;
import com.paulo.mybeer.model.data.entity.Beer;
import com.paulo.mybeer.utils.Utils;


public class DetailActivityPresenter implements MVP.DetailActivityPresenterIpl {

    private MVP.DetailActivityView view;
    private  Beer beer;

    public DetailActivityPresenter(MVP.DetailActivityView view) {
        this.view = view;
    }

    @Override
    public Beer hasData(Intent intent) {

        if (intent == null) return null;
        beer = intent.getParcelableExtra(Beer.KEY_BEER);
        updateUI(intent.getBooleanExtra(Beer.FAVORITE, false));
        return beer;

    }
    public void saveOrDeleteInFavorite() {
        boolean ok =Utils.saveOrDeleteInFavorite(view.getContext(),  beer);
        Utils.toast(view.getContext(), ok ? view.getContext().getString(R.string.favorited) : view.getContext().getString(R.string.removed));
        updateUI( ok );
    }
    private void updateUI(boolean isUpdate) {
        view.updateFavorite(isUpdate);
    }
}

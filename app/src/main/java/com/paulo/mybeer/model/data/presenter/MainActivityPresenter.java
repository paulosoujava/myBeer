package com.paulo.mybeer.model.data.presenter;

import android.content.Context;
import android.content.Intent;
import com.paulo.mybeer.R;
import com.paulo.mybeer.model.api.Api;
import com.paulo.mybeer.model.api.GetDataService;
import com.paulo.mybeer.model.data.entity.Beer;
import com.paulo.mybeer.ui.DetailActivity;
import com.paulo.mybeer.utils.Utils;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter implements MVP.MainActivityPresenterIpl {

    private static List<Beer> list;
    private static MVP.MainActivityView view;
    private Context context;
    private static int SHOW_TEN_PER_PAGE = 11;
    private int initial_page = 1;


    public MainActivityPresenter(MVP.MainActivityView view) {
        this.view = view;
        context = view.getContext();
        getFromApi(initial_page);
    }
    public void updateFab(boolean isUp) {
        view.showBack(isUp);
    }
    public static void getFromApi(int page) {
        GetDataService service = Api.getRetrofitInstance().create(GetDataService.class);
        Call<List<Beer>> call = service.getPunkBeerList(page, SHOW_TEN_PER_PAGE);
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                list = response.body();
                view.updateList();
            }
            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                view.updateList();
                view.showError();
            }
        });

    }
    public Context getContext() {
        return context;
    }

    @Override
    public void clickItemAdpaterBeer(Beer beer, boolean isFavored) {
        Intent it = new Intent(getContext(), DetailActivity.class);
        it.putExtra(Beer.KEY_BEER, beer);
        it.putExtra(Beer.FAVORITE, isFavored);
        getContext().startActivity(it);
    }
    @Override
    public void clickItemAdpaterFavoriteBeer(Beer beer) {
        Beer.saveBeer(view.getContext(), beer);
        Utils.toast(view.getContext(), view.getContext().getString(R.string.favorited));
    }
    public List<Beer> getList() {
        return list;
    }

    public int getTenPerPage() {
        return SHOW_TEN_PER_PAGE;
    }

    public int getInitial_page() {
        return initial_page;
    }
    public void setInitial_page(int initial_page) {
        this.initial_page = initial_page;
    }

    public void clickBack() {
        --initial_page;
        if (initial_page == 1) {
            getFromApi(initial_page);
            view.showBack(false);
        } else
            getFromApi(initial_page);

        Utils.toast(context, context.getString(R.string.page) + initial_page);


    }
}

package web.com.shoppingcart.ui.home.fragment;

import javax.inject.Inject;

public class HomePresenter implements HomeContract.Presenter{
    HomeContract.View view;

    @Inject
    HomePresenter(){

    }

    @Override
    public void takeView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }
}

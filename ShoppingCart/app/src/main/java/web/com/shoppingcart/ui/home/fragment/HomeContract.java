package web.com.shoppingcart.ui.home.fragment;

public interface HomeContract {
    interface View{
    }

    interface Presenter{
        public void takeView(HomeContract.View view);
        public void dropView();
    }
}

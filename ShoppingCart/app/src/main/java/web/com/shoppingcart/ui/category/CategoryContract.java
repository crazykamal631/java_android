package web.com.shoppingcart.ui.category;

import java.util.List;

import web.com.shoppingcart.db.Category;

public interface CategoryContract {
    interface View{
        public void updateList(List<Category> tempList);
    }

    interface Presenter{
        public void takeView(CategoryContract.View categoryFragmentView);
        public void dropView();
        public void loadData();
    }

    public interface RecyclerItemClickListener {
        void onItemClickListener(int position);
    }
}

package web.com.shoppingcart.ui.product;

import java.util.List;

import web.com.shoppingcart.db.ProductFullInfo;

public interface ProductContract {
    interface View{
        //public void updateList(List<Product> tempList);
        public void updateListCustom(List<ProductFullInfo> tempList);
    }

    interface Presenter{
        public void takeView(ProductContract.View view);
        public void dropView();
        public void loadData(int categoryId);
        public void sortData(List<ProductFullInfo> productList, int menuItemId);
    }

    public interface GridRecyclerItemClickListener {
        void onItemClickListener(ProductFullInfo productFullInfo);
    }
}

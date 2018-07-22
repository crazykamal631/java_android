package web.com.shoppingcart.ui.detail;

import java.util.List;

import web.com.shoppingcart.db.ProductFullInfo;
import web.com.shoppingcart.db.Variant;

public interface DetailContract {
    interface View{
        public void updateList(List<Variant> tempList);
        public void selectedVariant(Variant variant);

        void setDetails(String sharedInfo, String name);

        void updateSizeInfo(String sizeCount, String sizeString);

        void updateColorInfo(String colorCount);
    }

    interface Presenter{
        public void takeView(DetailContract.View categoryFragmentView);
        public void dropView();

        public void getVariationsForProduct(int productId);
        public void selectedVariant(Variant variant);

        void setDetails(ProductFullInfo productFullInfo);
    }

    public interface RecyclerItemClickListener {
        void onItemClickListener(int position);
    }
}

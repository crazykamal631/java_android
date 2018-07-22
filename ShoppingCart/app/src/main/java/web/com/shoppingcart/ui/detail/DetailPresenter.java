package web.com.shoppingcart.ui.detail;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import web.com.shoppingcart.db.ProductFullInfo;
import web.com.shoppingcart.db.Variant;
import web.com.shoppingcart.db.VariantDao;

public class DetailPresenter implements DetailContract.Presenter{

    private static final String TAG = "DetailPresenter_Cart741";

    @Inject
    VariantDao variantDao;

    private DetailContract.View detailFragmentView;
    private CompositeDisposable disposeBag;

    @Inject
    public DetailPresenter(){
        disposeBag = new CompositeDisposable();
    }

    @Override
    public void takeView(DetailContract.View categoryFragmentView) {
        this.detailFragmentView = categoryFragmentView;
    }

    @Override
    public void dropView() {
        detailFragmentView = null;
        disposeBag.clear();
    }

    @Override
    public void getVariationsForProduct(int productId) {
        getData(productId);
    }

    @Override
    public void selectedVariant(Variant variant) {
        detailFragmentView.selectedVariant(variant);
    }

    @Override
    public void setDetails(ProductFullInfo productFullInfo) {
        int sharedCount = productFullInfo.getShares();
        int viewedCount = productFullInfo.getViewCount();
        int orderCount = productFullInfo.getOrderCount();
        String name = productFullInfo.getName();
        String sharedInfo = sharedCount + " ratings"
                + " & " + viewedCount + " reviews "
                + " & " + orderCount + " orders";
        detailFragmentView.setDetails(sharedInfo, name);
    }

    private void getData(int productId){
        Disposable disposable = variantDao
                .findVariantsForProduct(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.subscribe(tempList -> detailFragmentView.updateList(tempList));
                //.subscribe(tempList -> processList(tempList));
                .subscribe(tempList -> processList(tempList));
        disposeBag.add(disposable);
    }

    private void processList(List<Variant> tempList){
        detailFragmentView.updateList(tempList);
        List<Integer> sizeList = new ArrayList<>();
        for(Variant variant : tempList){
            if(variant.size > 0) {
                sizeList.add(variant.size);
            }
        }
        String colorCount = "Color("+tempList.size()+")";
        String sizeCount = "Size("+sizeList.size()+")";
        String sizeString = android.text.TextUtils.join(",", sizeList);
        detailFragmentView.updateSizeInfo(sizeCount, sizeString);
        detailFragmentView.updateColorInfo(colorCount);
    }
}

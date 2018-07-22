package web.com.shoppingcart.ui.product;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import web.com.shoppingcart.R;
import web.com.shoppingcart.db.Product;
import web.com.shoppingcart.db.ProductDao;
import web.com.shoppingcart.db.ProductFullInfo;

public class ProductPresenter implements ProductContract.Presenter{

    private static final String TAG = "ProductPresenter_Cart741";

    private List<Product> listOfProducts;

    @Inject
    ProductDao productDao;

    private ProductContract.View categoryFragmentView;
    private CompositeDisposable disposeBag;

    @Inject
    public ProductPresenter(){
        disposeBag = new CompositeDisposable();
    }

    @Override
    public void takeView(ProductContract.View categoryFragmentView) {
        this.categoryFragmentView = categoryFragmentView;
    }

    @Override
    public void dropView() {
        categoryFragmentView = null;
        disposeBag.clear();
    }

    @Override
    public void loadData(int categoryId) {
        getData(categoryId);
    }

    @Override
    public void sortData(List<ProductFullInfo> productList, int menuItemId) {
        switch (menuItemId) {
            case R.id.sort_most_viewed:
                Log.d(TAG,"--sort data by view--");
                productList.sort(ProductFullInfo.viewCountSort);
                break;
            case R.id.sort_most_ordered:
                Log.d(TAG,"--sort data by ordered--");
                productList.sort(ProductFullInfo.orderCountSort);
                break;
            case R.id.sort_most_shared:
                Log.d(TAG,"--sort data by shared--");
                productList.sort(ProductFullInfo.sharesSort);
                break;
        }
        categoryFragmentView.updateListCustom(productList);
    }

    private void getData(int catId){
        /*Disposable disposable = productDao
                .findProductsForCategory(catId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listOfProducts ->categoryFragmentView.updateList(listOfProducts));
        disposeBag.add(disposable);*/

        Disposable disposable1 = productDao
                .getAllProductsWithJoinsMultipleTable(catId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listOfProducts ->categoryFragmentView.updateListCustom(listOfProducts));
        disposeBag.add(disposable1);

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = productDao.getAllProductsWithJoinsMultipleTable(catId);
                Log.d(TAG, "cursor size-->"+cursor.getCount());
                String st = DatabaseUtils.dumpCursorToString(cursor);
                Log.d(TAG, "cursor dump-->"+st);
            }
        }).start();*/
    }
}

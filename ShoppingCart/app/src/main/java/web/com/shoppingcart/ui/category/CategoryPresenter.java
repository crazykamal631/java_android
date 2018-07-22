package web.com.shoppingcart.ui.category;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import web.com.shoppingcart.db.CategoryDao;

public class CategoryPresenter implements CategoryContract.Presenter{

    private static final String TAG = "CategoryPresenter_Cart741";

    @Inject
    CategoryDao categoryDao;

    private CategoryContract.View categoryFragmentView;
    private CompositeDisposable disposeBag;

    @Inject
    public CategoryPresenter(){
        disposeBag = new CompositeDisposable();
    }

    @Override
    public void takeView(CategoryContract.View categoryFragmentView) {
        this.categoryFragmentView = categoryFragmentView;
    }

    @Override
    public void dropView() {
        categoryFragmentView = null;
        disposeBag.clear();
    }

    @Override
    public void loadData() {
        getData();
    }

    private void getData(){
        Disposable disposable = categoryDao
                .getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tempList ->categoryFragmentView.updateList(tempList));
        disposeBag.add(disposable);
    }
}

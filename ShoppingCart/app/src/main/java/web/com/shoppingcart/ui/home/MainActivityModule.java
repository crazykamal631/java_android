package web.com.shoppingcart.ui.home;

import java.util.ArrayList;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import web.com.shoppingcart.db.Category;
import web.com.shoppingcart.db.ProductFullInfo;
import web.com.shoppingcart.db.Variant;
import web.com.shoppingcart.di.scopes.ActivityScope;
import web.com.shoppingcart.di.scopes.FragmentScope;
import web.com.shoppingcart.ui.category.CategoryAdapter;
import web.com.shoppingcart.ui.category.CategoryContract;
import web.com.shoppingcart.ui.category.CategoryFragment;
import web.com.shoppingcart.ui.category.CategoryPresenter;
import web.com.shoppingcart.ui.detail.DetailContract;
import web.com.shoppingcart.ui.detail.DetailFragment;
import web.com.shoppingcart.ui.detail.DetailPresenter;
import web.com.shoppingcart.ui.detail.VariationsAdapter;
import web.com.shoppingcart.ui.home.fragment.HomeContract;
import web.com.shoppingcart.ui.home.fragment.HomeFragment;
import web.com.shoppingcart.ui.home.fragment.HomePresenter;
import web.com.shoppingcart.ui.home.fragment.ViewPagerAdapter;
import web.com.shoppingcart.ui.product.ProductContract;
import web.com.shoppingcart.ui.product.ProductFragment;
import web.com.shoppingcart.ui.product.ProductGridAdapter;
import web.com.shoppingcart.ui.product.ProductPresenter;

@Module
public abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract HomeFragment provideHomeFragment();

    @ActivityScope
    @Binds
    abstract HomeContract.Presenter provideHomePresenter(HomePresenter homePresenter);

    @FragmentScope
    @ContributesAndroidInjector
    abstract CategoryFragment provideCategoryFragment();

    @ActivityScope
    @Binds
    abstract CategoryContract.Presenter provideCategoryPresenter(CategoryPresenter categoryFragmentView);

    @ActivityScope
    @Provides
    static CategoryAdapter provideCategoryAdapter() {
        return new CategoryAdapter(new ArrayList<Category>());
    }

    @ActivityScope
    @Provides
    static ViewPagerAdapter provideViewPagerAdapter() {
        return new ViewPagerAdapter();
    }

    @FragmentScope
    @ContributesAndroidInjector
    abstract ProductFragment provideProductFragment();

    @ActivityScope
    @Binds
    abstract ProductContract.Presenter provideProductPresenter(ProductPresenter productPresenter);

    @ActivityScope
    @Provides
    static ProductGridAdapter provideProductGridAdapter() {
        return new ProductGridAdapter(new ArrayList<ProductFullInfo>());
    }

    @FragmentScope
    @ContributesAndroidInjector
    abstract DetailFragment provideDetailFragment();

    @ActivityScope
    @Binds
    abstract DetailContract.Presenter provideDetailPresenter(DetailPresenter detailPresenter);

    @ActivityScope
    @Provides
    static VariationsAdapter provideVariationsAdapter() {
        return new VariationsAdapter(new ArrayList<Variant>());
    }
}

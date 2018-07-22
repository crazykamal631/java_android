package web.com.shoppingcart;

import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import retrofit2.Call;
import retrofit2.Callback;
import web.com.shoppingcart.db.Category;
import web.com.shoppingcart.db.CategoryDao;
import web.com.shoppingcart.db.Product;
import web.com.shoppingcart.db.ProductDao;
import web.com.shoppingcart.db.RankingDataOrder;
import web.com.shoppingcart.db.RankingDataOrderDao;
import web.com.shoppingcart.db.RankingDataShare;
import web.com.shoppingcart.db.RankingDataShareDao;
import web.com.shoppingcart.db.RankingDataView;
import web.com.shoppingcart.db.RankingDataViewDao;
import web.com.shoppingcart.db.Variant;
import web.com.shoppingcart.db.VariantDao;
import web.com.shoppingcart.di.components.AppComponent;
import web.com.shoppingcart.di.components.DaggerAppComponent;
import web.com.shoppingcart.server.api.APIInterfaceWeatherService;
import web.com.shoppingcart.server.pojo.ProductCategory;
import web.com.shoppingcart.server.pojo.ProductVariant;
import web.com.shoppingcart.server.pojo.Product_;
import web.com.shoppingcart.server.pojo.Products;
import web.com.shoppingcart.server.pojo.Ranking;
import web.com.shoppingcart.server.pojo.Response;

public class InitApplication extends DaggerApplication {
    private static final String TAG = "InitApplication_Cart741";

    @Inject
    APIInterfaceWeatherService apiInterfaceWeatherService;

    @Inject
    CategoryDao categoryDao;

    @Inject
    ProductDao productDao;

    @Inject
    VariantDao variantDao;

    @Inject
    RankingDataOrderDao rankingDataOrderDao;

    @Inject
    RankingDataShareDao rankingDataShareDao;

    @Inject
    RankingDataViewDao rankingDataViewDao;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate ");

        new Thread(new Runnable() {
            @Override
            public void run() {
                int noOfRows = categoryDao.getNumberOfRows();
                Log.d(TAG, "noOfRows->" + noOfRows);
                if (noOfRows > 0) {
                    Log.d(TAG, "data is already inserted->" + noOfRows);
                } else {
                    fetchData();
                }
            }
        }).start();
    }

    private void fetchData() {
        Call<Response> call = apiInterfaceWeatherService.doGetWeatherInfoFiveDays();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response response1 = response.body();
                final List<ProductCategory> categoryList = response1.getCategories();
                List<Ranking> rankingList = response1.getRankings();
                Log.d(TAG, "categoryList-->" + categoryList.size());
                for (ProductCategory category : categoryList) {
                    Log.d(TAG, "category-->" + category.getName());
                }

                for (Ranking ranking : rankingList) {
                    Log.d(TAG, "Ranking-->" + ranking.getRanking());
                    List<Product_> productList = ranking.getProducts();
                    for (Product_ product : productList) {
                        Log.d(TAG, "--------------product has-->" + product.getId());
                        Log.d(TAG, "product getOrderCount-->" + product.getOrderCount());
                        Log.d(TAG, "product getShares-->" + product.getShares());
                        Log.d(TAG, "product getViewCount-->" + product.getViewCount());
                    }
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        insertData(categoryList);
                        insertRankingData(rankingList);
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    private void insertData(List<ProductCategory> categoryList) {
        for (ProductCategory productCategory : categoryList) {
            int categoryId = productCategory.getId();
            String name = productCategory.getName();
            Log.d(TAG, "inserting -categoryId->" + categoryId + "<--name-->" + name);
            categoryDao.insert(new Category(categoryId, name));
            List<Products> productsList = productCategory.getProducts();
            if (productsList != null && productsList.size() > 0) {
                for (Products product : productsList) {
                    int productId = product.getId();
                    productDao.insert(new Product(productId,
                            product.getName(), product.getDateAdded(), categoryId));
                    List<ProductVariant> productVariantList = product.getVariants();
                    if (productVariantList != null && productVariantList.size() > 0) {
                        for (ProductVariant variant : productVariantList) {
                            if (variant.getSize() == null) {
                                variantDao.insert(new Variant(variant.getId(), variant.getColor(),
                                        variant.getPrice(), productId));
                            } else {
                                variantDao.insert(new Variant(variant.getId(), variant.getColor(),
                                        variant.getSize(), variant.getPrice(), productId));
                            }
                        }
                    } else {
                        Log.d(TAG, "----product variant list is 0-----");
                    }
                }
            } else {
                Log.d(TAG, "----product list is 0-----");
            }
        }

        Log.d(TAG, "data inserted successfully");
    }

    private void insertRankingData(List<Ranking> rankingList) {
        for (Ranking ranking : rankingList) {
            Log.d(TAG, "Ranking-->" + ranking.getRanking());
            List<Product_> productList = ranking.getProducts();
            int orderCount = 0;
            int viewCount = 0;
            int shares = 0;
            for (Product_ product : productList) {
                Log.d(TAG, "--------------product has-->" + product.getId());
                Log.d(TAG, "product getOrderCount-->" + product.getOrderCount());
                Log.d(TAG, "product getShares-->" + product.getShares());
                Log.d(TAG, "product getViewCount-->" + product.getViewCount());

                orderCount = 0;
                viewCount = 0;
                shares = 0;
                if (product.getOrderCount() != null) {
                    orderCount = product.getOrderCount();
                    rankingDataOrderDao.insert(new RankingDataOrder(product.getId(), ranking.getRanking(), orderCount));
                }

                if (product.getViewCount() != null) {
                    viewCount = product.getViewCount();
                    rankingDataViewDao.insert(new RankingDataView(product.getId(), ranking.getRanking(), viewCount));
                }

                if (product.getShares() != null) {
                    shares = product.getShares();
                    rankingDataShareDao.insert(new RankingDataShare(product.getId(), ranking.getRanking(), shares));
                }
               /* rankingDataDao.insert(new RankingData(product.getId(),
                        ranking.getRanking(), viewCount, orderCount,
                        shares));*/
            }
        }
    }

    public static InitApplication getApplication(Context context) {
        return (InitApplication) context.getApplicationContext();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();
        appComponent.inject(this);
        Log.e(TAG, "applicationInjector ");
        return appComponent;
    }
}

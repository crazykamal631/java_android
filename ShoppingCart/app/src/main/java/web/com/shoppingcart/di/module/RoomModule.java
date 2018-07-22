package web.com.shoppingcart.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import web.com.shoppingcart.db.CartDb;
import web.com.shoppingcart.db.CategoryDao;
import web.com.shoppingcart.db.ProductDao;
import web.com.shoppingcart.db.RankingDataOrderDao;
import web.com.shoppingcart.db.RankingDataShareDao;
import web.com.shoppingcart.db.RankingDataViewDao;
import web.com.shoppingcart.db.VariantDao;
import web.com.shoppingcart.di.scopes.ApplicationScope;

@Module
public class RoomModule {
    //private CartDb cartDb;
    private static final String DB_NAME = "cartDatabase.db";

   /* public RoomModule(Context context) {
        this.cartDb = create(context);
    }*/

    //@Binds
    //abstract Context bindContext(Application application);

    /*@ApplicationScope
    @Provides
    CartDb provideDb() {
        this.cartDb = create(context);
        return cartDb;
    }*/

    @ApplicationScope
    @Provides
    ProductDao providesProductDao(CartDb demoDatabase) {
        return demoDatabase.getProductDao();
    }

    @ApplicationScope
    @Provides
    CategoryDao providesCategoryDao(CartDb demoDatabase) {
        return demoDatabase.getCategoryDao();
    }

    @ApplicationScope
    @Provides
    VariantDao providesVariantDao(CartDb demoDatabase) {
        return demoDatabase.getVariantDao();
    }

    @ApplicationScope
    @Provides
    RankingDataViewDao providesRankingDataViewDao(CartDb demoDatabase) {
        return demoDatabase.getRankingDataViewDao();
    }

    @ApplicationScope
    @Provides
    RankingDataShareDao providesRankingDataShareDao(CartDb demoDatabase) {
        return demoDatabase.getRankingDataShareDao();
    }

    @ApplicationScope
    @Provides
    RankingDataOrderDao providesRankingDataOrderDao(CartDb demoDatabase) {
        return demoDatabase.getRankingDataOrderDao();
    }

    @ApplicationScope
    @Provides
    @Inject
    CartDb create(final Context context) {
        return Room.databaseBuilder(
                context,
                CartDb.class,
                DB_NAME).build();
    }
}

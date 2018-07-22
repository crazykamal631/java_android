package web.com.shoppingcart.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Category.class, Product.class,
        Variant.class,
        RankingDataView.class, RankingDataOrder.class,
        RankingDataShare.class}, version = 1)
public abstract class CartDb extends RoomDatabase {
    private static final String DB_NAME = "cartDatabase.db";
    //private static volatile CartDb instance;

    /*static synchronized CartDb getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }*/

   /* private static CartDb create(final Context context) {
        return Room.databaseBuilder(
                context,
                CartDb.class,
                DB_NAME).build();
    }*/

    public abstract CategoryDao getCategoryDao();

    public abstract ProductDao getProductDao();

    public abstract VariantDao getVariantDao();

    //public abstract RankingDataDao getRankingDataDao();

    public abstract RankingDataOrderDao getRankingDataOrderDao();

    public abstract RankingDataShareDao getRankingDataShareDao();

    public abstract RankingDataViewDao getRankingDataViewDao();
}

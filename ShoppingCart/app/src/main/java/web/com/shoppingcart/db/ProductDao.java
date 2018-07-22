package web.com.shoppingcart.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM Product")
    List<Product> getAllProducts();

    @Insert
    void insert(Product... products);

    @Update
    void update(Product... products);

    @Delete
    void delete(Product... products);

//    @Query("SELECT * FROM Product WHERE categoryId=:categoryId")
//    List<Product> findProductsForCategory(final int categoryId);

    @Query("SELECT * FROM Product WHERE categoryId=:categoryId")
    Flowable<List<Product>> findProductsForCategory(final int categoryId);

    //@Query("SELECT Product.*,RankingData.name,RankingData.productId, RankingData.orderCount, RankingData.viewCount, RankingData.shares  FROM Product LEFT OUTER JOIN RankingData ON Product.id = RankingData.productId WHERE categoryId=:categoryId ")
    //Cursor getAllProductsWithJoins(final int categoryId);

    @Query("SELECT Product.*, " +
            "RankingDataShare.shares, RankingDataOrder.orderCount, RankingDataView.viewCount  " +
            "FROM Product" +
            " LEFT OUTER JOIN RankingDataShare ON Product.id = RankingDataShare.productId " +
            " LEFT OUTER JOIN RankingDataOrder ON Product.id = RankingDataOrder.productId " +
            " LEFT OUTER JOIN RankingDataView ON Product.id = RankingDataView.productId " +
            "WHERE categoryId=:categoryId ")
    Flowable<List<ProductFullInfo>> getAllProductsWithJoinsMultipleTable(final int categoryId);
    //Cursor getAllProductsWithJoinsMultipleTable(final int categoryId);

    /*static class ProductFullInfo {
        public int id;
        public String name;
        public String dateAdded;
        public int categoryId;
        public int orderCount;
        public int viewCount;
        public int shares;
    }*/
}

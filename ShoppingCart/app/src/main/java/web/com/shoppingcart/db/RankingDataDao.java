package web.com.shoppingcart.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface RankingDataDao {

    //@Query("SELECT * FROM Category")
    //List<Category> getAllCategories();

    @Query("SELECT * FROM RankingData")
    Flowable<List<RankingData>> getAllRanking();

    @Insert
    void insert(RankingData... rankingData);

    @Update
    void update(RankingData... rankingData);

    @Delete
    void delete(RankingData... rankingData);

    //@Query("SELECT * FROM Product WHERE categoryId=:categoryId")
    //List<Product> findProductsForCategory(final int categoryId);
}

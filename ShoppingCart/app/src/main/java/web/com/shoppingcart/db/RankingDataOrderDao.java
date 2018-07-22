package web.com.shoppingcart.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface RankingDataOrderDao {

    @Query("SELECT * FROM RankingDataOrder")
    Flowable<List<RankingDataOrder>> getAllRankingDataOrder();

    @Insert
    void insert(RankingDataOrder... rankingDataOrder);

    @Update
    void update(RankingDataOrder... rankingDataOrder);

    @Delete
    void delete(RankingDataOrder... rankingDataOrder);

}

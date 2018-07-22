package web.com.shoppingcart.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface RankingDataShareDao {

    @Query("SELECT * FROM RankingDataShare")
    Flowable<List<RankingDataShare>> getAllShareRanking();

    @Insert
    void insert(RankingDataShare... rankingDataShare);

    @Update
    void update(RankingDataShare... rankingDataShare);

    @Delete
    void delete(RankingDataShare... rankingDataShare);
}

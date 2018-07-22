package web.com.shoppingcart.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface RankingDataViewDao {

    @Query("SELECT * FROM RankingDataView")
    Flowable<List<RankingDataView>> getAllRankingDataView();

    @Insert
    void insert(RankingDataView... rankingDataView);

    @Update
    void update(RankingDataView... rankingDataView);

    @Delete
    void delete(RankingDataView... rankingDataView);

}

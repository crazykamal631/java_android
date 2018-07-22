package web.com.shoppingcart.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface VariantDao {

    @Query("SELECT * FROM Variant")
    List<Variant> getAllVariants();

    @Insert
    void insert(Variant... variants);

    @Update
    void update(Variant... variants);

    @Delete
    void delete(Variant... variants);

    @Query("SELECT * FROM Variant WHERE productId=:productId")
    Flowable<List<Variant>> findVariantsForProduct(final int productId);
}

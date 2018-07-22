package web.com.shoppingcart.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CategoryDao {

    @Query("SELECT COUNT(id) FROM Category")
    int getNumberOfRows();

    @Query("SELECT * FROM Category")
    Flowable<List<Category>> getAllCategories();

    @Insert
    void insert(Category... categories);

    @Update
    void update(Category... categories);

    @Delete
    void delete(Category... categories);

    //@Query("SELECT * FROM Product WHERE categoryId=:categoryId")
    //List<Product> findProductsForCategory(final int categoryId);
}

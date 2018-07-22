package web.com.shoppingcart.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Category.class
        , parentColumns = "id",
        childColumns = "categoryId",
        onDelete = CASCADE))
public class Product {

    @PrimaryKey
    public final int id;
    public final String name;
    public final String dateAdded;
    public final int categoryId;

    public Product(int id, String name, String dateAdded, int categoryId) {
        this.id = id;
        this.name = name;
        this.dateAdded = dateAdded;
        this.categoryId = categoryId;
    }
}

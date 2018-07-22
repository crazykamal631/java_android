package web.com.shoppingcart.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Product.class
        , parentColumns = "id",
        childColumns = "productId",
        onDelete = CASCADE))
public class Variant {

    @PrimaryKey
    public final int id;
    public final String color;
    public int size;
    public final int price;
    public final int productId;

    public Variant(int id, String color, int size, int price, int productId) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.price = price;
        this.productId = productId;
    }

    @Ignore
    public Variant(int id, String color, int price, int productId) {
        this.id = id;
        this.color = color;
        this.price = price;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "color->"+color+"<-size->"+size+"<-price->"+price;
    }
}

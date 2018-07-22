package web.com.shoppingcart.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey
    public final int id;
    public final String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

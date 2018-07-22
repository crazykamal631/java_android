package web.com.shoppingcart.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class RankingDataOrder {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int productId;
    public String name;
    public int orderCount;

    public RankingDataOrder(int id, String name, int orderCount) {
        this.productId = id;
        this.name = name;
        this.orderCount = orderCount;
    }
}

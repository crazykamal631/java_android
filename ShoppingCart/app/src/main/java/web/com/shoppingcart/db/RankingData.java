package web.com.shoppingcart.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class RankingData {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int productId;
    public String name;
    public int viewCount;
    public int orderCount;
    public int shares;

    public RankingData(int id, String name, int viewCount, int orderCount, int shares) {
        this.productId = id;
        this.name = name;
        this.viewCount = viewCount;
        this.orderCount = orderCount;
        this.shares = shares;
    }
}

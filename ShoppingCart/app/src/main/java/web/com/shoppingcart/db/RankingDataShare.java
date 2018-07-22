package web.com.shoppingcart.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class RankingDataShare {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int productId;
    public String name;
    public int shares;

    public RankingDataShare(int id, String name, int shares) {
        this.productId = id;
        this.name = name;
        this.shares = shares;
    }
}

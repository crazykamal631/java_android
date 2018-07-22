package web.com.shoppingcart.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class RankingDataView {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int productId;
    public String name;
    public int viewCount;

    public RankingDataView(int id, String name, int viewCount) {
        this.productId = id;
        this.name = name;
        this.viewCount = viewCount;
    }
}

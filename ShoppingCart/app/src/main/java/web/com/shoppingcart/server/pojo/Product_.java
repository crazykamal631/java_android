package web.com.shoppingcart.server.pojo;

import com.google.gson.annotations.SerializedName;

public class Product_ {

    private Integer id;

    @SerializedName("view_count")
    private Integer viewCount;

    @SerializedName("order_count")
    private Integer orderCount;

    private Integer shares;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }
}

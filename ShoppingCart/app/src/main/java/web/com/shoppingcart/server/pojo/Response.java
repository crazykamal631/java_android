package web.com.shoppingcart.server.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("categories")
    private List<ProductCategory> categories = null;
    private List<Ranking> rankings = null;

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ProductCategory> categories) {
        this.categories = categories;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }
}

package web.com.shoppingcart.server.pojo;

import java.util.List;

public class Ranking {

    private String ranking;
    private List<Product_> products = null;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public List<Product_> getProducts() {
        return products;
    }

    public void setProducts(List<Product_> products) {
        this.products = products;
    }
}

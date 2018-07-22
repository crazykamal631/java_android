package web.com.shoppingcart.server.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCategory {

    private Integer id;
    private String name;
    private List<Products> products = null;
    private List<Object> childCategories = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public List<Object> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<Object> childCategories) {
        this.childCategories = childCategories;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

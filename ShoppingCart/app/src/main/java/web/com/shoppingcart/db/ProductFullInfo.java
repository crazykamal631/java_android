package web.com.shoppingcart.db;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class ProductFullInfo implements Parcelable {
    public int id;
    public String name;
    public String dateAdded;
    public int categoryId;
    public int orderCount;
    public int viewCount;
    public int shares;

    public ProductFullInfo(int id, String name, String dateAdded, int categoryId, int orderCount, int viewCount, int shares) {
        this.id = id;
        this.name = name;
        this.dateAdded = dateAdded;
        this.categoryId = categoryId;
        this.orderCount = orderCount;
        this.viewCount = viewCount;
        this.shares = shares;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    /* Comparator for sorting the list by view count */
    public static Comparator<ProductFullInfo> viewCountSort = new Comparator<ProductFullInfo>() {

        public int compare(ProductFullInfo s1, ProductFullInfo s2) {

            int rollno1 = s1.getViewCount();
            int rollno2 = s2.getViewCount();

            /* For ascending order */
            //return rollno1 - rollno2;

            /* For descending order */
            return rollno2 - rollno1;
        }
    };

    /* Comparator for sorting the list by order count */
    public static Comparator<ProductFullInfo> orderCountSort = new Comparator<ProductFullInfo>() {

        public int compare(ProductFullInfo s1, ProductFullInfo s2) {

            int rollno1 = s1.getOrderCount();
            int rollno2 = s2.getOrderCount();

            /* For ascending order */
            //return rollno1 - rollno2;

            /* For descending order */
            return rollno2 - rollno1;
        }
    };

    /* Comparator for sorting the list by order count */
    public static Comparator<ProductFullInfo> sharesSort = new Comparator<ProductFullInfo>() {

        public int compare(ProductFullInfo s1, ProductFullInfo s2) {

            int rollno1 = s1.getShares();
            int rollno2 = s2.getShares();

            /* For ascending order */
            //return rollno1 - rollno2;

            /* For descending order */
            return rollno2 - rollno1;
        }
    };

    /* Comparator for sorting the list by order count */
    public static Comparator<ProductFullInfo> nameSort = new Comparator<ProductFullInfo>() {

        public int compare(ProductFullInfo s1, ProductFullInfo s2) {

            String name = s1.getName().toUpperCase();
            String name2 = s2.getName().toUpperCase();

            return name.compareTo(name2);
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(dateAdded);
        parcel.writeInt(categoryId);
        parcel.writeInt(orderCount);
        parcel.writeInt(viewCount);
        parcel.writeInt(shares);
    }

    private ProductFullInfo(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.dateAdded = in.readString();
        this.categoryId = in.readInt();
        this.orderCount = in.readInt();
        this.viewCount = in.readInt();
        this.shares = in.readInt();
    }

    public static final Parcelable.Creator<ProductFullInfo> CREATOR = new Parcelable.Creator<ProductFullInfo>() {

        @Override
        public ProductFullInfo createFromParcel(Parcel source) {
            return new ProductFullInfo(source);
        }

        @Override
        public ProductFullInfo[] newArray(int size) {
            return new ProductFullInfo[size];
        }
    };

    @Override
    public String toString() {
        return "id->" + id + "<-name->" + name
                + "<-dateAdded->" + dateAdded +
                "<-categoryId->"+categoryId +
                "<-orderCount->" + orderCount +
                "<-shares->" + shares
                + "<-viewCount->" +viewCount;
    }
}

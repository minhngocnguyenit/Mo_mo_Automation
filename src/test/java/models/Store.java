package models;

public class Store {

    public String id;
    public String storeAddress;
    public String storeName;
    public String merchantId;

    public Store() {    }

    public void setDefaultData() {

        this.storeName = "Store Name " + this.merchantId;
        this.storeAddress = "Store Address " + this.merchantId;
    }
}

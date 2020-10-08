package models;

public class Contract {
    public String merchantCategoryId;
    public String businessModelId;
    public String numberOfStoresId;
    public String serviceFee;

    public Contract() {}

    public Contract(String merchantCategoryId, String businessModelId, String numberOfStoresId) {
        this.merchantCategoryId = merchantCategoryId;
        this.businessModelId = businessModelId;
        this.numberOfStoresId = numberOfStoresId;
    }
}

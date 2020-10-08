package models;

public class Merchant {
    public String userName;
    public String password;
    public String userId;

    public String partnerCode;
    public String phone;
    public String businessCategoryId;
    public String businessModelId;
    public String businessRegistrationId;
    public String numberOfStoresId;
    public String companyName;
    public String companyAddress;
    public String viceGerent;
    public String openTime;
    public String closeTime;

    public String id;

    public String status;
    public String search;
    public String fromDate;
    public String toDate;
    public String storeId;
    public String transType;

    public String merchantIdList;
    public String searchType;
    public String offset;
    public String limit;
    public String lang;

    public Merchant() {}

    public void setDefaultData() {

        this.phone = this.userId;
        this.viceGerent = "Gerent Auto";
        this.companyName = "Company Name Auto";
        this.companyAddress = "Company Address Auto";
        this.businessRegistrationId = "3";
        this.businessCategoryId = "9";
        this.businessModelId = "534";
        this.numberOfStoresId = "539";
        this.openTime = "8";
        this.closeTime = "20";
    }

}

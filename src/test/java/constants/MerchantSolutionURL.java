package constants;

public interface MerchantSolutionURL {

//    M4B Onboarding Service
    String baseUriOnboarding = "http://172.16.13.82:8083";
    String getEContractPath = "/v1/e-contract";
    String loginBoardingPath = "/v1/login";
    String getRegisterInfoPath = "/v1/merchant-register-info";
    String createNewMerchantPath = "/v1/merchants";
    String existByUserIdPath = "/v1/merchants";
    String createNewStorePath = "/v1/stores";
    String updateMerchantPath = "/v1/merchants";

//    M4B Merchant Profile Service
    String baseUriMerchantProfile = "http://172.16.13.82:8084";
    String uploadImageBase64Path = "/v1/merchants";
    String getStoreFromParentIdPath = "/v1/merchants";
    String getImagePath = "/v1/documents";

//    M4B Transaction Service
    String baseUriTransaction = "http://172.16.13.82:8082";
    String getTransStatisticByDayPath = "/v1/transaction-statistic-by-day";
    String getTransStatisticByMonthPath = "/v1/transaction-statistic-by-month";
    String getTransHistoryPath = "/v1/transaction-history";

//    M4B Location Service
    String baseUriLocation = "http://172.16.13.82:8080";
    String autoCompletePlacePath = "/v1/auto-complete-place";
}
package steps.api_merchant_solution.transaction_service;

import constants.BodyStatusCode;
import constants.MerchantSolutionURL;
import constants.ResponseStatusCode;
import io.restassured.response.Response;
import models.Merchant;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;
import utilities.Log;
import utilities.RestAPI;

public class GetTransHistorySteps {

    private String createBody(Merchant merchant) {

        String body = "{\n" +
                "    \"fromDate\":\""+merchant.fromDate+"\",\n" +
                "    \"toDate\":\""+merchant.toDate+"\",\n" +
                "    \"search\":\""+merchant.search+"\",\n" +
                "    \"storeId\":\""+merchant.storeId+"\",\n" +
                "    \"merchantIdList\":[\""+merchant.merchantIdList+"\"],\n" +
                "    \"transType\":\""+merchant.transType+"\",\n" +
                "    \"status\":"+merchant.status+",\n" +
                "    \"limit\":"+merchant.limit+",\n" +
                "    \"offset\":"+merchant.offset+",\n" +
                "    \"searchType\":\""+merchant.searchType+"\",\n" +
                "    \"lang\":\""+merchant.lang+"\"\n" +
                "}";

        return  body;
    }

    public Response getTransHistory(Merchant merchant, String language){

        String body = createBody(merchant);

        String getPath = MerchantSolutionURL.getTransHistoryPath + "?language=" + language;

        Response response = RestAPI.postApi(MerchantSolutionURL.baseUriTransaction, getPath,
                "X-Request-Id","Auto", body);

        return  response;
    }

    @Step
    public void beAbleToGetTransHistory(Merchant merchant, String language) {

        Response response = getTransHistory(merchant,language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Thành công"))
                .and().body("data.transactionHistorySummary", Matchers.notNullValue())
                .and().body("data.transactionHistorySummary.totalAmount", Matchers.greaterThan(0))
                .and().body("data.transactionHistoryDetails", Matchers.notNullValue());

        Log.highlight("Done");
    }

    @Step
    public void noDataWhenGetTransHistory(Merchant merchant, String language) {

        Response response = getTransHistory(merchant,language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Thành công"))
                .and().body("data.transactionHistorySummary", Matchers.notNullValue())
                .and().body("data.transactionHistorySummary.totalAmount", Matchers.equalTo(0));

        Log.highlight("Done");
    }






}

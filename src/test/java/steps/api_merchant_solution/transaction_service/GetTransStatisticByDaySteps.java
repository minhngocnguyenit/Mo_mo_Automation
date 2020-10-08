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

public class GetTransStatisticByDaySteps {

    private String createBody(Merchant merchant) {

        String body = "{\n" +
                "    \"fromDate\":\""+merchant.fromDate+"\",\n" +
                "    \"toDate\":\""+merchant.toDate+"\",\n" +
                "    \"partnerCode\":\"" + merchant.partnerCode + "\",\n" +
                "    \"storeId\":\""+merchant.storeId+"\",\n" +
                "    \"transType\":\""+merchant.transType+"\",\n" +
                "    \"status\":"+merchant.status+",\n" +
                "    \"search\":\""+merchant.search+ "\"\n" +
                "}";

        return  body;
    }

    public Response getTransStatisticByDay(Merchant merchant, String language){

        String body = createBody(merchant);

        String getPath = MerchantSolutionURL.getTransStatisticByDayPath + "?language=" + language;

        Response response = RestAPI.postApi(MerchantSolutionURL.baseUriTransaction, getPath,
                "X-Request-Id","Auto", body);

        return  response;
    }

    @Step
    public void beAbleToGetTransStatisticByDay(Merchant merchant, String language) {

        Response response = getTransStatisticByDay(merchant,language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Thành công"))
                .and().body("data.transactionStatisticSummary", Matchers.notNullValue())
                .and().body("data.transactionStatisticDetails", Matchers.notNullValue())
                .and().body("data.transactionStatisticSummary.totalOverAmount", Matchers.greaterThan(0));

        Log.highlight("Done");
    }

    @Step
    public void noDataWhenGetTransStatisticByDay(Merchant merchant, String language) {

        Response response = getTransStatisticByDay(merchant,language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Thành công"))
                .and().body("data.transactionStatisticSummary", Matchers.notNullValue())
                .and().body("data.transactionStatisticDetails", Matchers.notNullValue())
                .and().body("data.transactionStatisticSummary.totalOverAmount", Matchers.equalTo(0));

        Log.highlight("Done");
    }
}

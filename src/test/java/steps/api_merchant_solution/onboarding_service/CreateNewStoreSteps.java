package steps.api_merchant_solution.onboarding_service;

import constants.BodyStatusCode;
import constants.MerchantSolutionURL;
import constants.ResponseStatusCode;
import io.restassured.response.Response;
import models.Store;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;
import utilities.Log;
import utilities.RestAPI;

public class CreateNewStoreSteps {

    private String createBody(Store store) {

        String body = "{\n" +
            "  \"storeAddress\": \""+store.storeAddress+"\",\n" +
           "  \"storeName\": \""+store.storeName+"\",\n" +
           "  \"id\": 0,\n" +
           "  \"merchantId\": "+store.merchantId+"\n" +
            "}";

        return  body;
    }

    public Response createNewStore(Store store, String language){

        String body = createBody(store);

        String getPath = MerchantSolutionURL.createNewStorePath + "?language=" + language;

        Response response = RestAPI.postApi(MerchantSolutionURL.baseUriOnboarding, getPath,
                "X-Request-Id","Auto", body);

        return  response;
    }

    @Step
    public void beAbleToCreateNewStore(Store store, String language) {

        Response response = createNewStore(store, language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Thành công"))
                .and().body("data.companyName", Matchers.equalTo(store.storeName))
                .and().body("data.companyAddress", Matchers.equalTo(store.storeAddress))
                .and().body("data.parentId", Matchers.equalTo(Integer.parseInt(store.merchantId)))
                .and().body("data.isActive", Matchers.equalTo(1));

        Log.highlight("Done");
    }

    @Step
    public void notAbleToCreateNewStore(Store store, String language) {

        Response response = createNewStore(store, language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.NOT_FOUND)
                .and().body("status", Matchers.equalTo(BodyStatusCode.NOT_FOUND))
                .and().body("data", Matchers.isEmptyOrNullString());

        Log.highlight("Done");
    }
}

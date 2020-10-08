package steps.api_merchant_solution.merchant_profile_service;

import constants.BodyStatusCode;
import constants.MerchantSolutionURL;
import constants.ResponseStatusCode;
import io.restassured.response.Response;
import models.Merchant;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;
import utilities.Log;
import utilities.RestAPI;

public class GetStoreFromParentIdSteps {

    public Response getStoreFromParentId(Merchant merchant, String language) {

        String basePath = MerchantSolutionURL.getStoreFromParentIdPath + "/" + merchant.id + "/stores";
        String parameterPath = "?language=" + language;

        Response response = RestAPI.getApi(MerchantSolutionURL.baseUriMerchantProfile, basePath,
                "X-Request-Id","Auto", parameterPath);
        return  response;
    }

    @Step
    public void beAbleToGetStoreFromParentId(Merchant merchant, String language, int expectNumberOfStores) {

        Response response = getStoreFromParentId(merchant, language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Thành công"))
                .and().body("data", Matchers.notNullValue())
                .and().body("data.stores", Matchers.hasSize(expectNumberOfStores));

        Log.highlight("Done");
    }

    @Step
    public void unableToGetStoreFromParentId(Merchant merchant, String language) {

        Response response = getStoreFromParentId(merchant, language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.NOT_FOUND)
                .and().body("status", Matchers.equalTo(BodyStatusCode.NOT_FOUND))
                .and().body("message", Matchers.equalTo("Không tìm thấy"))
                .and().body("data", Matchers.isEmptyOrNullString());

        Log.highlight("Done");
    }
}

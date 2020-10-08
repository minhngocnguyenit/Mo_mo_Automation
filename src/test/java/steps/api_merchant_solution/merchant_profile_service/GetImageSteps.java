package steps.api_merchant_solution.merchant_profile_service;

import constants.BodyStatusCode;
import constants.MerchantSolutionURL;
import constants.ResponseStatusCode;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;
import utilities.Log;
import utilities.RestAPI;

public class GetImageSteps {

    public Response getImage(String id, String language) {

        String getPath = MerchantSolutionURL.getImagePath + "/" + id;
        String parameterPath = "?language=" + language;

        Response response = RestAPI.getApi(MerchantSolutionURL.baseUriMerchantProfile, getPath,
                "X-Request-Id","Auto", parameterPath);

        return response;
    }

    @Step
    public void beAbleToGetImage(String id, String language) {

        Response response = getImage(id, language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Thành công"))
                .and().body("data", Matchers.notNullValue());

        Log.highlight("Done");
    }

    @Step
    public void notAbleToGetImage(String id, String language) {

        Response response = getImage(id, language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.NOT_FOUND)
                .and().body("status", Matchers.equalTo(BodyStatusCode.NOT_FOUND))
                .and().body("message", Matchers.equalTo("Không tìm thấy"))
                .and().body("data", Matchers.isEmptyOrNullString());

        Log.highlight("Done");
    }

}

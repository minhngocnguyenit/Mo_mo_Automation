package steps.api_merchant_solution.location_service;

import constants.BodyStatusCode;
import constants.MerchantSolutionURL;
import constants.ResponseStatusCode;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;
import utilities.Log;
import utilities.RestAPI;

public class AutoCompletePlaceSteps {

    @Step
    public void beAbleToAutoCompletePlace(String address, String location, String language) {

        String parameterPath = "?language=" + language + "&address=" + address + "&location=" + location;

        Response response = RestAPI.getApi(MerchantSolutionURL.baseUriLocation, MerchantSolutionURL.autoCompletePlacePath,
                "X-Request-Id", "Auto", parameterPath);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Thành công"));

        Log.highlight("Done");
    }
}
package steps.api_merchant_solution.onboarding_service;

import constants.BodyStatusCode;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import constants.MerchantSolutionURL;
import utilities.Log;
import utilities.RestAPI;
import constants.ResponseStatusCode;
import org.hamcrest.Matchers;

public class GetRegisterInfoSteps {

    @Step
    public void beAbleToGetRegisterInfo(String language) {

        String parameterPath = "?language=" + language;

        Response response = RestAPI.getApi(MerchantSolutionURL.baseUriOnboarding, MerchantSolutionURL.getRegisterInfoPath,
                                "X-Request-Id","Auto3", parameterPath);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Thành công"));

        Log.highlight("Done");
    }
}


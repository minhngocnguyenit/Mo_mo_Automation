package steps.api_merchant_solution.onboarding_service;

import constants.BodyStatusCode;
import constants.ResponseStatusCode;
import io.restassured.response.Response;
import models.Contract;
import net.thucydides.core.annotations.Step;
import constants.MerchantSolutionURL;
import org.hamcrest.Matchers;
import utilities.Log;
import utilities.RestAPI;

public class GetEContractSteps {

    public Response getEContract(Contract contract, String language) {

        String parameterPath = "?language=" + language
                + "&merchantCategoryId=" + contract.merchantCategoryId;

        Response response = RestAPI.getApi(MerchantSolutionURL.baseUriOnboarding, MerchantSolutionURL.getEContractPath,
                "X-Request-Id","Auto", parameterPath);

        return response;
    }

    @Step
    public void beAbleToGetEContract(Contract contract, String language) {

        Response response = getEContract(contract,language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Thành công"))
                .and().body("data.serviceFee", Matchers.equalTo(contract.serviceFee));

        Log.highlight("Done");
    }

    @Step
    public void notFoundWhenGetEContract(Contract contract, String language) {

        Response response = getEContract(contract,language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.NOT_FOUND)
                .and().body("status", Matchers.equalTo(BodyStatusCode.NOT_FOUND));

        Log.highlight("Done");
    }
}

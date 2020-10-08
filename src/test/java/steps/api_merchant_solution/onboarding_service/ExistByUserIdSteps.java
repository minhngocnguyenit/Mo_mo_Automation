package steps.api_merchant_solution.onboarding_service;

import constants.BodyStatusCode;
import constants.ResponseStatusCode;
import io.restassured.response.Response;
import models.Merchant;
import net.thucydides.core.annotations.Step;
import constants.MerchantSolutionURL;
import org.hamcrest.Matchers;
import utilities.Log;
import utilities.RestAPI;

public class ExistByUserIdSteps {

    public Response existByUserID(Merchant merchant, String language) {

        String basePath = MerchantSolutionURL.existByUserIdPath + "/" + merchant.userId;
        String parameterPath = "?language=" + language;

        Response response = RestAPI.getApi(MerchantSolutionURL.baseUriOnboarding, basePath,
                "X-Request-Id","Auto", parameterPath);
        return  response;
    }

    @Step
    public void verifyUserExistAndMerchantCreated(Merchant merchant, String language) {

        Response response = existByUserID(merchant, language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Thành công"))
                .and().body("data", Matchers.notNullValue())
                .and().body("data.companyName", Matchers.equalTo(merchant.companyName))
                .and().body("data.companyAddress", Matchers.equalTo(merchant.companyAddress))
                .and().body("data.phone", Matchers.equalTo(merchant.phone))
                .and().body("data.viceGerent", Matchers.equalTo(merchant.viceGerent))
                .and().body("data.isActive", Matchers.equalTo(1))
                .and().body("data.businessRegistrationId", Matchers.equalTo(Integer.parseInt(merchant.businessRegistrationId)))
                .and().body("data.merchantBankInfo.momoWallet", Matchers.equalTo(merchant.userId))
                .and().body("data.merchantRegisters[0].businessModelId", Matchers.equalTo(Integer.parseInt(merchant.businessModelId)))
                .and().body("data.merchantRegisters[0].numberStoreId", Matchers.equalTo(Integer.parseInt(merchant.numberOfStoresId)))
                .and().body("data.merchantRegisters[0].openTime", Matchers.equalTo(Integer.parseInt(merchant.openTime)))
                .and().body("data.merchantRegisters[0].closeTime", Matchers.equalTo(Integer.parseInt(merchant.closeTime)));

        Log.highlight("Done");
    }

    @Step
    public void verifyUserNotExistAndMerchantNotCreated(Merchant merchant, String language) {

        Response response = existByUserID(merchant, language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.NOT_FOUND)
                .and().body("status", Matchers.equalTo(BodyStatusCode.NOT_FOUND))
                .and().body("message", Matchers.equalTo("Tài khoản chưa đăng nhập với hệ thống"))
                .and().body("data", Matchers.isEmptyOrNullString());

        Log.highlight("Done");
    }

    @Step
    public void verifyUserIdWrongFormat(Merchant merchant, String language) {

        Response response = existByUserID(merchant, language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.BAD_REQUEST)
                .and().body("status", Matchers.equalTo(BodyStatusCode.BAD_REQUEST))
                .and().body("message", Matchers.equalTo("Số điện thoại người dùng không chính xác"))
                .and().body("data", Matchers.isEmptyOrNullString());

        Log.highlight("Done");
    }
}
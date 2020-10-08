package steps.api_merchant_solution.onboarding_service;

import constants.BodyStatusCode;
import constants.MerchantSolutionURL;
import constants.ResponseStatusCode;
import io.restassured.response.Response;
import models.Merchant;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;
import utilities.Log;
import utilities.RestAPI;

public class CreateNewMerchantSteps {

    private String createBody(Merchant merchant) {

        String body = "{\n" +
                "    \"phone\": \"" + merchant.phone + "\",\n" +
                "    \"viceGerent\": \""+ merchant.viceGerent+"\",\n" +
                "    \"companyName\": \""+ merchant.companyName +"\",\n" +
                "    \"companyAddress\": \""+ merchant.companyAddress +"\",\n" +
                "    \"businessRegistrationId\": "+ merchant.businessRegistrationId +",\n" +
                "    \"businessCategoryId\": "+ merchant.businessCategoryId +",\n" +
                "    \"businessModelId\": "+ merchant.businessModelId +",\n" +
                "    \"numberOfStoresId\": "+ merchant.numberOfStoresId +",\n" +
                "    \"openTime\": "+ merchant.openTime +",\n" +
                "    \"closeTime\": "+ merchant.closeTime +",\n" +
                "    \"userId\": \"" + merchant.userId + "\"\n" +
                "}";

        return  body;
    }

    public Response createNewMerchant(Merchant merchant, String language){

        String body = createBody(merchant);

        String getPath = MerchantSolutionURL.createNewMerchantPath + "?language=" + language;

        Response response = RestAPI.postApi(MerchantSolutionURL.baseUriOnboarding, getPath,
                "X-Request-Id","Auto", body);

        return  response;
    }

    @Step
    public void beAbleToCreateNewMerchant(Merchant merchant, String language) {

        Response response = createNewMerchant(merchant, language);

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
    public void notAbleToCreateNewMerchant(Merchant merchant, String language) {

        Response response = createNewMerchant(merchant, language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.BAD_REQUEST)
                .and().body("status", Matchers.equalTo(BodyStatusCode.BAD_REQUEST))
                .and().body("message", Matchers.equalTo("User Id đã tồn tại"))
                .and().body("data", Matchers.isEmptyOrNullString());

        Log.highlight("Done");
    }

    @Step
    public void verifyUserIdNotValid(Merchant merchant, String language) {

        Response response = createNewMerchant(merchant, language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.BAD_REQUEST)
                .and().body("status", Matchers.equalTo(BodyStatusCode.BAD_REQUEST))
                .and().body("message", Matchers.equalTo("Số điện thoại người dùng không chính xác"))
                .and().body("data", Matchers.isEmptyOrNullString());

        Log.highlight("Done");
    }

}

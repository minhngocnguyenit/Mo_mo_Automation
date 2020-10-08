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

public class UpdateMerchantSteps {

    private String createBody(Merchant merchant) {

        String body = "{\n" +
                "  \"approvedId\": 16,\n" +
                "  \"businessModelId\": "+ merchant.businessModelId +",\n" +
                "  \"businessRegistrationId\": "+ merchant.businessRegistrationId +",\n" +
                "  \"changeInfoAccepted\": 1,\n" +
                "  \"closeTime\": "+ merchant.closeTime +",\n" +
                "  \"companyAddress\": \""+ merchant.companyAddress +"\",\n" +
                "  \"companyName\": \""+ merchant.companyName +"\",\n" +
                "  \"createdBy\": 2987,\n" +
                "  \"eContractAgreementDate\": \"2020-10-07T15:58:04.76\",\n" +
                "  \"email\": \" \",\n" +
                "  \"id\": "+merchant.id+",\n" +
                "  \"isActive\": 1,\n" +
                "  \"isDefined\": 0,\n" +
                "  \"merchantCategoryId\": "+ merchant.businessCategoryId +",\n" +
                "  \"numberOfStoresId\": "+ merchant.numberOfStoresId +",\n" +
                "  \"openTime\": "+ merchant.openTime +",\n" +
                "  \"parentId\": null,\n" +
                "  \"password\": \""+merchant.phone+"\",\n" +
                "  \"phone\": \""+merchant.phone+"\",\n" +
                "  \"role\": \"MERCHANT_HAS_STORE\",\n" +
                "  \"roleId\": 4,\n" +
                "  \"storeId\": null,\n" +
                "  \"storeSlug\": \"null\",\n" +
                "  \"userId\": \""+merchant.userId+"\",\n" +
                "  \"username\": \""+merchant.userName+"\",\n" +
                "  \"viceGerent\": \""+merchant.viceGerent+"\",\n" +
                "  \"viceGerentPid\": null,\n" +
                "  \"viceGerentRole\": \"DICRECTOR\"\n" +
                "}\n";

        return  body;
    }

    public Response updateMerchant(Merchant merchant, String language){

        String body = createBody(merchant);

        String patchPath = MerchantSolutionURL.updateMerchantPath + "/"+merchant.id +"?language=" + language;

        Response response = RestAPI.patchApi(MerchantSolutionURL.baseUriOnboarding, patchPath,
                "X-Request-Id","Auto", body);

        return  response;
    }

    @Step
    public void beAbleToUpdateMerchant(Merchant merchant, String language) {

        Response response = updateMerchant(merchant, language);

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



}

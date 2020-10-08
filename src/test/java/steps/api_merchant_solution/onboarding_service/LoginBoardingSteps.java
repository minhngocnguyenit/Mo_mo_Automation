package steps.api_merchant_solution.onboarding_service;

import constants.BodyStatusCode;
import constants.ResponseStatusCode;
import io.restassured.response.Response;
import models.Merchant;
import net.thucydides.core.annotations.Step;
import constants.MerchantSolutionURL;
import utilities.Log;
import org.hamcrest.Matchers;
import utilities.RestAPI;

public class LoginBoardingSteps {

    private String createBody(Merchant merchant) {

        String body = "{\n" +
                "    \"userName\": \"" + merchant.userName + "\",\n" +
                "    \"password\": \"" + merchant.password + "\",\n" +
                "    \"userId\": \"" + merchant.userId + "\"\n" +
                "}";

        return  body;
    }

    public Response loginBoarding(Merchant merchant, String language){

        String body = createBody(merchant);

        String getPath = MerchantSolutionURL.loginBoardingPath + "?language=" + language;

        Response response = RestAPI.postApi(MerchantSolutionURL.baseUriOnboarding, getPath,
                "X-Request-Id","Auto", body);

        return response;
    }

    @Step
    public void beAbleToLoginBoarding(Merchant merchant, String language) {

        Response response = loginBoarding(merchant,language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Tài khoản đã đăng nhập với hệ thống"));

        Log.highlight("Done");
    }

    @Step
    public void unableToLoginBoarding(Merchant merchant, String language) {

        Response response = loginBoarding(merchant,language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.NOT_FOUND)
                .and().body("status", Matchers.equalTo(BodyStatusCode.NOT_FOUND))
                .and().body("message", Matchers.equalTo("Không tìm thấy"));

        Log.highlight("Done");
    }

    @Step
    public void wrongPasswordLoginBoarding(Merchant merchant, String language) {

        Response response = loginBoarding(merchant,language);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.BAD_REQUEST)
                .and().body("status", Matchers.equalTo(BodyStatusCode.BAD_REQUEST))
                .and().body("message", Matchers.equalTo("Mật khẩu không đúng"));

        Log.highlight("Done");
    }
}

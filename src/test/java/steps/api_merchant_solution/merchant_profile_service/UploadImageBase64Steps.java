package steps.api_merchant_solution.merchant_profile_service;

import constants.BodyStatusCode;
import constants.MerchantSolutionURL;
import constants.ResponseStatusCode;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;
import utilities.Log;
import utilities.RestAPI;

public class UploadImageBase64Steps {

    public Response uploadImageBase64(String id, String imageFileBase64, String imageType) {

        String uploadPath = MerchantSolutionURL.uploadImageBase64Path + "/" + id + "/imageBase64";

        Response response = RestAPI.postApiFormData(MerchantSolutionURL.baseUriMerchantProfile, uploadPath,
                "X-Request-Id","Auto", "file", imageFileBase64,"type", imageType);

        return response;
    }

    @Step
    public void beAbleToUploadImageBase64Public(String id, String imageFileBase64, String imageType) {

        Response response = uploadImageBase64(id, imageFileBase64, imageType);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Thành công"))
                .and().body("data.isActive", Matchers.equalTo(1))
                .and().body("data.cdnUrl", Matchers.notNullValue());

        Log.highlight("Done");
    }

    @Step
    public void beAbleToUploadImageBase64Private(String id, String imageFileBase64, String imageType) {

        Response response = uploadImageBase64(id, imageFileBase64, imageType);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.OK)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SUCCESS))
                .and().body("message", Matchers.equalTo("Thành công"))
                .and().body("data.isActive", Matchers.equalTo(1))
                .and().body("data.cdnUrl", Matchers.isEmptyOrNullString());

        Log.highlight("Done");
    }

    @Step
    public void notAbleToUploadImageBase64(String id, String imageFileBase64, String imageType) {

        Response response = uploadImageBase64(id, imageFileBase64, imageType);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.NOT_FOUND)
                .and().body("status", Matchers.equalTo(BodyStatusCode.NOT_FOUND))
                .and().body("message", Matchers.equalTo("Không tìm thấy"))
                .and().body("data", Matchers.isEmptyOrNullString());

        Log.highlight("Done");
    }

    @Step
    public void errorUploadImageBase64(String id, String imageFileBase64, String imageType) {

        Response response = uploadImageBase64(id, imageFileBase64, imageType);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.INTERNAL_SERVER_ERROR)
                .and().body("status", Matchers.equalTo(BodyStatusCode.SYSTEM_ERROR))
                .and().body("message", Matchers.equalTo("Lỗi hệ thống"))
                .and().body("data", Matchers.isEmptyOrNullString());

        Log.highlight("Done");
    }

    @Step
    public void badRequestUploadImageBase64(String id, String imageFileBase64, String imageType) {

        Response response = uploadImageBase64(id, imageFileBase64, imageType);

        response.then().assertThat()
                .statusCode(ResponseStatusCode.BAD_REQUEST)
                .and().body("status", Matchers.equalTo(BodyStatusCode.BAD_REQUEST))
                .and().body("message", Matchers.equalTo("Dữ liệu sai định dạng"))
                .and().body("data", Matchers.isEmptyOrNullString());

        Log.highlight("Done");
    }

}

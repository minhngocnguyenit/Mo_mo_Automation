package features.api_merchant_solution.merchant_profile_service;

import constants.ImageFileBase64;
import constants.ImageType;
import io.restassured.response.Response;
import models.ImageBase64;
import models.Merchant;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.api_merchant_solution.merchant_profile_service.GetImageSteps;
import steps.api_merchant_solution.merchant_profile_service.UploadImageBase64Steps;
import steps.api_merchant_solution.onboarding_service.CreateNewMerchantSteps;
import utilities.PhoneNumber;

@RunWith(SerenityRunner.class)
public class GetImage {

    @Steps
    GetImageSteps getImageSteps;

    static Merchant[] merchants = new Merchant[10];
    static ImageBase64[] imagesBase64 = new ImageBase64[10];

    @BeforeClass
    public static void prepareDataTest() {

        //create Merchants
        String[] phoneNumbers = PhoneNumber.createNew(3);
        Response res;
        CreateNewMerchantSteps newMerchant = new CreateNewMerchantSteps();
        for (int i = 1; i <= 3; i++) {
            merchants[i] = new Merchant();
            merchants[i].userId = phoneNumbers[i];
            merchants[i].setDefaultData();
            res = newMerchant.createNewMerchant(merchants[i], "vi");
            merchants[i].id = res.then().extract().path("data.id").toString();
        }

        //upload images
        UploadImageBase64Steps newImage = new UploadImageBase64Steps();
        imagesBase64[1] = new ImageBase64();
        res = newImage.uploadImageBase64(merchants[1].id, ImageFileBase64.AVATAR, ImageType.AVATAR);
        imagesBase64[1].id = res.then().extract().path("data.id").toString();

        imagesBase64[2] = new ImageBase64();
        res = newImage.uploadImageBase64(merchants[2].id, ImageFileBase64.BUSINESS_LICENSE, ImageType.BUSINESS_LICENSE);
        imagesBase64[2].id = res.then().extract().path("data.id").toString();

        imagesBase64[3] = new ImageBase64();
        imagesBase64[3].id = "7894563";
    }

    @Test
    @Title("get Image public")
    public void testGetImagePublic(){

        getImageSteps.beAbleToGetImage(imagesBase64[1].id, "vi");
    }

    @Test
    @Title("get Image private")
    public void testGetImagePrivate(){

        getImageSteps.beAbleToGetImage(imagesBase64[2].id, "vi");
    }

    @Test
    @Title("get Image wrong ID")
    public void testGetImageWrongId(){

        getImageSteps.notAbleToGetImage(imagesBase64[3].id, "vi");
    }
}
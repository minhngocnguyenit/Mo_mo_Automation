package features.api_merchant_solution.merchant_profile_service;

import constants.ImageFileBase64;
import constants.ImageType;
import io.restassured.response.Response;
import models.Merchant;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.api_merchant_solution.merchant_profile_service.UploadImageBase64Steps;
import steps.api_merchant_solution.onboarding_service.CreateNewMerchantSteps;
import utilities.PhoneNumber;

@RunWith(SerenityRunner.class)
public class UploadImageBase64 {

    @Steps
    UploadImageBase64Steps uploadImageBase64Steps;

    static Merchant[] merchants = new Merchant[20];

    @BeforeClass
    public static void prepareDataTest() {

        CreateNewMerchantSteps newMerchant = new CreateNewMerchantSteps();
        String[] phoneNumbers = PhoneNumber.createNew(10);
        Response res;

        for (int i = 1; i <= 10; i++) {
            merchants[i] = new Merchant();
            merchants[i].userId = phoneNumbers[i];
            merchants[i].setDefaultData();
            res = newMerchant.createNewMerchant(merchants[i], "vi");
            merchants[i].id = res.then().extract().path("data.id").toString();
        }

        merchants[11] = new Merchant();
        merchants[11].id = "9778855";
    }

    @Test
    @Title("id and file are valid. Type = AI (Avatar)")
    public void testUploadImageTypeAvatar(){

        uploadImageBase64Steps.beAbleToUploadImageBase64Public(merchants[1].id, ImageFileBase64.AVATAR, ImageType.AVATAR);
    }

    @Test
    @Title("id, file are valid. Type = CI (Cover)")
    public void testUploadImageTypeCover(){

        uploadImageBase64Steps.beAbleToUploadImageBase64Public(merchants[2].id, ImageFileBase64.COVER, ImageType.COVER);
    }

    @Test
    @Title("id, file are valid. Type = A (Business License)")
    public void testUploadImageTypeBusinessLicense(){

        uploadImageBase64Steps.beAbleToUploadImageBase64Private(merchants[3].id, ImageFileBase64.BUSINESS_LICENSE, ImageType.BUSINESS_LICENSE);
    }

    @Test
    @Title("id, file are valid. Type = B (Front Identity)")
    public void testUploadImageTypeFrontIdentity(){

        uploadImageBase64Steps.beAbleToUploadImageBase64Private(merchants[4].id, ImageFileBase64.FRONT_IDENTITY, ImageType.FRONT_IDENTITY);
    }

    @Test
    @Title("id, file are valid. Type = C (Rear Identity))")
    public void testUploadImageTypeRearIdentity(){

        uploadImageBase64Steps.beAbleToUploadImageBase64Private(merchants[5].id, ImageFileBase64.REAR_IDENTITY, ImageType.REAR_IDENTITY);
    }

    @Test
    @Title("id, file are valid. Type = R1 (STORE_OUTSIDE_INSIDE)")
    public void testUploadImageTypeStore(){

        uploadImageBase64Steps.beAbleToUploadImageBase64Private(merchants[6].id, ImageFileBase64.STORE_OUTSIDE, ImageType.STORE_OUTSIDE_INSIDE);
    }

    @Test
    @Title("id, file are valid. Type = R2 (PRODUCT))")
    public void testUploadImageTypeProduct(){

        uploadImageBase64Steps.beAbleToUploadImageBase64Private(merchants[7].id, ImageFileBase64.PRODUCT, ImageType.PRODUCT);
    }

    @Test
    @Title("id, file are valid. Type = R3 (BRAND)")
    public void testUploadImageTypeBrand(){

        uploadImageBase64Steps.beAbleToUploadImageBase64Private(merchants[8].id, ImageFileBase64.BRAND, ImageType.BRAND);
    }

    @Test
    @Title("id, file are valid. Type is invalid")
    public void testUploadImageTypeInvalid(){

        uploadImageBase64Steps.errorUploadImageBase64(merchants[9].id, ImageFileBase64.STORE_INSIDE, "S45");
    }

    @Test
    @Title("file is empty")
    public void testUploadImageFileEmpty(){

        uploadImageBase64Steps.badRequestUploadImageBase64(merchants[10].id, "", ImageType.STORE_OUTSIDE_INSIDE);
    }

    @Test
    @Title("id does not exist")
    public void testUploadImageIdNotExist(){

        uploadImageBase64Steps.notAbleToUploadImageBase64(merchants[11].id, ImageFileBase64.STORE_INSIDE, ImageType.STORE_OUTSIDE_INSIDE);
    }
}
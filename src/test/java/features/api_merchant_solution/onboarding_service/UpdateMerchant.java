package features.api_merchant_solution.onboarding_service;

import constants.CsvFilePath;
import io.restassured.response.Response;
import models.Merchant;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.api_merchant_solution.onboarding_service.CreateNewMerchantSteps;
import steps.api_merchant_solution.onboarding_service.UpdateMerchantSteps;
import utilities.DataCSV;
import utilities.PhoneNumber;
import java.util.List;

@RunWith(SerenityRunner.class)
public class UpdateMerchant {

    @Steps
    UpdateMerchantSteps updateMerchantSteps;

    static Merchant[] merchants = new Merchant[10];

    @BeforeClass
    public static void prepareDataTest() {

        //Load all data in CSV file
        List<List<String>> allDataCSV = DataCSV.getAllDataCSV(CsvFilePath.UPDATE_MERCHANT);

        //set data for Merchants
        for(int i = 1; i <= 2; i++){
            merchants[i] = new Merchant();
            merchants[i].phone = allDataCSV.get(i).get(0);
            merchants[i].businessCategoryId = allDataCSV.get(i).get(1);
            merchants[i].businessModelId = allDataCSV.get(i).get(2);
            merchants[i].businessRegistrationId = allDataCSV.get(i).get(3);
            merchants[i].numberOfStoresId = allDataCSV.get(i).get(4);
            merchants[i].companyName = allDataCSV.get(i).get(5);
            merchants[i].companyAddress = allDataCSV.get(i).get(6);
            merchants[i].viceGerent = allDataCSV.get(i).get(7);
            merchants[i].openTime = allDataCSV.get(i).get(8);
            merchants[i].closeTime = allDataCSV.get(i).get(9);
        }

        merchants[1].userId = PhoneNumber.createNew();
        merchants[2].userId = merchants[1].userId;
        CreateNewMerchantSteps newMerchant = new CreateNewMerchantSteps();
        Response res;
        res = newMerchant.createNewMerchant(merchants[1], "vi");
        merchants[1].id = res.then().extract().path("data.id").toString();
        merchants[2].id = merchants[1].id;
        merchants[1].userName = res.then().extract().path("data.username").toString();
        merchants[2].userName = merchants[1].userName;
    }

    @Test
    @Title("Able to update all main fields")
    public void testUpdateMerchant()   {
        //merchant[1] is old data, merchant[2] is new data
        updateMerchantSteps.beAbleToUpdateMerchant(merchants[2], "vi");
    }
}
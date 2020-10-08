package features.api_merchant_solution.onboarding_service;

import constants.CsvFilePath;
import models.Merchant;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import steps.api_merchant_solution.onboarding_service.ExistByUserIdSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import utilities.DataCSV;
import java.util.List;
import steps.api_merchant_solution.onboarding_service.CreateNewMerchantSteps;
import utilities.PhoneNumber;

@RunWith(SerenityRunner.class)
public class ExistByUserId {

    @Steps
    ExistByUserIdSteps existByUserIdSteps;

    static Merchant[] merchants = new Merchant[10];

    @BeforeClass
    public static void prepareDataTest() {

        //Load all data in CSV file
        List<List<String>> allDataCSV = DataCSV.getAllDataCSV(CsvFilePath.EXIST_BY_USER_ID);

        //set data for Merchants
        for(int i = 1; i < allDataCSV.size(); i++){
            merchants[i] = new Merchant();
            merchants[i].userId = allDataCSV.get(i).get(0);
        }

        //prepare data for merchants[1]
        merchants[1].userId = PhoneNumber.createNew();
        merchants[1].setDefaultData();
        CreateNewMerchantSteps newMerchant = new CreateNewMerchantSteps();
        newMerchant.createNewMerchant(merchants[1], "vi");
    }

    @Test
    @Title("UserId exists and created Merchant")
    public void testUserExistAndMerchantCreated() {

        existByUserIdSteps.verifyUserExistAndMerchantCreated(merchants[1],"vi");
    }

    @Test
    @Title("UserId not exists and not created Merchant yet")
    public void testUserNotExistAndMerchantNotCreated() {

        existByUserIdSteps.verifyUserNotExistAndMerchantNotCreated(merchants[2],"vi");
    }

    @Test
    @Title("UserId wrong format")
    public void testUserIdWrongFormat() {

        existByUserIdSteps.verifyUserIdWrongFormat(merchants[3],"vi");
    }
}


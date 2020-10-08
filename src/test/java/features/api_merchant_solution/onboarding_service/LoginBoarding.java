package features.api_merchant_solution.onboarding_service;

import constants.CsvFilePath;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import models.Merchant;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import steps.api_merchant_solution.onboarding_service.CreateNewMerchantSteps;
import steps.api_merchant_solution.onboarding_service.LoginBoardingSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import utilities.DataCSV;
import utilities.PhoneNumber;
import java.util.List;

@RunWith(SerenityRunner.class)
public class LoginBoarding {

    @Steps
    LoginBoardingSteps loginInBoardingSteps;

    static Merchant[] merchants = new Merchant[10];

    @BeforeClass
    public static void prepareDataTest() {

        //Load all data in CSV file
        List<List<String>> allDataCSV = DataCSV.getAllDataCSV(CsvFilePath.LOGIN_BOARDING);

        //set data for Merchants
        for(int i = 1; i < allDataCSV.size(); i++){
            merchants[i] = new Merchant();
            merchants[i].userId = allDataCSV.get(i).get(0);
            merchants[i].userName = allDataCSV.get(i).get(1);
            merchants[i].password = allDataCSV.get(i).get(2);
        }

        String[] listPhoneNumber = PhoneNumber.createNew(4);

        //prepare data for merchants[1]
        merchants[1].userId = listPhoneNumber[1];
        merchants[1].setDefaultData();
        CreateNewMerchantSteps newMerchant1 = new CreateNewMerchantSteps();
        Response response1 = newMerchant1.createNewMerchant(merchants[1], "vi");
        merchants[1].userName = response1.then().extract().path("data.username").toString();
        merchants[1].password = merchants[1].phone;

        //prepare data for merchants[3]
        merchants[3].userId = listPhoneNumber[3];
        merchants[3].setDefaultData();
        CreateNewMerchantSteps newMerchant3 = new CreateNewMerchantSteps();
        Response response3 = newMerchant3.createNewMerchant(merchants[3], "vi");
        merchants[3].password = merchants[3].phone;

        //prepare data for merchants[4]
        merchants[4].userId = listPhoneNumber[4];
        merchants[4].setDefaultData();
        CreateNewMerchantSteps newMerchant4 = new CreateNewMerchantSteps();
        Response response4 = newMerchant4.createNewMerchant(merchants[4], "vi");
        merchants[4].userName = response4.then().extract().path("data.username").toString();
    }

    @Test
    @Title("UserID, userName, password are correct")
    public void testLoginBoardingSuccess() {

        loginInBoardingSteps.beAbleToLoginBoarding(merchants[1], "vi");
    }

    @Test
    @Title("UserId does not create Merchant yet")
    public void testUserIdNotCreateMerchant() {

        loginInBoardingSteps.unableToLoginBoarding(merchants[2], "vi");
    }

    @Test
    @Title("userName is incorrect")
    public void testUserNameIncorrect() {

        loginInBoardingSteps.unableToLoginBoarding(merchants[3], "vi");
    }

    @Test
    @Title("password is incorrect")
    public void testPasswordIncorrect() {

        loginInBoardingSteps.wrongPasswordLoginBoarding(merchants[4], "vi");
    }
}

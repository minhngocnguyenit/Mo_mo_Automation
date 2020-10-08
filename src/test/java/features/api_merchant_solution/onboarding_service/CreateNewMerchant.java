package features.api_merchant_solution.onboarding_service;

import constants.CsvFilePath;
import models.Merchant;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.api_merchant_solution.onboarding_service.CreateNewMerchantSteps;
import utilities.DataCSV;
import utilities.PhoneNumber;
import java.util.List;

@RunWith(SerenityRunner.class)
public class CreateNewMerchant {

    @Steps
    CreateNewMerchantSteps createNewMerchantSteps;

    static Merchant[] merchants = new Merchant[10];

    @BeforeClass
    public static void prepareDataTest() {

        //Load all data in CSV file
        List<List<String>> allDataCSV = DataCSV.getAllDataCSV(CsvFilePath.CREATE_NEW_MERCHANT);

        //set data for Merchants
        for(int i = 1; i < allDataCSV.size(); i++){
            merchants[i] = new Merchant();
            merchants[i].userId = allDataCSV.get(i).get(0);
            merchants[i].phone = allDataCSV.get(i).get(1);
            merchants[i].businessCategoryId = allDataCSV.get(i).get(2);
            merchants[i].businessModelId = allDataCSV.get(i).get(3);
            merchants[i].businessRegistrationId = allDataCSV.get(i).get(4);
            merchants[i].numberOfStoresId = allDataCSV.get(i).get(5);
            merchants[i].companyName = allDataCSV.get(i).get(6);
            merchants[i].companyAddress = allDataCSV.get(i).get(7);
            merchants[i].viceGerent = allDataCSV.get(i).get(8);
            merchants[i].openTime = allDataCSV.get(i).get(9);
            merchants[i].closeTime = allDataCSV.get(i).get(10);
        }

        String[] phoneNumbers = PhoneNumber.createNew(allDataCSV.size()-1);
        merchants[1].userId = phoneNumbers[1];
        merchants[2].userId = phoneNumbers[2];
        merchants[4].userId = phoneNumbers[4];
        merchants[4].phone = merchants[1].phone;
        merchants[5].userId = phoneNumbers[5];
        merchants[5].companyName = merchants[1].companyName;
        merchants[6].userId = phoneNumbers[6];
        merchants[6].phone = merchants[6].userId;

    }

    @Test
    @Title("UserID valid and has not created Merchant yet")
    public void testUserIDValidAndNotMerchant()   {

        createNewMerchantSteps.beAbleToCreateNewMerchant(merchants[1], "vi");
    }

    @Test
    @Title("UserID valid and has created Merchant")
    public void testUserIDValidAndHasMerchant() {

        createNewMerchantSteps.createNewMerchant(merchants[2],"vi");
        createNewMerchantSteps.notAbleToCreateNewMerchant(merchants[2], "vi");
    }

    @Test
    @Title("UserID not valid")
    public void testUserIDNotValid() {

        createNewMerchantSteps.verifyUserIdNotValid(merchants[3], "vi");
    }

    @Test
    @Title("UserID valid and Phone exists")
    public void testPhoneExists()   {

        createNewMerchantSteps.beAbleToCreateNewMerchant(merchants[4], "vi");
    }

    @Test
    @Title("UserID valid and CompanyName exists")
    public void testCompanyNameExists()   {

        createNewMerchantSteps.beAbleToCreateNewMerchant(merchants[5], "vi");
    }

    @Test
    @Title("UserID and phone are same")
    public void testUserIdAndPhoneAreSame()   {

        createNewMerchantSteps.beAbleToCreateNewMerchant(merchants[6], "vi");
    }
}
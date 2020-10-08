package features.api_merchant_solution.onboarding_service;

import constants.CsvFilePath;
import io.restassured.response.Response;
import models.Merchant;
import models.Store;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.api_merchant_solution.onboarding_service.CreateNewMerchantSteps;
import steps.api_merchant_solution.onboarding_service.CreateNewStoreSteps;
import utilities.DataCSV;
import utilities.PhoneNumber;
import java.util.List;

@RunWith(SerenityRunner.class)
public class CreateNewStore {

    @Steps
    CreateNewStoreSteps createNewStoreSteps;

    static Store[] stores = new Store[10];

    @BeforeClass
    public static void prepareDataTest() {

        //Load all data in CSV file
        List<List<String>> allDataCSV = DataCSV.getAllDataCSV(CsvFilePath.CREATE_NEW_STORE);

        String[] phoneNumbers = PhoneNumber.createNew(3);
        Response res;
        Merchant[] merchants = new Merchant[10];
        CreateNewMerchantSteps newMerchant = new CreateNewMerchantSteps();
        //set data for Stores
        for(int i = 1; i <= 3; i++){
            merchants[i] = new Merchant();
            merchants[i].userId = phoneNumbers[i];
            merchants[i].setDefaultData();
            res = newMerchant.createNewMerchant(merchants[i], "vi");
            merchants[i].id = res.then().extract().path("data.id").toString();
            stores[i] = new Store();
            stores[i].storeName = allDataCSV.get(i).get(0);
            stores[i].storeAddress = allDataCSV.get(i).get(1);
            stores[i].merchantId = merchants[i].id;
        }
        stores[3].merchantId = merchants[1].id;
        stores[4] = new Store();
        stores[4].storeName = allDataCSV.get(4).get(0);
        stores[4].storeAddress = allDataCSV.get(4).get(1);
        stores[4].merchantId = allDataCSV.get(4).get(2);;
    }

    @Test
    @Title("merchantID exists")
    public void testCreateNewStoreMerchantIdExists() {

        createNewStoreSteps.beAbleToCreateNewStore(stores[1], "vi");
    }

    @Test
    @Title("Address and Name null")
    public void testCreateNewStoreAddressAndNameNull() {

        createNewStoreSteps.beAbleToCreateNewStore(stores[2], "vi");
    }

    @Test
    @Title("create New Store while Merchant already has stores")
    public void testCreateNextStore() {

        createNewStoreSteps.beAbleToCreateNewStore(stores[3], "vi");
    }

    @Test
    @Title("merchantID not exists")
    public void testCreateNewStoreMerchantIdNotExists() {

        createNewStoreSteps.notAbleToCreateNewStore(stores[4], "vi");
    }

}
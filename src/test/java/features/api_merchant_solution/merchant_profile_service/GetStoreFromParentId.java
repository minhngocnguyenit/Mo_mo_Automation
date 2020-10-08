package features.api_merchant_solution.merchant_profile_service;

import io.restassured.response.Response;
import models.Merchant;
import models.Store;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.api_merchant_solution.merchant_profile_service.GetStoreFromParentIdSteps;
import steps.api_merchant_solution.onboarding_service.CreateNewMerchantSteps;
import steps.api_merchant_solution.onboarding_service.CreateNewStoreSteps;
import utilities.PhoneNumber;

@RunWith(SerenityRunner.class)
public class GetStoreFromParentId {

    @Steps
    GetStoreFromParentIdSteps getStoreFromParentIdSteps;

    static Merchant[] merchants = new Merchant[10];
    static Store[] stores = new Store[10];

    @BeforeClass
    public static void prepareDataTest() {

        //Create merchants
        String[] phoneNumbers = PhoneNumber.createNew(3);
        Response res;
        CreateNewMerchantSteps newMerchant = new CreateNewMerchantSteps();
        for(int i = 1; i <= 2; i++) {
            merchants[i] = new Merchant();
            merchants[i].userId = phoneNumbers[i];
            merchants[i].setDefaultData();
            res = newMerchant.createNewMerchant(merchants[i], "vi");
            merchants[i].id = res.then().extract().path("data.id").toString();
        }
        merchants[3] = new Merchant();
        merchants[3].userId = phoneNumbers[3];
        merchants[3].id = phoneNumbers[3];

        //Create stores for merchants[1]
        CreateNewStoreSteps newStore = new CreateNewStoreSteps();
        for(int j = 1; j <= 2; j++){
            stores[j] = new Store();
            stores[j].merchantId = merchants[1].id;
            stores[j].setDefaultData();
            res = newStore.createNewStore(stores[j],"vi");
        }
    }

    @Test
    @Title("Parent has few stores")
    public void testParentHasFewStores() {

        getStoreFromParentIdSteps.beAbleToGetStoreFromParentId(merchants[1], "vi",2);
    }

    @Test
    @Title("Parent does not have any store")
    public void testParentHasNoStore() {

        getStoreFromParentIdSteps.beAbleToGetStoreFromParentId(merchants[2], "vi",0);
    }

    @Test
    @Title("Parent ID does not exist")
    public void testParentIdNotExist() {

        getStoreFromParentIdSteps.unableToGetStoreFromParentId(merchants[3], "vi");
    }
}
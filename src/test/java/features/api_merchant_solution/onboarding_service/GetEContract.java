package features.api_merchant_solution.onboarding_service;

import constants.CsvFilePath;
import models.Contract;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import steps.api_merchant_solution.onboarding_service.GetEContractSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import utilities.DataCSV;
import java.util.List;

@RunWith(SerenityRunner.class)
public class GetEContract {

    @Steps
    GetEContractSteps getEContractSteps;

    static Contract[] contracts = new Contract[10];

    @BeforeClass
    public static void prepareDataTest() {

        //Load all data in CSV file
        List<List<String>> allDataCSV = DataCSV.getAllDataCSV(CsvFilePath.GET_E_CONTRACT);

        //set data for Contracts
        for(int i = 1; i < allDataCSV.size(); i++){
            contracts[i] = new Contract();
            contracts[i].merchantCategoryId = allDataCSV.get(i).get(0);
            contracts[i].serviceFee = allDataCSV.get(i).get(1);
        }
    }

    @Test
    @Title("businessCategoryId is Type A")
    public void testBusinessCategoryIdTypeA() {

        getEContractSteps.beAbleToGetEContract(contracts[1], "vi");
    }

    @Test
    @Title("businessCategoryId is Type B")
    public void testBusinessCategoryIdTypeB() {

        getEContractSteps.beAbleToGetEContract(contracts[2], "vi");
    }

    @Test
    @Title("businessCategoryId is not in the list of getRegisterInfo")
    public void testBusinessCategoryIdNotInList() {

        getEContractSteps.notFoundWhenGetEContract(contracts[3], "vi");
    }
}

package features.api_merchant_solution.transaction_service;

import models.Merchant;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.api_merchant_solution.transaction_service.GetTransStatisticByMonthSteps;
import utilities.DataCSV;

import java.util.List;

@RunWith(SerenityRunner.class)
public class GetTransStatisticByMonth {

    @Steps
    GetTransStatisticByMonthSteps getTransStatisticByMonthSteps;

    Merchant[] merchants = new Merchant[20];

    @Before
    public void prepareDataTest() {

        //Load all data in CSV file
        List<List<String>> allDataCSV = DataCSV.getAllDataCSV("src/test/resources/api_merchant_solution/transaction_service/get_trans_statistic_by_month.csv");

        //set data for Merchants
        for(int i = 1; i < allDataCSV.size(); i++){
            merchants[i] = new Merchant();
            merchants[i].partnerCode = allDataCSV.get(i).get(0);
            merchants[i].status = allDataCSV.get(i).get(1);
            merchants[i].search = allDataCSV.get(i).get(2);
            merchants[i].fromDate = allDataCSV.get(i).get(3);
            merchants[i].toDate = allDataCSV.get(i).get(4);
            merchants[i].storeId = allDataCSV.get(i).get(5);
            merchants[i].transType = "trans,refund,discount";
        }
    }

    @Test
    @Title("Merchant has data from FromDate to ToDate")
    public void testGetTransMerchantHasData() {
        getTransStatisticByMonthSteps.beAbleToGetTransStatisticByMonth(merchants[1], "vi");
    }

    @Test
    @Title("Merchant has  data from FromDate to ToDate")
    public void testGetTransMerchantHasNoData() {
        getTransStatisticByMonthSteps.noDataWhenGetTransStatisticByMonth(merchants[2], "vi");
    }
}
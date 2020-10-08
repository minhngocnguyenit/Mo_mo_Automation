package features.api_merchant_solution.location_service;

import constants.CsvFilePath;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.api_merchant_solution.location_service.AutoCompletePlaceSteps;
import utilities.DataCSV;
import java.util.List;

@RunWith(SerenityRunner.class)
public class AutoCompletePlace {

    @Steps
    AutoCompletePlaceSteps autoCompletePlaceSteps;

    static String[] address = new String[10];
    static String[] location = new String[10];

    @BeforeClass
    public static void prepareDataTest() {

        //Load all data in CSV file
        List<List<String>> allDataCSV = DataCSV.getAllDataCSV(CsvFilePath.AUTO_COMPLETE_PLACE);

        //set data for each test cases
        for(int i = 1; i < allDataCSV.size(); i++){
        address[i] = allDataCSV.get(i).get(0);
        location[i] = allDataCSV.get(i).get(1);
        }
    }

    @Test
    @Title("Get places with Number, Street, District, City")
    public void testPlaceWithNumberStreetDistrictCity() {
        autoCompletePlaceSteps.beAbleToAutoCompletePlace(address[1], location[1], "vi");
    }

    @Test
    @Title("Get places with Street, District, City")
    public void testPlaceWithStreetDistrictCity() {
        autoCompletePlaceSteps.beAbleToAutoCompletePlace(address[2], location[2], "vi");
    }

    @Test
    @Title("Get places with District")
    public void testPlaceWithDistrict() {
        autoCompletePlaceSteps.beAbleToAutoCompletePlace(address[3], location[3], "vi");
    }

    @Test
    @Title("Get places with City")
    public void testPlaceWithCity() {
        autoCompletePlaceSteps.beAbleToAutoCompletePlace(address[4], location[4], "vi");
    }

    @Test
    @Title("Get places with Building Name")
    public void testPlaceWithBuildingName() {
        autoCompletePlaceSteps.beAbleToAutoCompletePlace(address[5], location[5], "vi");
    }

    @Test
    @Title("Get places with Invalid data")
    public void testPlaceWithInvalidData() {
        autoCompletePlaceSteps.beAbleToAutoCompletePlace(address[6], location[6], "vi");
    }
}

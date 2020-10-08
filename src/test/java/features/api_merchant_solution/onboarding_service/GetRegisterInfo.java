package features.api_merchant_solution.onboarding_service;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import steps.api_merchant_solution.onboarding_service.GetRegisterInfoSteps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetRegisterInfo {

    @Steps
    GetRegisterInfoSteps getRegisterInfoSteps;

    @Test
    public void testGetRegisterInfo() {
        getRegisterInfoSteps.beAbleToGetRegisterInfo("vi");
    }
}

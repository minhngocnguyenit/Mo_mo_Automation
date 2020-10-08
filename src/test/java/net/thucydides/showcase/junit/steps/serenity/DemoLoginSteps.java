package net.thucydides.showcase.junit.steps.serenity;

import net.thucydides.core.annotations.Step;
import net.thucydides.showcase.junit.pages.DemoLoginPage;
import net.thucydides.showcase.junit.pages.HomePage;

public class DemoLoginSteps {

    DemoLoginPage demoLoginPage;
    HomePage homePage;

    @Step
    public void loginFlowTest() throws InterruptedException {
        demoLoginPage.inputPassword("111111");
        Thread.sleep(10000);
        homePage.allowPermission();
        Thread.sleep(4000);
        homePage.confirmAllow();
        Thread.sleep(10000);
    }
}

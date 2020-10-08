package net.thucydides.showcase.junit.features.mobile_GUI;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.showcase.junit.steps.serenity.DemoLoginSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class Demo {

    @Managed
    WebDriver appium;

    @Steps
    DemoLoginSteps demoLoginSteps;

    @Before
    public void appBaseState() throws Exception {
        System.out.println("start");
    }

    @After
    public void putAppInBackgroundOrTerminate(){
        System.out.println("end");
    }

    @Test
    public void login() throws InterruptedException {
        demoLoginSteps.loginFlowTest();
    }


}

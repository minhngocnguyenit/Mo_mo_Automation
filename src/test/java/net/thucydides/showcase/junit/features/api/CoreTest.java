package net.thucydides.showcase.junit.features.api;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.showcase.junit.model.Client;
import net.thucydides.showcase.junit.steps.serenity.core.CoreSteps;
import utilities.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CoreTest {

    public Client client;

    @Steps
    CoreSteps coreSteps;

    @Before
    public void prepareDataTest() {
        client = new Client("PP12!@pp", "CRM", "truong.le", 309, "01663198000");
    }

    @Test
    public void testSetCapSet() {
        coreSteps.beAbleToSetCapSet(client);
        Log.info("Done");
    }

    @Test
    public void testPayOut() {
        coreSteps.beAbleToPayout();
        Log.info("done");
    }
}

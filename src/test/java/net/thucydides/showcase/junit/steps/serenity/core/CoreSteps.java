package net.thucydides.showcase.junit.steps.serenity.core;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.showcase.junit.constants.CoreEndPoint;
import net.thucydides.showcase.junit.model.Client;
import net.thucydides.showcase.junit.steps.serenity.UtilSteps;
import utilities.Log;
import org.junit.Assert;

public class CoreSteps {

    public String setCapSet(Client client) {
        Log.debug("Begin to set capset");

        String body = "{\n" +
                "\"queue\": \"ha_qu_core_v7_test_req\",\n" +
                "\"data\":\n" +
                "\t{\n" +
                "\t\t\"initiator\":\"protoconfirm\",\n" +
                "\t\t\"pin\":\"" + client.getPin() + "\",\n" +
                "\t\t\"pin_encoding\":\"plain\",\n" +
                "\t\t\"ugaml_content_type\":\"modify_account\",\n" +
                "\t\t\"class_name\":\"com.mservice.goldengate.model.ModifyAccountRequest\",\n" +
                "\t\t\"extraTransData\":\n" +
                "\t\t{\n" +
                "\t\t\t\"client\":\"" + client.getClient() + "\",\n" +
                "\t\t\t\"user_action\": \"" + client.getUser_action() + "\"\n" +
                "\t\t},\n" +
                "\t\t\"cap_set\":" + client.getCap_set() + ",\n" +
                "\t\t\"type\":1,\n" +
                "\t\t\"agent\":\"" + client.getAgent() + "\"\n" +
                "\t\t\n" +
                "\t}\n" +
                "}";

        Log.debug("body: " + body);

        String response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .body(body)
                .when()
                .post(UtilSteps.getAPI_CORE_URL()+ CoreEndPoint.CAP_SET)
                .then().extract().asString();

        return response;
    }

    @Step
    public void beAbleToSetCapSet(Client client) {
        String response = setCapSet(client);
        Log.highlight(response);
        // Assert
        Assert.assertTrue(true);
        Log.info("Set CapSet success!");
    }

    public String payout() {
        Log.debug("Begin to payout");

        String body = "{\n" +
                "\"queue\": \"ha_qu_core_v7_test_req\",\n" +
                "\"data\":\n" +
                "  {\n" +
                "    \"initiator\":\"protoconfirm\",\n" +
                "    \"pin\":\"PP12!@pp\",\n" +
                "    \"pin_encoding\":\"plain\",\n" +
                "    \"ugaml_content_type\":\"unmap_agent\",\n" +
                "    \"class_name\":\"com.mservice.goldengate.model.MapAgentRequest\",\n" +
                "    \"extraTransData\":\n" +
                "    {\n" +
                "      \"client\":\"CRM\",\n" +
                "      \"user_action\": \"truong.dang\"\n" +
                "    },\n" +
                "    \"agid\":19112,\n" +
                "    \"agent\":\"0967102163\"\n" +
                "  \n" +
                "  }\n" +
                "}";

        Log.debug("body: " + body);

        String response = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON.withCharset("UTF-8"))
                .body(body)
                .when()
                .post("http://172.16.13.13:1234/rabbitClient")
                .then().extract().asString();

        return response;
    }

    @Step
    public void beAbleToPayout() {
        String response = payout();
        Log.highlight(response);
        // Assert
        Assert.assertTrue(true);
        Log.info("Payout success!");
    }
}

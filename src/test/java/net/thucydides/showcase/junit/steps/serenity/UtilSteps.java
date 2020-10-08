package net.thucydides.showcase.junit.steps.serenity;

import net.thucydides.core.guice.ThucydidesModule;
import net.thucydides.core.util.EnvironmentVariables;

public class UtilSteps {

    private static EnvironmentVariables evs;

    public static String getEnv(String key) {
        if (evs == null) {
            evs = (new ThucydidesModule()).provideEnvironmentVariables();
        }
        return evs.getProperty(key)==null?"":evs.getProperty(key);
    }

    public static String getAPI_CORE_URL(){
        return getEnv("api.core.url");
    }
}

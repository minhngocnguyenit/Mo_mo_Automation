package net.thucydides.showcase.junit.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class HomePage extends PageObject {

    @AndroidFindBy(xpath = "//*[@text = 'Cho phép truy cập']")
    private WebElement btnAllowPermission;

    @AndroidFindBy(xpath = "//*[@text = 'Allow']")
    private WebElement btnAllow;

    @AndroidFindBy(xpath = "//*[@text = 'Chuyển nhận tiền']")
    private WebElement iconSendMoney;

    public HomePage allowPermission(){
        btnAllowPermission.click();
        return this;
    }

    public HomePage confirmAllow(){
        btnAllow.click();
        return this;
    }

    public HomePage clickSendMoneyIcon(){
        iconSendMoney.click();
        return this;
    }

}

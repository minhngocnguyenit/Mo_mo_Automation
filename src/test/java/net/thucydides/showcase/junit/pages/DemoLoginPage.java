package net.thucydides.showcase.junit.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DemoLoginPage extends PageObject {


    @AndroidFindBy(className = "android.widget.EditText")
    private WebElement txtPhone;

    @AndroidFindBy(xpath = "//*[@text = 'Nhập mật khẩu']")
    private WebElement txtPassword;

    @AndroidFindBy(xpath = "//*[text() = 'ĐĂNG NHẬP']")
    private WebElement btnLogin;

    public DemoLoginPage inputPhone(String phone){
        waitFor(txtPhone);
        txtPhone.sendKeys(phone);
        return this;
    }

    public DemoLoginPage inputPassword(String password) throws InterruptedException {
        waitFor(txtPassword);
        txtPassword.sendKeys(password);
        return this;
    }

    public void login() {
        btnLogin.click();
    }
}


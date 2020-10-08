package net.thucydides.showcase.junit.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class SendMoneyPage extends PageObject {

    @AndroidFindBy(xpath = "//*[@text = 'Đến ví MoMo']")
    private WebElement itemSendToMoMo;

    @AndroidFindBy(xpath = "//*[@text = 'Nhập tên hoặc số điện thoại']")
    private WebElement tbPhone;

    @AndroidFindBy(xpath = "//*[@text = 'Chọn']")
    private WebElement btnSelect;

    @AndroidFindBy(xpath = "//*[@text = 'TIẾP TỤC']")
    private WebElement btnContinue;

    @AndroidFindBy(xpath = "//*[@text = 'Nhập số tiền']")
    private WebElement tbBalance;

    @AndroidFindBy(xpath = "//*[@package = 'com.mservice.momotransfer'][@class='android.view.ViewGroup'][@index = 4]")
    private WebElement btnSendMoney;

    @AndroidFindBy(xpath = "//*[@class='android.view.ViewGroup'][@index = 3]")
    private WebElement btnConfirm;

    public SendMoneyPage clickSendMoneyIcon(){
        itemSendToMoMo.click();
        return this;
    }

    public SendMoneyPage fillDesPhone(String phoneNumber){
        tbPhone.sendKeys(phoneNumber);
        return this;
    }

    public SendMoneyPage selectBtnSelect(){
        btnSelect.click();
        return this;
    }

    public SendMoneyPage clickBtnContinue(){
        btnContinue.click();
        return this;
    }

    public SendMoneyPage fillBalance(String balance){
        tbBalance.sendKeys(balance);
        return this;
    }

    public SendMoneyPage clickConfirmButton (String balance){
        btnConfirm.click();
        return this;
    }


}

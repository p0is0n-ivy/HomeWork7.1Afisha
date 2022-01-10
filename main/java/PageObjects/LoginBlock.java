package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginBlock extends BaseView{

    public LoginBlock(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[.='Войти']")
    private WebElement entryButton;

    @Step("Клик по кнопке 'Войти'")
    public LoginBlock entryButtonClick() {
        entryButton.click();
        return this;
    }

    @FindBy(xpath = "//iframe[contains(@src,'rambler.ru/login')]")
    private WebElement loginFrame;

    @Step("Переключение в iframe авторизации")
    public LoginBlock switchToLoginFrame() {
        driver.switchTo().frame(loginFrame);
        return this;
    }

    private final static String loginInputLocatorById = "login";
    @FindBy(id = loginInputLocatorById)
    private WebElement loginInput;

    @Step("Ввод логина")
    public LoginBlock fillLoginInput(String login) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(loginInputLocatorById)));
        loginInput.sendKeys(login);
        return this;
    }

    @FindBy(id = "password")
    private WebElement passwordInput;
    @Step("Ввод пароля")
    public LoginBlock fillPasswordInput(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @FindBy(xpath = "//span[@class='rui-Button-content']")
    private WebElement submitLoginButton;
    @Step("Нажать кнопку 'Войти'")
    public MainPage submitLoginButtonClick() {
        submitLoginButton.click();
        return new MainPage(driver);
    }
}

package PageObjects;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class MainPage extends BaseView {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[.='Войти']")
    WebElement loginButton;

    @Step("Клик по кнопке 'Войти'")
    public MainPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    @FindBy(xpath = "//button[@class='_3jiFM _2oJKT _1wjeh NYM3K _2wba5 _3MVGy _1e-25']")
    WebElement searchButton;

    @Step("Нажать на иконку поиска")
    public MainPage searchButtonClick() throws InterruptedException {
        Thread.sleep(5000);
        searchButton.click();
        return this;
    }

    private final static String SEARCH_FILM_FIELD_LOCATOR =
            "//input[@class='_1zHcQ _2DZMQ Y1g6a _3c12L']";
    @FindBy(xpath = SEARCH_FILM_FIELD_LOCATOR)
    WebElement searchFilmField;

    @Step("Ввод названия фильма")
    public MainPage searchFilmInput(String nameFilm) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_FILM_FIELD_LOCATOR)));
        searchFilmField.sendKeys(nameFilm);
        return this;
    }

    @FindBy(xpath = "//div[@class='_2PoNO _3YJjH'][contains(., 'Найти')]")
    WebElement startSearching;

    @Step("Запустить поиск, нажав кнопку 'Найти'")
    public MainPage startSearchingClick() {
        startSearching.click();
        return this;
    }

    private final static String FOUND_MOVIE_LOCATOR =
            "//ul[@class='_22-Vd _267Dy']/div[@class='_1kwbj lkWIA _2Ds3f']";
    @FindBy(xpath = FOUND_MOVIE_LOCATOR)
    List<WebElement> filmsList;

    @Step("Проверка соотвествия названия найденного фильма запросу")
    public MainPage SearchResultAndAssert() throws InterruptedException {
        Thread.sleep(1000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 200);");
        List<WebElement> filmsList = driver.findElements(By.xpath("//ul[@class='_22-Vd _267Dy']/div[@class='_1kwbj lkWIA _2Ds3f']"));
        filmsList.get(0);
        String actualString = driver.findElement(By.xpath(FOUND_MOVIE_LOCATOR)).getText();
        Assert.assertTrue(actualString.contains("Человек-паук"));
        return this;
    }
}




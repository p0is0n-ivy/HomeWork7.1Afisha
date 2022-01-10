package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;


public class ExpectedPremieresPage extends BaseView {

    public ExpectedPremieresPage(WebDriver driver) {
        super(driver);
    }

    private final static String FILMLIST_LOCATORS =
            "//li [@class = '_15Vwt _3d2h5 kWxBc']";
    @FindBy(xpath = FILMLIST_LOCATORS)
    public List<WebElement> filmsList;

@Step("Проскроллить страницу вниз и кликнуть в первый в списке фильм")
    public FilmPage clickFilm() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 300);");
        List<WebElement> filmsList = driver.findElements(By.xpath("//li [@class = '_15Vwt _3d2h5 kWxBc']//a[@class='_3NqYW DWsHS _3lmHp wkn_c']"));
        filmsList.get(0).click();
        return new FilmPage(driver);
    }

}



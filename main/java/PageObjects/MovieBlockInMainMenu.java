package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MovieBlockInMainMenu extends BaseView{
    public MovieBlockInMainMenu(WebDriver driver) {
        super(driver);
    }
    private final static String MOVIE_LOCATOR_IN_MAIN_MENU =
            "//nav/a[contains(., 'КИНО')]";

    @FindBy(xpath = MOVIE_LOCATOR_IN_MAIN_MENU)
    WebElement movieElement;

    @Step("Наведение на пункт в меню 'КИНО'")
        public MovieBlockInMainMenu hoverMovieElement() throws InterruptedException {

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav/a[contains(., 'КИНО')]")));
        actions.moveToElement(movieElement).build().perform();
        Thread.sleep(1000);

        return this;
    }
    @FindBy(xpath = "//a[contains(., 'Ожидаемые премьеры')]")
    WebElement newsElement;

    @Step("Кликнуть по пункту 'Ожидаемые премьеры' в меню 'КИНО'")
    public ExpectedPremieresPage newsElementClick() {
       newsElement.click();
        return new ExpectedPremieresPage(driver);
    }
}

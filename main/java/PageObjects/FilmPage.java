package PageObjects;


import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class FilmPage extends BaseView {

    public  FilmPage (WebDriver driver) {
        super(driver);
    }

    private final static String SHAIRE_WITH_FRIENDS_BUTTON_LOCATOR =
            "//button [@data-test='BUTTON SHARE-BUTTON']";
    @FindBy(xpath = SHAIRE_WITH_FRIENDS_BUTTON_LOCATOR)
    public WebElement shaireWhithFriendsButton;
@Step("Кликнуть кнопку 'Поделиться с друзьями'")
    public FilmPage shaireWhithFriendsButtonClick()  {

        shaireWhithFriendsButton.click();
        return this;
    }

    private final static String SHAIRE_WITH_FRIENDS_VKONTAKTE_BUTTON_LOCATOR =
            "//div[@data-rambler-share='vkontakte']";
    @FindBy(xpath = SHAIRE_WITH_FRIENDS_VKONTAKTE_BUTTON_LOCATOR)
    public WebElement shaireWhithFriendsvVkontakteButton;

@Step("Выбрать иконку 'ВКонтакте' в блоке 'Поделиться с друзьями'")
    public FilmPage shaireWhithFriendsvVkontakteButtonClick() throws InterruptedException {
        Thread.sleep(2000);
        shaireWhithFriendsvVkontakteButton.click();
        return this;
    }
    @Step("Проверка того, что открылось окно авторизации 'ВКонтакте'")
    public FilmPage checkingLinkClick() {
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        assertThat(driver.getCurrentUrl(), containsString(".vk.com/authorize"));
        driver.close();
        driver.switchTo().window(tabs2.get(0));
        return this;
    }
}

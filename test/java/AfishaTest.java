import PageObjects.*;
import PageObjects.CustomLogger.CustomLogger;
import PageObjects.ExpectedPremieresPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Iterator;


public class AfishaTest {
    WebDriver driver;
    EventFiringWebDriver eventFiringWebDriver;
    MainPage mainPage;
    LoginBlock loginBlock;
    private final static String AFISHA_BASE_URL = "https://afisha.ru";


    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        eventFiringWebDriver = new EventFiringWebDriver(new ChromeDriver());
        // driver = new ChromeDriver();
        eventFiringWebDriver.register(new CustomLogger());
        mainPage = new MainPage(eventFiringWebDriver);
        loginBlock = new LoginBlock(eventFiringWebDriver);
        eventFiringWebDriver.get(AFISHA_BASE_URL);

    }

    @Test
    @Description("Проверка перехода по ссылке в окно авторизации 'Вконтакте' в разделе 'Поделиться с друзьями'")
    @TmsLink("1233212")
    @Feature("Переход по ссылке в соцсети")
    void checkingLinkVkontakte() throws InterruptedException {
        new MainPage(eventFiringWebDriver).clickLoginButton();
        new LoginBlock(eventFiringWebDriver)
                .switchToLoginFrame()
                .fillLoginInput("irinasiv230@mail.ru")
                .fillPasswordInput("QWErty123")
                .submitLoginButtonClick();
        new MovieBlockInMainMenu(eventFiringWebDriver)
                .hoverMovieElement()
                .newsElementClick();
        new ExpectedPremieresPage(eventFiringWebDriver)
                .clickFilm();
        new FilmPage(eventFiringWebDriver)
                .shaireWhithFriendsButtonClick()
                .shaireWhithFriendsvVkontakteButtonClick()
                .checkingLinkClick();


    }

    @Test
    @Description("Проверка функции поиска фильмов")
    @TmsLink("123321")
    @Issue("Чек-лист №35")
    @Story("Основной функционал/ дымовое тестирование")
    void findFilm() throws InterruptedException {
        new MainPage(eventFiringWebDriver).clickLoginButton();
        new LoginBlock(eventFiringWebDriver)
                .switchToLoginFrame()
                .fillLoginInput("irinasiv230@mail.ru")
                .fillPasswordInput("QWErty123")
                .submitLoginButtonClick();
        new MainPage(eventFiringWebDriver)
                .searchButtonClick()
                .searchFilmInput("Человек-паук")
                .startSearchingClick()
                .SearchResultAndAssert();

    }

    @AfterEach
    void tearDown() {
        LogEntries logs = eventFiringWebDriver.manage().logs().get(LogType.BROWSER);
        Iterator<LogEntry> iterator = logs.iterator();
        while (iterator.hasNext()) {
            Allure.addAttachment("Элемент лога браузера", iterator.next().getMessage());
        }
        eventFiringWebDriver.quit();
    }
}




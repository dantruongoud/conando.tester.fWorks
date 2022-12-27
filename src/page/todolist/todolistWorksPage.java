package page.todolist;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import page.Works.createsubWorksPage;

public class todolistWorksPage {
    public WebDriver driver;

    @FindBy(xpath = "//a[@class='has-text-info'][contains(text(),'Todolist liên kết')]")
    private WebElement navigation_todolist;

    @FindBy(css = ".icon.has-text-link")
    private WebElement createBtn;

    @FindBy(id = "task_todo")
    public WebElement title_input;

    @FindBy(xpath = "/html/body/main/section/section[3]/section/section[2]/ul/li[2]/div[2]/ul/li[2]/div/div/div[2]/div/table/tbody/tr[6]/td[1]")
    private WebElement dayStarEnd;

    @FindBy(css = ".icon-text.has-text-link.mr-3")
    private WebElement saveBtn;

    public todolistWorksPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigation() {
        try {
            Thread.sleep(1500);
            navigation_todolist.click();
            Thread.sleep(2000);
            createBtn.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void titleSendkeys(String title) {
        try {
            title_input.sendKeys(title);
            saveBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void choseDay() {
        try {
            createsubWorksPage subworks = new createsubWorksPage(driver);
            subworks.choseDay.click();
            Thread.sleep(1000);
            clickDay();
            subworks.confirmDay.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickDay() {
        try {
            Thread.sleep(1000);
            dayStarEnd.click();
            Thread.sleep(1000);
            dayStarEnd.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

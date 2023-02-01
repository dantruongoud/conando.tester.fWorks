package page.Works;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class createWorksPage {

    public String[] tagline = {
            "Nhập tiêu đề của công việc!",
            "Nhập thời gian thực hiện của công việc!",
            "Chưa chọn nhóm của công việc!",
            "Đã tạo công việc thành công!"
    };
    public WebDriver driver;

    @FindBy(css = ".button.is-small.is-rounded.is-success")
    public WebElement worksBtn;

    @FindBy(id = "task_name")
    public WebElement titleWorks;

    @FindBy(css = "div[class='control is-expanded'] a[class='icon-text'] span[class='icon is-small mr-1']")
    private WebElement choseDay;

    @FindBy(xpath = "//li[@class='column is-10 is-size-7 p-4']/div[3]//td[@class='today  available']")
    private WebElement dayStarEnd;

    @FindBy(xpath = "//li[@class='column is-10 is-size-7 p-4']/div[3]//button[@class='applyBtn button is-small is-link']")
    private WebElement confirmDay;

    @FindBy(xpath = "//span[contains(text(),'Chọn nhóm công việc')]")
    private WebElement clickChoseTask;

    @FindBy(linkText = "Thực thi automation đạt chuẩn")
    private WebElement choseTask;

    @FindBy(css = "a[class='button is-link is-small']")
    public WebElement doneBtn;

    public createWorksPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickchoseDay() {
        try {
            Thread.sleep(1000);
            choseDay.click();
            Thread.sleep(1000);
            dayStarEnd.click();
            Thread.sleep(1000);
            confirmDay.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void choseTaskWorks() {
        try {
            Thread.sleep(1000);
            clickChoseTask.click();
            Thread.sleep(500);
            choseTask.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

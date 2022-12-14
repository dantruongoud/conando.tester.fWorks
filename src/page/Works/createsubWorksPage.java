package page.Works;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class createsubWorksPage {

    public WebDriver driver;

    @FindBy(how = How.CLASS_NAME, using = "item_name")
    private List<WebElement> listWorks;

    @FindBy(xpath = "//a[contains(text(),'Công việc phụ')]")
    private WebElement navigation_subWorks;

    @FindBy(xpath = "//div[@class='py-2']/a[1]")
    public WebElement subWorksBtn;

    @FindBy(css = "input[placeholder='Nhập tiêu đề công việc']")
    public WebElement titleWorks_input;

    @FindBy(xpath = "//span[contains(text(),'Chọn ngày')]")
    public WebElement choseDay;

    @FindBy(xpath = "/html/body/main/section/section[3]/section/section[2]/ul/li[2]/div/ul[2]/li[2]/div/div/div[2]/div/table/tbody/tr[5]/td[1]")
    private WebElement dayStarEnd;

    @FindBy(xpath = "(//button[contains(@type,'button')][contains(text(),'Xác nhận')])[3]")
    public WebElement confirmDay;

    @FindBy(css = ".icon-text.has-text-link.mr-3")
    public WebElement saveBtn;

    @FindBy(xpath = "//body[1]/main[1]/section[1]/section[3]/section[1]/section[2]/ul[1]/li[2]/div[1]/ul[2]/li[3]/div[1]/div[1]")
    public WebElement add_user;

    public createsubWorksPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void choseWorks() {
        try {
            for (WebElement row : listWorks) {
                String nameWorks = row.getText().strip();
                if (nameWorks.equals("Tiêu đề công việc")) {
                    row.click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickchoseDay() {
        try {
            Thread.sleep(1000);
            dayStarEnd.click();
            Thread.sleep(1000);
            dayStarEnd.click();
            Thread.sleep(1000);
            confirmDay.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void formsubWorks() {
        try {
            Thread.sleep(1500);
            navigation_subWorks.click();
            Thread.sleep(2000);
            subWorksBtn.click();
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package page.Planning;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class createPlanPage {

    public String[] tagline = {
            "Kế hoạch chưa có thành viên.",
            "Bạn chưa nhập tiêu đề kế hoạch."
    };
    public WebDriver driver;

    @FindBy(xpath = "//a[@class='button is-info is-fullwidth']")
    public WebElement createBtn;

    @FindBy(xpath = "//section[@class='modal-card-head']")
    public WebElement modal;

    @FindBy(id = "plan_name")
    public WebElement title_input;

    @FindBy(css = ".icon.is-small.mr-1")
    public WebElement date_end;

    @FindBy(tagName = "tbody")
    public WebElement table;

    @FindBy(xpath = "//td[normalize-space()='29']")
    public WebElement day;

    @FindBy(xpath = "//span[contains(text(),'Hoàn tất')]")
    public WebElement donePlan;

    @FindBy(xpath = "//div[contains(@class,'buttons is-right')]//a[contains(@class,'button is-link is-small')]")
    public WebElement done_user;

    @FindBy(xpath = "//i[normalize-space()='remove_circle_outline']")
    public WebElement remove_user;

    @FindBy(xpath = "(//ul[contains(@class,'columns is-vcentered is-variable is-1')])[1]")
    private WebElement user1;

    @FindBy(how = How.XPATH, using = "//a[contains(@class,'dropdown-item py-1')]")
    private List<WebElement> listPosition;

    @FindBy(id = "search_member")
    private WebElement search_member;

    @FindBy(xpath = "(//a[@class='icon is-small has-text-info'])[1]")
    private WebElement chose_user;

    @FindBy(tagName = "textarea")
    public WebElement txaDescription;

    @FindBy(css = "div[class='dropdown is-right is-fullwidth '] i[class='material-icons-outlined is-size-6']")
    public WebElement btnAddUser;

    public createPlanPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void chosePosition(String codition) {
        try {
            Thread.sleep(1000);
            WebElement chosePosition = user1.findElement(By.className("dropdown-trigger"));
            chosePosition.click();
            Thread.sleep(1000);
            for (WebElement row : listPosition) {
                String namePosition = row.getText().strip();
                if (namePosition.equals(codition)) {
                    row.click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUser() {
        try {
            Thread.sleep(1000);
            remove_user.click();
            Thread.sleep(1000);
            done_user.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void choseMember(String condition) {
        try {
            Thread.sleep(500);
            search_member.sendKeys(condition);
            Thread.sleep(1000);
            chose_user.click();
            Thread.sleep(1000);
            chosePosition("Quản lý");
            Thread.sleep(1000);
            done_user.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void txtClear() {
        title_input.clear();
        txaDescription.clear();
    }
}

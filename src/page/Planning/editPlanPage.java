package page.Planning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class editPlanPage {

    public WebDriver driver;

    @FindBy(css = ".icon-text.has-text-link")
    public WebElement editBtn;

    @FindBy(css = "#plan_name")
    public WebElement plan_input;

    @FindBy(css = "a[class='button is-link is-small']")
    private WebElement saveBtn;

    public editPlanPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void edit_plan(String nameplan) {
        try {
            plan_input.sendKeys(nameplan);
            saveBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

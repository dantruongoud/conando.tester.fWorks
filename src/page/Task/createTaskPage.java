package page.Task;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class createTaskPage {
    public WebDriver driver;

    @FindBy(how = How.CSS, using = "a[class='has-text-info']")
    private List<WebElement> listWorks;

    @FindBy(xpath = "//a[@class='has-text-black'][contains(.,'Công việc')]")
    public WebElement navigation_task;

    @FindBy(id = "plan_bubble")
    public WebElement dropdown_task;

    @FindBy(css = ".button.is-small.is-rounded.is-info")
    private WebElement createTask;

    @FindBy(css = ".button.is-small.is-rounded.is-success")
    private WebElement createWork;

    @FindBy(css = "input[placeholder='Nhập tiêu đề nhóm công việc...']")
    public WebElement titleTask;

    @FindBy(xpath = "//i[normalize-space()='done']")
    public WebElement doneTask;

    public createTaskPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void choseWorks() {
        try {
            for (WebElement row : listWorks) {
                String nameWorks = row.getText().strip();
                if (nameWorks.equals("fWorks: Prepare for Testing")) {
                    row.click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createtask() {
        try {
            if (createTask.isDisplayed()) {
                createTask.click();
            } else {
                dropdown_task.click();
                Thread.sleep(500);
                createTask.click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void creatework() {
        try {
            if (createWork.isDisplayed()) {
                createWork.click();
            } else {
                dropdown_task.click();
                Thread.sleep(500);
                createWork.click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package page.Comment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class sendCommentPage {

    public WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Bình luận')]")
    public WebElement navigation_cmt;

    @FindBy(id = "task_comment")
    private WebElement cmt_input;

    @FindBy(css = ".icon.is-right.has-text-link")
    private WebElement sendBtn;

    @FindBy(css = ".has-text-grey.pl-5.ml-2.mt-1")
    public WebElement result;

    public sendCommentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void comment(String cmt) {
        try {
            cmt_input.sendKeys(cmt);
            sendBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verifyResult(String condition) {
        try {
            String nameResult = result.getText().strip();
            return nameResult.equals(condition);
        } catch (Exception e) {
            return false;
        }
    }
}

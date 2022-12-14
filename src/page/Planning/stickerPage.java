package page.Planning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class stickerPage {
    WebDriver driver;

    @FindBy(css = "input[placeholder='Nhập tiêu đề nhãn']")
    private WebElement title_input;

    @FindBy(css = "a[class='button is-link']")
    private WebElement addBtn;

    public stickerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void createStick(String title) {
        try {
            title_input.sendKeys(title);
            addBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

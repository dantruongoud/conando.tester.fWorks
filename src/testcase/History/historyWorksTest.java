package testcase.History;

import org.openqa.selenium.WebDriver;

import page.Works.*;
import page.indexPage;
import page.History.historyPage;
import page.Task.createTaskPage;
import setupbase.baseSetup;

public class historyWorksTest {
    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            indexPage index = new indexPage(driver);
            createTaskPage taskPage = new createTaskPage(driver);
            createsubWorksPage subWorks = new createsubWorksPage(driver);
            historyPage history = new historyPage(driver);

            index.waitForPageLoaded();
            index.login();

            index.navigation_works.click();
            index.waitForPageLoaded();

            taskPage.choseWorks("Testing sản phẩm");
            taskPage.navigation_task.click();
            index.waitForPageLoaded();
            if (index.verifyTitle("Danh sách công việc")) {
                subWorks.choseWorks();
                Thread.sleep(2000);
                history.navigation_history.click();
                index.waitForPageLoaded();
                history.getTitleTask();
            } else {
                index.error_titlePage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

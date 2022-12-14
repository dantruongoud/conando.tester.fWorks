package testcase.History;

import org.openqa.selenium.WebDriver;

import page.indexPage;
import page.History.historyPage;
import page.Task.createTaskPage;
import setupbase.baseSetup;

public class historyPlanTest {
    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            indexPage index = new indexPage(driver);
            createTaskPage taskPage = new createTaskPage(driver);
            historyPage history = new historyPage(driver);

            index.waitForPageLoaded();
            index.login();

            index.navigation_works.click();
            index.waitForPageLoaded();

            taskPage.choseWorks("Testing sản phẩm");
            index.waitForPageLoaded();
            if (index.verifyTitle("Tổng quan kế hoạch")) {
                history.getTitleTask();
            } else {
                index.error_titlePage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

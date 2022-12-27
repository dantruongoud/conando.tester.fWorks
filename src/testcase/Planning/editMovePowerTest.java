package testcase.Planning;

import org.openqa.selenium.WebDriver;

import page.Task.*;
import page.indexPage;
import page.Planning.editMovePowerPage;
import setupbase.baseSetup;

public class editMovePowerTest {
    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            indexPage index = new indexPage(driver);
            createTaskPage taskPage = new createTaskPage(driver);
            editMovePowerPage edit = new editMovePowerPage(driver);

            index.waitForPageLoaded();
            index.login();
            index.navigation_works.click();
            index.waitForPageLoaded();

            taskPage.choseWorks();
            index.waitForPageLoaded();

            if (index.verifyTitle("Tổng quan kế hoạch")) {

                edit.addUser.click();
                Thread.sleep(1000);
                edit.getUser.click();
                Thread.sleep(1000);
                edit.chosePower.click();
                Thread.sleep(1000);
                edit.savePower.click();

                String noti = index.tagline();
                if (noti.equals("Bạn không thể xóa hết quản lý của kế hoạch.")) {
                    System.out.println("===================");
                    System.out.println(noti);
                    index.passed();
                } else {
                    index.failed();
                }
            } else {
                index.error_titlePage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

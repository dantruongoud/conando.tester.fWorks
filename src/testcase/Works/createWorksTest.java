package testcase.Works;

import org.openqa.selenium.WebDriver;

import excelHelpers.excelhelpers;
import page.Works.*;
import page.indexPage;
import page.Task.createTaskPage;
import setupbase.baseSetup;

public class createWorksTest {
    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            indexPage index = new indexPage(driver);
            createTaskPage taskPage = new createTaskPage(driver);
            createWorksPage worksPage = new createWorksPage(driver);
            excelhelpers excel = new excelhelpers();
            excel.setExcelSheet("Works");

            index.waitForPageLoaded();
            index.login();

            index.navigation_works.click();
            index.waitForPageLoaded();

            taskPage.choseWorks();
            taskPage.navigation_task.click();
            index.waitForPageLoaded();

            taskPage.creatework();
            Thread.sleep(1000);

            for (int i = 1; i < 5; i++) {

                System.out.println("===================");

                System.out.println("Test Case: " + excel.getCellData("TCID", i));
                worksPage.titleWorks.sendKeys(excel.getCellData("title", i));
                worksPage.doneBtn.click();
                Thread.sleep(1000);

                String noti = index.tagline();
                switch (noti) {
                    case "Nhập tiêu đề của công việc!":
                        System.out.println(noti);
                        index.passed();
                        break;
                    case "Nhập thời gian thực hiện của công việc!":
                        System.out.println(noti);
                        index.passed();
                        worksPage.clickchoseDay();
                        worksPage.titleWorks.clear();
                        break;
                    case "Chưa chọn nhóm của công việc!":
                        System.out.println(noti);
                        index.passed();
                        worksPage.choseTaskWorks();
                        worksPage.titleWorks.clear();
                        break;
                    default:
                        if (noti.equals("Đã tạo công việc thành công!")) {
                            System.out.println(noti);
                            index.passed();
                        } else {
                            System.out.println(noti);
                            index.failed();
                        }
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

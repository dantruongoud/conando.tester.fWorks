package testcase.Task;

import org.openqa.selenium.WebDriver;

import excelHelpers.excelhelpers;
import page.Task.*;
import page.indexPage;
import setupbase.baseSetup;

public class createTaskTest {
    int testcase;
    String titleTask;

    public createTaskTest(int testcase, String titleTask) {
        this.testcase = testcase;
        this.titleTask = titleTask;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            indexPage index = new indexPage(driver);
            createTaskPage taskPage = new createTaskPage(driver);
            excelhelpers excel = new excelhelpers();
            excel.setExcelSheet("Task");

            index.login();
            index.navigation_works.click();
            index.waitForPageLoaded();

            taskPage.choseWorks();

            taskPage.navigation_task.click();
            index.waitForPageLoaded();

            if (index.verifyTitle("Danh sách công việc")) {
                taskPage.createtask();

                for (int i = 1; i < 3; i++) {

                    System.out.println("===================");

                    System.out.println("Testcase: " + excel.getCellData("TCID", i));
                    taskPage.titleTask.sendKeys(excel.getCellData("title", i));
                    taskPage.doneTask.click();

                    Thread.sleep(1000);
                    String noti = index.tagline();
                    switch (noti) {
                        case "Bạn chưa nhập tiêu đề nhóm công việc!":
                            System.out.println(noti);
                            index.passed();
                            break;
                        default:
                            if (noti.equals("Đã thêm nhóm công việc!")) {
                                System.out.println(noti);
                                index.passed();
                            } else {
                                index.failed();
                            }
                            break;
                    }
                }

            } else {
                index.error_titlePage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

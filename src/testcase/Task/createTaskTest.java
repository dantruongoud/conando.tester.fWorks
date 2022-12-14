package testcase.Task;

import org.openqa.selenium.WebDriver;

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
            index.waitForPageLoaded();
            index.login();
            index.navigation_works.click();
            index.waitForPageLoaded();

            taskPage.choseWorks("Testing sản phẩm");

            taskPage.navigation_task.click();
            index.waitForPageLoaded();
            if (index.verifyTitle("Danh sách công việc")) {
                taskPage.createtask();
                createTaskTest[] data = {
                        new createTaskTest(1, ""),
                        new createTaskTest(2, "Công việc nhóm QC")
                };
                for (int i = 0; i < data.length; i++) {
                    System.out.println("===================");
                    System.out.println("Testcase: " + data[i].testcase);
                    taskPage.titleTask.sendKeys(data[i].titleTask);
                    taskPage.doneTask.click();
                    Thread.sleep(1000);
                    String noti = index.tagline();
                    switch (noti) {
                        case "Bạn chưa nhập tiêu đề nhóm công việc!":
                            System.out.println(noti);
                            System.out.println("PASSED");
                            System.out.println("===================");
                            break;
                        default:
                            noti = index.tagline();
                            if (noti.equals("Đã thêm nhóm công việc!")) {
                                System.out.println(noti);
                                System.out.println("PASSED");
                                System.out.println("===================");
                            } else {
                                System.out.println("Tagline is not Displayed...");
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

package testcase.Works;

import org.openqa.selenium.WebDriver;

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

            index.waitForPageLoaded();
            index.login();

            index.navigation_works.click();
            index.waitForPageLoaded();

            taskPage.choseWorks("Testing sản phẩm");
            taskPage.navigation_task.click();
            index.waitForPageLoaded();
            if (index.verifyTitle("Danh sách công việc")) {
                taskPage.dropdown_task.click();
                Thread.sleep(1000);
                worksPage.worksBtn.click();
                index.waitForPageLoaded();
                System.out.println("===================");
                System.out.println("Testcase: 1");
                worksPage.doneBtn.click();
                String noti = index.tagline();
                if (noti.equals("Nhập tiêu đề của công việc")) {
                    System.out.println(noti);
                    index.passed();
                    worksPage.titleWorks.sendKeys("Tiêu đề công việc");
                    Thread.sleep(1000);
                    worksPage.doneBtn.click();
                    System.out.println("===================");
                    System.out.println("Testcase: 2");
                    Thread.sleep(1000);
                    noti = index.tagline();
                    if (noti.equals("Nhập thời gian thực hiện của công việc.")) {
                        System.out.println(noti);
                        index.passed();
                        worksPage.clickchoseDay();
                        Thread.sleep(1000);
                        worksPage.doneBtn.click();
                        System.out.println("===================");
                        System.out.println("Testcase: 4");
                        Thread.sleep(1000);
                        noti = index.tagline();
                        if (noti.equals("Chọn nhóm của công việc.")) {
                            System.out.println(noti);
                            index.passed();
                            worksPage.choseTaskWorks();
                            worksPage.doneBtn.click();
                            System.out.println("===================");
                            System.out.println("Testcase: 5");
                            Thread.sleep(1000);
                            noti = index.tagline();
                            if (noti.equals("Đã tạo công việc thành công!")) {
                                System.out.println(noti);
                                index.passed();
                            } else {
                                index.failed();
                            }
                        } else {
                            index.failed();
                        }
                    } else {
                        index.failed();
                    }

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

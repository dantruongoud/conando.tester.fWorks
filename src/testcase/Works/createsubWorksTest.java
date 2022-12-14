package testcase.Works;

import org.openqa.selenium.WebDriver;

import page.Task.*;
import page.Works.createsubWorksPage;
import page.indexPage;
import setupbase.baseSetup;

public class createsubWorksTest {
    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            indexPage index = new indexPage(driver);
            createTaskPage taskPage = new createTaskPage(driver);
            createsubWorksPage subWorks = new createsubWorksPage(driver);

            index.waitForPageLoaded();
            index.login();
            index.navigation_works.click();
            index.waitForPageLoaded();
            taskPage.choseWorks("Testing sản phẩm");
            taskPage.navigation_task.click();
            index.waitForPageLoaded();
            if (index.verifyTitle("Danh sách công việc")) {
                subWorks.choseWorks();
                subWorks.formsubWorks();

                subWorks.saveBtn.click();
                System.out.println("====================");
                System.out.println("Testcase: 1");
                String noti = index.tagline();
                if (noti.equals("Nhập tiêu đề của công việc")) {
                    System.out.println(noti);
                    index.passed();
                    subWorks.titleWorks_input.sendKeys("Tiêu đề công việc phụ");
                    System.out.println("====================");
                    System.out.println("Testcase: 2");
                    subWorks.saveBtn.click();
                    Thread.sleep(1000);
                    noti = index.tagline();
                    if (noti.equals("Nhập thời gian thực hiện của công việc.")) {
                        System.out.println(noti);
                        index.passed();
                        subWorks.choseDay.click();
                        subWorks.clickchoseDay();
                        subWorks.saveBtn.click();
                        System.out.println("====================");
                        System.out.println("Testcase: 3");
                        Thread.sleep(1000);
                        noti = index.tagline();
                        if (noti.equals("Chọn người tham gia của công việc.")) {
                            System.out.println(noti);
                            index.passed();
                            subWorks.add_user.click();
                            Thread.sleep(500);
                            index.choseMember("truong");
                            Thread.sleep(1000);
                            System.out.println("====================");
                            System.out.println("Testcase: 4");
                            subWorks.saveBtn.click();
                            Thread.sleep(1000);
                            noti = index.tagline();
                            if (noti.length() == 0) {
                                System.out.println("Tạo mới thành công");
                                index.passed();
                            } else {
                                System.out.println(noti);
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

package testcase.Planning;

import org.openqa.selenium.WebDriver;

import page.indexPage;
import page.Planning.stickerPage;
import page.Task.createTaskPage;
import setupbase.baseSetup;

public class stickerTest {
    int testcase;
    String title;

    public stickerTest(int testcase, String title) {
        this.testcase = testcase;
        this.title = title;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            indexPage index = new indexPage(driver);
            createTaskPage taskPage = new createTaskPage(driver);
            stickerPage stick = new stickerPage(driver);

            index.waitForPageLoaded();
            index.login();
            index.navigation_works.click();
            index.waitForPageLoaded();

            taskPage.choseWorks("Testing sản phẩm");
            index.waitForPageLoaded();
            if (index.verifyTitle("Tổng quan kế hoạch")) {

                stickerTest[] data = {
                        new stickerTest(1, ""),
                        new stickerTest(2, "QC")
                };

                for (int i = 0; i < data.length; i++) {

                    System.out.println("====================");
                    System.out.println("Testcase: " + data[i].testcase);
                    stick.createStick(data[i].title);
                    Thread.sleep(1000);

                    String noti = index.tagline();
                    switch (noti) {
                        case "Bạn chưa nhập tiêu đề nhãn.":
                            System.out.println(noti);
                            index.passed();
                            break;
                        default:
                            noti = index.tagline();
                            if (noti.equals("Đã cập nhật nhãn công việc.")) {
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

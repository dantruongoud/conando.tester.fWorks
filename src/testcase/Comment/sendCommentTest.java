package testcase.Comment;

import org.openqa.selenium.WebDriver;

import page.indexPage;
import page.Comment.sendCommentPage;
import page.Task.createTaskPage;
import page.Works.createsubWorksPage;
import setupbase.baseSetup;

public class sendCommentTest {
    int testcase;
    String cmt;

    public sendCommentTest(int testcase, String cmt) {
        this.testcase = testcase;
        this.cmt = cmt;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            indexPage index = new indexPage(driver);
            createTaskPage taskPage = new createTaskPage(driver);
            createsubWorksPage subWorks = new createsubWorksPage(driver);
            sendCommentPage sendCmt = new sendCommentPage(driver);

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
                sendCmt.navigation_cmt.click();
                index.waitForPageLoaded();

                sendCommentTest[] data = {
                        new sendCommentTest(1, ""),
                        new sendCommentTest(2, "Tôi đã Comment")
                };

                for (int i = 0; i < data.length; i++) {
                    System.out.println("====================");

                    System.out.println("Testcase: " + data[i].testcase);
                    sendCmt.comment(data[i].cmt);
                    Thread.sleep(2000);

                    String noti = index.tagline();

                    switch (noti) {
                        case "Bạn chưa nhập nội dung bình luận!":
                            System.out.println(noti);
                            index.passed();
                            break;
                        default:
                            if (sendCmt.verifyResult("Tôi đã Comment")) {
                                System.out.println("Comment Success");
                                index.passed();
                            } else {
                                index.failed();
                            }
                            break;
                    }

                    Thread.sleep(1000);
                }
                
            } else {
                index.error_titlePage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

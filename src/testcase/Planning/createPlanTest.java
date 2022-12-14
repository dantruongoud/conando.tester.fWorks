package testcase.Planning;

import org.openqa.selenium.WebDriver;

import page.indexPage;
import page.Planning.*;
import setupbase.baseSetup;

public class createPlanTest {
    int testcase;
    String title, description;

    public createPlanTest(int testcase, String title, String description) {
        this.testcase = testcase;
        this.title = title;
        this.description = description;
    }

    public static void main(String[] args) {
        try {
            
            createPlanTest[] data = {
                    new createPlanTest(1, "fWorks: Prepare for Testing", "Đây là dự án cực kỳ quan trọng"),
                    new createPlanTest(2, "", "Đây là dự án cực kỳ quan trọng"),
                    new createPlanTest(3, "fWorks: Prepare for Testing", "Đây là dự án cực kỳ quan trọng"),
            };

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            indexPage index = new indexPage(driver);
            createPlanPage create = new createPlanPage(driver);

            index.waitForPageLoaded();
            index.login();

            index.navigation_works.click();
            index.waitForPageLoaded();

            if (index.verifyTitle("Công việc của tôi")) {

                create.createBtn.click();
                Thread.sleep(1500);
                index.add_user.click();
                create.removeUser();

                for (int i = 0; i < data.length; i++) {

                    System.out.println("====================");

                    System.out.println("Testcase: " + data[i].testcase);
                    create.title_input.sendKeys(data[i].title);
                    create.txaDescription.sendKeys(data[i].description);
                    create.donePlan.click();
                    Thread.sleep(1000);

                    String noti = index.tagline();
                    switch (noti) {

                        case "Kế hoạch chưa có thành viên.":
                            System.out.println(noti);
                            index.passed();
                            index.add_user.click();
                            create.choseMember("truong");
                            create.txtClear();
                            break;

                        case "Bạn chưa nhập tiêu đề kế hoạch.":
                            System.out.println(noti);
                            index.passed();
                            create.txtClear();
                            break;

                        default:
                            noti = index.tagline();
                            if (noti.equals("Đã tạo kế hoạch: fWorks: Prepare for Testing")) {
                                System.out.println(noti);
                                index.passed();
                            } else {
                                System.out.println(noti);
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

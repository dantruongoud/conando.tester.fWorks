package testcase.Planning;

import org.openqa.selenium.WebDriver;

import page.indexPage;
import page.Planning.editPlanPage;
import page.Task.createTaskPage;
import setupbase.baseSetup;

public class editPlanTest {
    int testcase;
    String nameplan;

    public editPlanTest(int testcase, String nameplan) {
        this.testcase = testcase;
        this.nameplan = nameplan;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            indexPage index = new indexPage(driver);
            index.waitForPageLoaded();
            createTaskPage taskPage = new createTaskPage(driver);
            editPlanPage editPlan = new editPlanPage(driver);

            index.login();
            index.navigation_works.click();
            index.waitForPageLoaded();

            taskPage.choseWorks();
            index.waitForPageLoaded();

            if (index.verifyTitle("Tổng quan kế hoạch")) {
                editPlan.editBtn.click();
                Thread.sleep(1000);
                editPlanTest[] data = {
                        new editPlanTest(1, ""),
                        new editPlanTest(2, "fWorks: Prepare for Testing")
                };
                editPlan.plan_input.clear();
                for (int i = 0; i < data.length; i++) {
                    System.out.println("===================");
                    System.out.println("Testcase: " + data[i].testcase);
                    editPlan.edit_plan(data[i].nameplan);
                    Thread.sleep(1000);
                    String noti = index.tagline();
                    switch (noti) {
                        case "Bạn chưa nhập tiêu đề kế hoạch!":
                            System.out.println(noti);
                            index.passed();
                            break;
                        default:
                            if (noti.equals("Đã cập nhật thông tin của dự án.")) {
                                System.out.println(noti);
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

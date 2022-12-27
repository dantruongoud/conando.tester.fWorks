package testcase.Planning;

import org.openqa.selenium.WebDriver;

import excelHelpers.excelhelpers;
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

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            indexPage index = new indexPage(driver);
            createPlanPage create = new createPlanPage(driver);
            excelhelpers excel = new excelhelpers();
            excel.setExcelSheet("Create Plan");

            index.waitForPageLoaded();
            index.login();

            index.navigation_works.click();
            index.waitForPageLoaded();

            if (index.verifyTitle("Công việc của tôi")) {

                create.createBtn.click();
                Thread.sleep(1500);
                index.add_user.click();
                create.removeUser();

                for (int i = 1; i < 4; i++) {

                    System.out.println("====================");

                    System.out.println("Testcase: " + excel.getCellData("TCID", i));
                    create.title_input.sendKeys(excel.getCellData("title", i));
                    create.txaDescription.sendKeys(excel.getCellData("description", i));
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
                            if (noti.equals("Đã tạo kế hoạch: " + excel.getCellData("title", 3))) {
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

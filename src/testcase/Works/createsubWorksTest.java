package testcase.Works;

import org.openqa.selenium.WebDriver;

import excelHelpers.excelhelpers;
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
            excelhelpers excel = new excelhelpers();
            excel.setExcelSheet("subWorks");

            index.login();
            index.navigation_works.click();
            index.waitForPageLoaded();

            taskPage.choseWorks();
            taskPage.navigation_task.click();
            index.waitForPageLoaded();

            if (index.verifyTitle("Danh sách công việc")) {

                subWorks.choseWorks();
                subWorks.formsubWorks();

                for (int i = 1; i < 5; i++) {
                    System.out.println("====================");

                    System.out.println("Testcase: " + excel.getCellData("TCID", i));
                    subWorks.setText(excel.getCellData("title", i), excel.getCellData("description", i));
                    Thread.sleep(1000);

                    String noti = index.tagline();
                    switch (noti) {
                        case "Nhập tiêu đề của công việc":
                            System.out.println(noti);
                            index.passed();
                            subWorks.clear();
                            break;
                        case "Nhập thời gian thực hiện của công việc.":
                            System.out.println(noti);
                            index.passed();
                            subWorks.clickchoseDay();
                            subWorks.clear();
                            break;
                        case "Chọn người tham gia của công việc.":
                            System.out.println(noti);
                            index.passed();
                            subWorks.add_user.click();
                            index.choseMember("truong");
                            subWorks.clear();
                            break;
                        default:
                            if (subWorks.verifySubwork("Công việc phụ số 1")) {
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

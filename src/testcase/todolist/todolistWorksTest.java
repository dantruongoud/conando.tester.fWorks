package testcase.todolist;

import org.openqa.selenium.WebDriver;

import excelHelpers.excelhelpers;
import page.indexPage;
import page.Task.createTaskPage;
import page.Works.createsubWorksPage;
import page.todolist.todolistWorksPage;
import setupbase.baseSetup;

public class todolistWorksTest {
    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            indexPage index = new indexPage(driver);
            createTaskPage taskPage = new createTaskPage(driver);
            createsubWorksPage subWorks = new createsubWorksPage(driver);
            todolistWorksPage linkTodolist = new todolistWorksPage(driver);
            excelhelpers excel = new excelhelpers();
            excel.setExcelSheet("todolist");

            index.login();
            index.navigation_works.click();
            index.waitForPageLoaded();

            taskPage.choseWorks();
            taskPage.navigation_task.click();
            index.waitForPageLoaded();

            if (index.verifyTitle("Danh sách công việc")) {
                subWorks.choseWorks();
                linkTodolist.navigation();
                for (int i = 1; i < 4; i++) {
                    System.out.println("====================");
                    System.out.println("Testcase: " + excel.getCellData("TCID", i));
                    linkTodolist.title_input.clear();

                    linkTodolist.titleSendkeys(excel.getCellData("title", i));

                    Thread.sleep(1000);

                    Boolean passed = false;
                    String noti = index.tagline();
                    for (int j = 0; j < linkTodolist.tagline.length; j++) {
                        if (noti.equals(linkTodolist.tagline[j])) {
                            passed = true;
                            index.passed();
                            if (j == 1)
                                linkTodolist.choseDay();
                            break;
                        } else if (noti.equals(linkTodolist.tagline[2])) {
                            passed = true;
                            index.passed();
                            break;
                        }
                    }
                    if (!passed)
                        index.failed();
                    // switch (noti) {
                    // case "Nhập tiêu đề của Todolist":
                    // System.out.println(noti);
                    // index.passed();
                    // linkTodolist.title_input.clear();
                    // break;
                    // case "Chọn ngày tạo Todolist":
                    // System.out.println(noti);
                    // index.passed();
                    // linkTodolist.title_input.clear();
                    // linkTodolist.choseDay();
                    // break;
                    // default:
                    // if (noti.equals("Đã tạo Todolist liên kết với công việc")) {
                    // System.out.println(noti);
                    // index.passed();
                    // } else {
                    // System.out.println(noti);
                    // index.failed();
                    // }
                    // break;
                    // }
                    // Thread.sleep(1000);
                }
            } else {
                index.error_titlePage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

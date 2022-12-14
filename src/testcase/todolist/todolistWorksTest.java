package testcase.todolist;

import org.openqa.selenium.WebDriver;

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

            index.waitForPageLoaded();
            index.login();
            index.navigation_works.click();
            index.waitForPageLoaded();
            taskPage.choseWorks("Testing sản phẩm");
            taskPage.navigation_task.click();
            index.waitForPageLoaded();
            if (index.verifyTitle("Danh sách công việc")) {
                subWorks.choseWorks();
                linkTodolist.navigation();

                subWorks.saveBtn.click();
                System.out.println("====================");
                System.out.println("Testcase: 1");
                Thread.sleep(1000);
                String noti = index.tagline();
                if (noti.equals("Nhập tiêu đề của Todolist")) {
                    System.out.println(noti);
                    index.passed();
                    linkTodolist.title_input.sendKeys("todolist liên kết");
                    Thread.sleep(1000);
                    subWorks.saveBtn.click();
                    System.out.println("====================");
                    System.out.println("Testcase: 2");
                    Thread.sleep(1000);
                    noti = index.tagline();
                    if (noti.equals("Chọn ngày tạo Todolist")) {
                        System.out.println(noti);
                        index.passed();
                        Thread.sleep(1000);
                        subWorks.choseDay.click();
                        linkTodolist.clickDay();
                        subWorks.confirmDay.click();
                        subWorks.saveBtn.click();
                        Thread.sleep(1000);
                        System.out.println("====================");
                        System.out.println("Testcase: 3");
                        noti = index.tagline();
                        if (noti.equals("Đã tạo Todolist liên kết với công việc")) {
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
                index.error_titlePage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

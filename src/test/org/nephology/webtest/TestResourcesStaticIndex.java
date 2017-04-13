package org.nephology.webtest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class TestResourcesStaticIndex {

    private static HtmlUnitDriver driver;
    private static StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void setUp() throws Exception {
        driver = new HtmlUnitDriver(true);
        String target = "https://nephology-test.herokuapp.com";

        driver.navigate().to(target);

        System.out.println("Goto " + target);
    }

    @Test
    public void testResourcesStaticIndex() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.titleContains("Login"));
        System.out.println(driver.getCurrentUrl());
        // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
        assertEquals("Login", driver.getTitle());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.close();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }
}

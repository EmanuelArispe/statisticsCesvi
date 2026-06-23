package com.statisticsCesvi;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class Driver {

    private static Driver instancia;

    private WebDriver driver;
    private Map<String, Object> vars;
    private JavascriptExecutor js;

    Driver() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();
    }

    public static Driver getInstancia() {

        if (instancia == null) {
            instancia = new Driver();
        }

        return instancia;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }

        instancia = null;
    }
}

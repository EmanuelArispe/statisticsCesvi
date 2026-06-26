package com.statisticsCesvi;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    public static final String DOWNLOAD_DIR =
            System.getProperty("user.home") + File.separator + "Downloads";

    private static Driver instancia;

    private WebDriver driver;
    private Map<String, Object> vars;
    private JavascriptExecutor js;

    Driver() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", DOWNLOAD_DIR);
        profile.setPreference("browser.download.useDownloadDir", true);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/vnd.ms-excel,application/msexcel,application/excel");
        profile.setPreference("browser.download.manager.showWhenStarting", false);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        driver = new FirefoxDriver(options);
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

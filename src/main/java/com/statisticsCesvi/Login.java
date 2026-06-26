package com.statisticsCesvi;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {

  public void loginUser(WebDriver driver, String URL, String USERNAME, String PASSWORD) {
      driver.get(URL);
      driver.manage().window().maximize();

      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

      WebElement docField = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtNroDoc")));
      docField.click();
      docField.sendKeys(USERNAME);

      WebElement passField = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtPass")));
      passField.click();
      passField.sendKeys(PASSWORD);

      wait.until(ExpectedConditions.elementToBeClickable(By.id("btnIngresar"))).click();
  }

  public void getManagement(WebDriver driver) {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
      wait.until(ExpectedConditions.elementToBeClickable(By.id("imgOrionGestion"))).click();
      wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAceptar"))).click();
  }
}

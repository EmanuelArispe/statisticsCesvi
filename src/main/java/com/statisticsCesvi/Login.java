package com.statisticsCesvi;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class Login {


  public void loginUser(WebDriver driver,String URL, String USERNAME, String PASSWORD) {
      driver.get(URL);
      driver.manage().window().setSize(new Dimension(1936, 1048));
      driver.findElement(By.id("txtNroDoc")).click();
      driver.findElement(By.id("txtNroDoc")).sendKeys(USERNAME);
      driver.findElement(By.id("txtPass")).click();
      driver.findElement(By.id("txtPass")).sendKeys(PASSWORD);
      driver.findElement(By.id("btnIngresar")).click();
      WebElement element = driver.findElement(By.id("btnIngresar"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
  }

  public void getManagement(WebDriver driver) {
    driver.findElement(By.id("imgOrionGestion")).click();
    driver.findElement(By.id("btnAceptar")).click();
  }
}

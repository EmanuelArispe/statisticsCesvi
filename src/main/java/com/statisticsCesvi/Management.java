package com.statisticsCesvi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Management {

    public void managementDownload(WebDriver driver,String startDate, String endDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loadPersonal(wait);
        loadGroup(wait);
        loadProvincia(wait);
        loadCity(driver);
        loadStartDate(wait,startDate);
    }

    private void loadPersonal(WebDriverWait wait) {
        WebElement peritosElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("lstPeritos"))
        );

        Select peritos = new Select(peritosElement);
        peritos.selectByVisibleText("ARISPE EMANUEL");
    }

    private void loadGroup(WebDriverWait wait) {
        WebElement grupoElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("lstGrupoPeritos"))
        );

        Select grupos = new Select(grupoElement);
        grupos.selectByVisibleText("Zona10-Chiappanni");
    }

    private void loadProvincia(WebDriverWait wait) {
        WebElement provinciaElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("ddlProvincia"))
        );

        Select provincia = new Select(provinciaElement);
        provincia.selectByVisibleText("Buenos Aires");
    }

    private void loadCity(WebDriver driver) {
        WebDriverWait waitLocalidad = new WebDriverWait(driver, Duration.ofSeconds(10));

        waitLocalidad.until(driver1 -> {
            Select localidadSelect = new Select(
                    driver1.findElement(By.id("ddlLocalidad"))
            );

            return localidadSelect.getOptions()
                    .stream()
                    .anyMatch(option ->
                            option.getText().trim().equals("Tandil"));
        });

        WebElement localidadElement = waitLocalidad.until(
                ExpectedConditions.presenceOfElementLocated(By.id("ddlLocalidad"))
        );
        Select localidad = new Select(localidadElement);
        localidad.selectByVisibleText("Tandil");
    }

    private void loadStartDate(WebDriverWait wait, String startDate) {
        WebElement dateElement = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("MainContent_txtFechaInformeDesde"))
        );

        dateElement.clear();
        dateElement.sendKeys(startDate);
    }

    private void loadEndDate(WebDriverWait wait, String endDate) {
        WebElement dateElement = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("MainContent_txtFechaInformeHasta"))
        );

        dateElement.clear();
        dateElement.sendKeys(endDate);
    }

}
    /*
    driver.findElement(By.id("MainContent_txtFechaInformeDesde")).click();
    driver.findElement(By.id("MainContent_txtFechaInformeHasta")).click();
    driver.findElement(By.id("MainContent_ddlAmpliacion")).click();
    {
      WebElement dropdown = driver.findElement(By.id("MainContent_ddlAmpliacion"));
      dropdown.findElement(By.xpath("//option[. = 'Sin Ampliación']")).click();
    }
    driver.findElement(By.cssSelector("#MainContent_ddlAmpliacion > option:nth-child(3)")).click();
    driver.findElement(By.id("chkTipoPeritacionList_0")).click();
    driver.findElement(By.id("chkTipoPeritacionList_1")).click();
    driver.findElement(By.id("chkTipoPeritacionList_2")).click();
    driver.findElement(By.id("chkTipoPeritacionList_3")).click();
    driver.findElement(By.cssSelector("#chkTipoPeritacionList td:nth-child(5)")).click();
    driver.findElement(By.id("chkTipoPeritacionList_4")).click();
    driver.findElement(By.id("chkTipoPeritacionList_5")).click();
    driver.findElement(By.id("chkTipoPeritacionList_6")).click();
    driver.findElement(By.id("chkTipoPeritacionList_7")).click();
    driver.findElement(By.id("chkTipoPeritacionList_8")).click();
    driver.findElement(By.id("btnBuscar")).click();

    driver.findElement(By.id("btnExportarExcelBusqueda")).click();
  }
        */

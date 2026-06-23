package com.statisticsCesvi;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Management {

    private static final String CHK_ASEGURADO    = "chkAseguradoList_0";
    private static final String CHK_TERCERO      = "chkAseguradoList_1";
    private static final String CHK_NORMAL       = "chkResultadoPeritacionList_0";
    private static final String CHK_PPT          = "chkResultadoPeritacionList_1";
    private static final String CHK_PTE          = "chkResultadoPeritacionList_2";
    private static final String AMPLIACION_VALUE = "1";
    private static final String SIN_AMPLIACION_VALUE = "0";

    public void managementDownload(WebDriver driver, String startDate, String endDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loadPersonal(driver, wait);
        loadGroup(driver, wait);
        loadProvincia(driver, wait);
        loadCity(driver);
        loadStartDate(driver, wait, startDate);
        loadEndDate(driver, wait, endDate);
        clickCheckbox(driver, wait, CHK_ASEGURADO);
        clickCheckbox(driver, wait, CHK_TERCERO);
        clickCheckbox(driver, wait, CHK_NORMAL);
        clickCheckbox(driver, wait, CHK_PPT);
        clickCheckbox(driver, wait, CHK_PTE);
        loadAmpliacion(driver, wait, AMPLIACION_VALUE);
        //loadAmpliacion(driver, wait, SIN_AMPLIACION_VALUE);
    }

    private void scrollTo(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", element);
    }

    private void loadPersonal(WebDriver driver, WebDriverWait wait) {
        WebElement peritosElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("lstPeritos"))
        );
        scrollTo(driver, peritosElement);
        Select perito = new Select(peritosElement);
        perito.selectByVisibleText("ARISPE EMANUEL");
    }

    private void loadGroup(WebDriver driver, WebDriverWait wait) {
        WebElement grupoElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("lstGrupoPeritos"))
        );
        scrollTo(driver, grupoElement);
        Select grupos = new Select(grupoElement);
        grupos.selectByVisibleText("Zona10-Chiappanni");
    }

    private void loadProvincia(WebDriver driver, WebDriverWait wait) {
        WebElement provinciaElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("ddlProvincia"))
        );
        scrollTo(driver, provinciaElement);
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
        scrollTo(driver, localidadElement);
        Select localidad = new Select(localidadElement);
        localidad.selectByVisibleText("Tandil");
    }

    private void loadStartDate(WebDriver driver, WebDriverWait wait, String startDate) {
        WebElement dateElement = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("MainContent_txtFechaInformeDesde"))
        );
        scrollTo(driver, dateElement);
        dateElement.clear();
        dateElement.sendKeys(startDate);
    }

    private void loadEndDate(WebDriver driver, WebDriverWait wait, String endDate) {
        WebElement dateElement = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("MainContent_txtFechaInformeHasta"))
        );
        scrollTo(driver, dateElement);
        dateElement.clear();
        dateElement.sendKeys(endDate);
    }

    private void clickCheckbox(WebDriver driver, WebDriverWait wait, String id) {
        WebElement checkbox = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id(id))
        );
        scrollTo(driver, checkbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    // selecciona por atributo value, más robusto que buscar por texto visible
    private void loadAmpliacion(WebDriver driver, WebDriverWait wait, String value) {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("MainContent_ddlAmpliacion"))
        );
        scrollTo(driver, element);
        new Select(element).selectByValue(value);
    }

}
    /*
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

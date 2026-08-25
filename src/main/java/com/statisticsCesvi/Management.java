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

    protected static final String CHK_ASEGURADO           = "chkAseguradoList_0";
    protected static final String CHK_TERCERO             = "chkAseguradoList_1";

    // CHECK TIPO INFORME
    protected static final String CHK_NORMAL              = "chkResultadoPeritacionList_0";
    protected static final String CHK_PPT                 = "chkResultadoPeritacionList_1";
    protected static final String CHK_PTE                 = "chkResultadoPeritacionList_2";

    // CHECK TIPO VEHICULO
    protected static final String CHK_AUTO                = "chkTipoVehiculoList_0";
    protected static final String CHK_CAMION              = "chkTipoVehiculoList_1";
    protected static final String CHK_MOTO                = "chkTipoVehiculoList_2";

    // CHECK TIPO PERITACION
    protected static final String CHK_SIN_TIPO            = "chkTipoPeritacionList_10";
    protected static final String CHK_ROTURA_CRISTAL      = "chkTipoPeritacionList_0";
    protected static final String CHK_ROBO_AP             = "chkTipoPeritacionList_1";
    protected static final String CHK_ROBO_PAR            = "chkTipoPeritacionList_2";
    protected static final String CHK_ROBO_RUE            = "chkTipoPeritacionList_3";
    protected static final String CHK_INCENDIO            = "chkTipoPeritacionList_4";
    protected static final String CHK_PERIT_FOTO          = "chkTipoPeritacionList_5";
    protected static final String CHK_GRANIZO             = "chkTipoPeritacionList_6";
    protected static final String CHK_INUNDACION          = "chkTipoPeritacionList_7";
    protected static final String CHK_ORDEN_RAPI          = "chkTipoPeritacionList_8";
    protected static final String CHK_PERIT_REMOTA        = "chkTipoPeritacionList_9";

    // SELECT AMPLIACION
    protected static final String DDL_AMPLIACION          = "MainContent_ddlAmpliacion";
    protected static final String AMPLIACION_VALUE        = "1";
    protected static final String SIN_AMPLIACION_VALUE    = "0";

    // SELECT CLEAS
    protected static final String DDL_CLEAS               = "MainContent_ddlPeritacionCleas";
    protected static final String CLEAS_VALUE             = "1";
    protected static final String SIN_CLEAS_VALUE         = "0";

    // DATE INPUT IDs
    protected static final String INPUT_FECHA_DESDE       = "MainContent_txtFechaInformeDesde";
    protected static final String INPUT_FECHA_HASTA       = "MainContent_txtFechaInformeHasta";

    // BUTTON IDs
    protected static final String BTN_BUSCAR              = "btnBuscar";
    protected static final String BTN_DESCARGAR           = "btnExportarExcelBusqueda";

    // TABLE / LOADER IDs
    protected static final String TABLE_RESULTADOS        = "gvBusquedaPeritacion";
    private   static final String DIV_LOADER              = "divLoader";

    // SELECT value attributes
    protected static final String PERITO_NOMBRE           = "ARISPE EMANUEL";
    protected static final String GRUPO_VALUE             = "3666";
    protected static final String PROVINCIA_VALUE         = "2";
    protected static final String CIUDAD_VALUE            = "0";

    // SELECT IDs
    protected static final String DDL_PERITOS             = "lstPeritos";
    protected static final String DDL_GRUPOS              = "lstGrupoPeritos";
    protected static final String DDL_PROVINCIA           = "ddlProvincia";
    protected static final String DDL_LOCALIDAD           = "ddlLocalidad";

    protected void resetToSearch(WebDriverWait wait) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='BusquedaPeritacion.aspx']"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(DDL_PERITOS)));
    }

    protected void generalConfig(WebDriver driver, WebDriverWait wait, String startDate, String endDate) {
        selectByText(driver, wait, DDL_PERITOS,    PERITO_NOMBRE);
        selectByValue(driver, wait, DDL_GRUPOS,    GRUPO_VALUE);
        selectByValue(driver, wait, DDL_PROVINCIA, PROVINCIA_VALUE);
        loadCity(driver);
        fillDateInput(driver, wait, INPUT_FECHA_DESDE, startDate);
        fillDateInput(driver, wait, INPUT_FECHA_HASTA, endDate);
    }

    protected void selectByText(WebDriver driver, WebDriverWait wait, String id, String text) {
        waitForLoader(wait);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        scrollTo(driver, element);
        new Select(element).selectByVisibleText(text);
    }

    protected void selectByValue(WebDriver driver, WebDriverWait wait, String id, String value) {
        waitForLoader(wait);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        scrollTo(driver, element);
        new Select(element).selectByValue(value);
    }

    protected void fillDateInput(WebDriver driver, WebDriverWait wait, String id, String value) {
        waitForLoader(wait);
        WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        scrollTo(driver, dateElement);
        dateElement.clear();
        dateElement.sendKeys(value);
    }

    protected void clickCheckBox(WebDriver driver, WebDriverWait wait, String id) {
        waitForLoader(wait);
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        scrollTo(driver, checkbox);
        checkbox.click();
    }

    protected void clickButton(WebDriver driver, WebDriverWait wait, String id) {
        waitForLoader(wait);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        scrollTo(driver, button);
        button.click();
    }

    protected void loadCity(WebDriver driver) {
        WebDriverWait waitLocalidad = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitLocalidad.until(driver1 -> {
            Select localidadSelect = new Select(driver1.findElement(By.id(DDL_LOCALIDAD)));
            return localidadSelect.getOptions().stream()
                    .anyMatch(option -> option.getAttribute("value").equals(CIUDAD_VALUE));
        });
        WebElement localidadElement = waitLocalidad.until(
                ExpectedConditions.presenceOfElementLocated(By.id(DDL_LOCALIDAD)));
        scrollTo(driver, localidadElement);
        new Select(localidadElement).selectByValue(CIUDAD_VALUE);
    }

    private void waitForLoader(WebDriverWait wait) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(DIV_LOADER)));
    }

    private void scrollTo(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", element);
    }
}

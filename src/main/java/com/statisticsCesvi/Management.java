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

    private static final String CHK_ASEGURADO           = "chkAseguradoList_0";
    private static final String CHK_TERCERO             = "chkAseguradoList_1";

    // CHECK TIPO INFORME
    private static final String CHK_NORMAL              = "chkResultadoPeritacionList_0";
    private static final String CHK_PPT                 = "chkResultadoPeritacionList_1";
    private static final String CHK_PTE                 = "chkResultadoPeritacionList_2";

    // CHECK TIPO VEHICULO
    private static final String CHK_AUTO                = "chkTipoVehiculoList_0";
    private static final String CHK_CAMION              = "chkTipoVehiculoList_1";
    private static final String CHK_MOTO                = "chkTipoVehiculoList_2";

    // CHECK TIPO PERITACION
    private static final String CHK_SIN_TIPO                = "chkTipoPeritacionList_10";
    private static final String CHK_ROTURA_CRISTAL          = "chkTipoPeritacionList_0";
    private static final String CHK_ROBO_AP                 = "chkTipoPeritacionList_1"; // Roba aparecido
    private static final String CHK_ROBO_PAR                = "chkTipoPeritacionList_2"; // Robo parcial
    private static final String CHK_ROBO_RUE                = "chkTipoPeritacionList_3"; // Robo Rueda
    private static final String CHK_INCENDIO                = "chkTipoPeritacionList_4";
    private static final String CHK_PERIT_FOTO              = "chkTipoPeritacionList_5";
    private static final String CHK_GRANIZO                 = "chkTipoPeritacionList_6";
    private static final String CHK_INUNDACION              = "chkTipoPeritacionList_7";
    private static final String CHK_ORDEN_RAPI              = "chkTipoPeritacionList_8";
    private static final String CHK_PERIT_REMOTA            = "chkTipoPeritacionList_9";

    // SELECT AMPLIACION IDs y values
    private static final String DDL_AMPLIACION          = "MainContent_ddlAmpliacion";
    private static final String AMPLIACION_VALUE        = "1";
    private static final String SIN_AMPLIACION_VALUE    = "0";

    // SELECT CLEAS IDs y values
    private static final String DDL_CLEAS          = "MainContent_ddlPeritacionCleas";
    private static final String CLEAS_VALUE        = "1";
    private static final String SIN_CLEAS_VALUE    = "0";

    // DATE INPUT IDs
    private static final String INPUT_FECHA_DESDE       = "MainContent_txtFechaInformeDesde";
    private static final String INPUT_FECHA_HASTA       = "MainContent_txtFechaInformeHasta";

    // BUTTON IDs
    private static final String BTN_BUSCAR              = "btnBuscar";
    private static final String BTN_DESCARGAR           = "btnExportarExcelBusqueda";

    // TABLE IDs
    private static final String TABLE_RESULTADOS        = "gvBusquedaPeritacion";

    // SELECT value attributes
    private static final String PERITO_NOMBRE           = "ARISPE EMANUEL";
    private static final String GRUPO_VALUE             = "3666"; // Zona10-Chiappanni
    private static final String PROVINCIA_VALUE         = "2"; // Buenos Aires
    private static final String CIUDAD_VALUE            = "0"; // [Todas]

    // SELECT IDs
    private static final String DDL_PERITOS             = "lstPeritos";
    private static final String DDL_GRUPOS              = "lstGrupoPeritos";
    private static final String DDL_PROVINCIA           = "ddlProvincia";
    private static final String DDL_LOCALIDAD           = "ddlLocalidad";

    public void managementDownload(WebDriver driver, String startDate, String endDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        selectByText(driver, wait, DDL_PERITOS,   PERITO_NOMBRE);
        selectByValue(driver, wait, DDL_GRUPOS,   GRUPO_VALUE);
        selectByValue(driver, wait, DDL_PROVINCIA, PROVINCIA_VALUE);
        loadCity(driver);
        fillDateInput(driver, wait, INPUT_FECHA_DESDE, startDate);
        fillDateInput(driver, wait, INPUT_FECHA_HASTA, endDate);
        clickCheckBox(driver, wait, CHK_TERCERO);

        selectByValue(driver, wait, DDL_AMPLIACION, SIN_AMPLIACION_VALUE);
        //selectByValue(driver, wait, DDL_AMPLIACION, SIN_AMPLIACION_VALUE);
        clickButton(driver, wait, BTN_BUSCAR);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(TABLE_RESULTADOS))); // para esperar a que cargue la table y poder descargarla
        clickButton(driver, wait, BTN_DESCARGAR);
    }

    private void scrollTo(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", element);
    }

    private void selectByText(WebDriver driver, WebDriverWait wait, String id, String text) {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id(id))
        );
        scrollTo(driver, element);
        new Select(element).selectByVisibleText(text);
    }

    private void loadCity(WebDriver driver) {
        WebDriverWait waitLocalidad = new WebDriverWait(driver, Duration.ofSeconds(10));

        waitLocalidad.until(driver1 -> {
            Select localidadSelect = new Select(
                    driver1.findElement(By.id(DDL_LOCALIDAD))
            );

            return localidadSelect.getOptions()
                    .stream()
                    .anyMatch(option ->
                            option.getAttribute("value").equals(CIUDAD_VALUE));
        });

        WebElement localidadElement = waitLocalidad.until(
                ExpectedConditions.presenceOfElementLocated(By.id(DDL_LOCALIDAD))
        );
        scrollTo(driver, localidadElement);
        new Select(localidadElement).selectByValue(CIUDAD_VALUE);
    }

    private void fillDateInput(WebDriver driver, WebDriverWait wait, String id, String value) {
        WebElement dateElement = wait.until(
                ExpectedConditions.elementToBeClickable(By.id(id))
        );
        scrollTo(driver, dateElement);
        dateElement.clear();
        dateElement.sendKeys(value);
    }

    private void clickCheckBox(WebDriver driver, WebDriverWait wait, String id) {
        WebElement checkbox = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id(id))
        );
        scrollTo(driver, checkbox);
            checkbox.click();
    }

    private void clickButton(WebDriver driver, WebDriverWait wait, String id) {
        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(By.id(id))
        );
        scrollTo(driver, button);
        button.click();
    }

    // selecciona por atributo value, más robusto que buscar por texto visible
    private void selectByValue(WebDriver driver, WebDriverWait wait, String id, String value) {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id(id))
        );
        scrollTo(driver, element);
        new Select(element).selectByValue(value);
    }

}


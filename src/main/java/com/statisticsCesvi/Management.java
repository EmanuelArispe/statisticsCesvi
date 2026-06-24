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

    // DATE INPUT IDs
    private static final String INPUT_FECHA_DESDE       = "MainContent_txtFechaInformeDesde";
    private static final String INPUT_FECHA_HASTA       = "MainContent_txtFechaInformeHasta";

    public void managementDownload(WebDriver driver, String startDate, String endDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loadPersonal(driver, wait);
        loadGroup(driver, wait);
        loadProvincia(driver, wait);
        loadCity(driver);
        fillDateInput(driver, wait, INPUT_FECHA_DESDE, startDate);
        fillDateInput(driver, wait, INPUT_FECHA_HASTA, endDate);
        clickCheckBox(driver, wait, CHK_ASEGURADO);

        selectByValue(driver, wait, DDL_AMPLIACION, AMPLIACION_VALUE);
        //selectByValue(driver, wait, DDL_AMPLIACION, SIN_AMPLIACION_VALUE);
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
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
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

package com.statisticsCesvi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReportDownloader extends Management {

    public void downloadMotoDTAsegMaterial(WebDriver driver, String startDate, String endDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        resetToSearch(wait);
        generalConfig(driver, wait, startDate, endDate);
        filterMotoDTAsegMaterial(driver, wait);
    }

    // Motos, Aseguradas, Daños Materiales y DT
    private void filterMotoDTAsegMaterial(WebDriver driver, WebDriverWait wait) {
        clickCheckBox(driver, wait, CHK_TERCERO);
        clickCheckBox(driver, wait, CHK_PPT);
        clickCheckBox(driver, wait, CHK_NORMAL);
        clickCheckBox(driver, wait, CHK_PTE);
        selectByValue(driver, wait, DDL_AMPLIACION, SIN_AMPLIACION_VALUE);
        clickButton(driver, wait, CHK_AUTO);
        clickButton(driver, wait, CHK_CAMION);
        clickCheckBox(driver, wait, CHK_ROTURA_CRISTAL);
        clickCheckBox(driver, wait, CHK_ROBO_AP);
        clickCheckBox(driver, wait, CHK_ROBO_PAR);
        clickCheckBox(driver, wait, CHK_ROBO_RUE);
        clickCheckBox(driver, wait, CHK_INCENDIO);
        clickCheckBox(driver, wait, CHK_PERIT_FOTO);
        clickCheckBox(driver, wait, CHK_GRANIZO);
        clickCheckBox(driver, wait, CHK_INUNDACION);
        clickCheckBox(driver, wait, CHK_ORDEN_RAPI);
        clickCheckBox(driver, wait, CHK_PERIT_REMOTA);
        clickButton(driver, wait, BTN_BUSCAR);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(TABLE_RESULTADOS)));
        clickButton(driver, wait, BTN_DESCARGAR);
    }
}

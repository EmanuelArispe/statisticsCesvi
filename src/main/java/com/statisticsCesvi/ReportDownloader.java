package com.statisticsCesvi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReportDownloader extends Management {

    private static final String[] VEHICULOS = { CHK_AUTO, CHK_CAMION, CHK_MOTO };

    private static final String[] PERITACIONES = {
            CHK_SIN_TIPO, CHK_ROTURA_CRISTAL, CHK_ROBO_AP, CHK_ROBO_PAR, CHK_ROBO_RUE,
            CHK_INCENDIO, CHK_PERIT_FOTO, CHK_GRANIZO, CHK_INUNDACION, CHK_ORDEN_RAPI, CHK_PERIT_REMOTA
    };

    private void download(WebDriver driver, String startDate, String endDate, String aseguradoExcluido,
                           boolean dt, String vehiculo, String peritacion, String cleasValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        resetToSearch(wait);
        generalConfig(driver, wait, startDate, endDate);
        applyFilters(driver, wait, aseguradoExcluido, dt, vehiculo, peritacion, cleasValue);
    }

    private void applyFilters(WebDriver driver, WebDriverWait wait, String aseguradoExcluido, boolean dt,
                               String vehiculo, String peritacion, String cleasValue) {
        clickCheckBox(driver, wait, aseguradoExcluido);
        if (dt) {
            clickCheckBox(driver, wait, CHK_PPT);
            clickCheckBox(driver, wait, CHK_NORMAL);
            clickCheckBox(driver, wait, CHK_PTE);
        }
        selectByValue(driver, wait, DDL_AMPLIACION, SIN_AMPLIACION_VALUE);
        for (String v : VEHICULOS) {
            if (!v.equals(vehiculo)) {
                clickButton(driver, wait, v);
            }
        }
        for (String p : PERITACIONES) {
            if (!p.equals(peritacion)) {
                clickCheckBox(driver, wait, p);
            }
        }
        if (cleasValue != null) {
            selectByValue(driver, wait, DDL_CLEAS, cleasValue);
        }
        clickButton(driver, wait, BTN_BUSCAR);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(TABLE_RESULTADOS)));
        clickButton(driver, wait, BTN_DESCARGAR);
    }

    // Motos, Aseguradas, Daños Materiales y DT
    public void downloadMotoDTAsegMaterial(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, true, CHK_MOTO, CHK_SIN_TIPO, null);
    }

    // Motos, Aseguradas, DT y Robo Aparecido
    public void downloadMotoDTRoboAparecido(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, true, CHK_MOTO, CHK_ROBO_AP, null);
    }

    // Motos, DT, Aseguradas, Incendio
    public void downloadMotoDTIncendio(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, true, CHK_MOTO, CHK_INCENDIO, null);
    }

    // Motos, DT, Aseguradas, Granizo
    public void downloadMotoDTGranizo(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, true, CHK_MOTO, CHK_GRANIZO, null);
    }

    // Motos, DT, Aseguradas, Inundacion
    public void downloadMotoDTInundacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, true, CHK_MOTO, CHK_INUNDACION, null);
    }

    // Motos, Terceros, Sin Tipo Asignado
    public void downloadMotoTerceros(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_ASEGURADO, false, CHK_MOTO, CHK_SIN_TIPO, null);
    }

    // Motos, Terceros, Sin Tipo Asignado, DT (Posible Perdida Total y Perdida Total Economica)
    public void downloadMotoTercerosDT(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_ASEGURADO, true, CHK_MOTO, CHK_SIN_TIPO, null);
    }

    // Autos, Aseguradas, Granizo
    public void downloadAutoAsegGranizo(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_GRANIZO, SIN_CLEAS_VALUE);
    }

    // Autos, Aseguradas, Incendio Parcial
    public void downloadAutoAsegIncendioParcial(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_INCENDIO, SIN_CLEAS_VALUE);
    }

    // Autos, Aseguradas, Incendio, DT (Posible Perdida Total y Perdida Total Economica)
    public void downloadAutoAsegIncendioDT(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, true, CHK_AUTO, CHK_INCENDIO, SIN_CLEAS_VALUE);
    }

    // Autos, Aseguradas, Inundacion
    public void downloadAutoAsegInundacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_INUNDACION, SIN_CLEAS_VALUE);
    }

    // Autos, Aseguradas, Inundacion, DT (Posible Perdida Total y Perdida Total Economica)
    public void downloadAutoAsegInundacionDT(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, true, CHK_AUTO, CHK_INUNDACION, SIN_CLEAS_VALUE);
    }

    // Autos, Aseguradas, Robo Parcial
    public void downloadAutoAsegRoboParcial(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_ROBO_PAR, SIN_CLEAS_VALUE);
    }

    // Autos, Aseguradas, Robo Aparecido Parcial
    public void downloadAutoAsegRoboAparecidoParcial(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_ROBO_AP, SIN_CLEAS_VALUE);
    }

    // Autos, Aseguradas, Robo Aparecido, DT (Posible Perdida Total y Perdida Total Economica)
    public void downloadAutoAsegRoboAparecidoTotal(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, true, CHK_AUTO, CHK_ROBO_AP, SIN_CLEAS_VALUE);
    }

    // Autos, Aseguradas, Rotura de Cristales
    public void downloadAutoAsegCristales(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_ROTURA_CRISTAL, SIN_CLEAS_VALUE);
    }

    // Autos, Aseguradas, Robo de Rueda
    public void downloadAutoAsegRoboRueda(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_ROBO_RUE, SIN_CLEAS_VALUE);
    }

    // Autos, Aseguradas, Sin Tipo Asignado (Danio Parcial)
    public void downloadAutoAsegDanioParcial(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_SIN_TIPO, SIN_CLEAS_VALUE);
    }

    // Autos, Aseguradas, Sin Tipo Asignado (Danio Parcial), Solo Cleas
    public void downloadAutoAsegDanioParcialCleas(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_SIN_TIPO, CLEAS_VALUE);
    }

    // Autos, Terceros, Sin Tipo Asignado
    public void downloadAutoTerceros(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_ASEGURADO, false, CHK_AUTO, CHK_SIN_TIPO, SIN_CLEAS_VALUE);
    }

    // Camiones, Aseguradas, Granizo
    public void downloadCamionAsegGranizo(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_CAMION, CHK_GRANIZO, SIN_CLEAS_VALUE);
    }

    // Camiones, Aseguradas, Incendio Parcial
    public void downloadCamionAsegIncendioParcial(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_CAMION, CHK_INCENDIO, SIN_CLEAS_VALUE);
    }

    // Camiones, Aseguradas, Incendio, DT (Posible Perdida Total y Perdida Total Economica)
    public void downloadCamionAsegIncendioDT(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, true, CHK_CAMION, CHK_INCENDIO, SIN_CLEAS_VALUE);
    }

    // Camiones, Aseguradas, Inundacion
    public void downloadCamionAsegInundacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_CAMION, CHK_INUNDACION, SIN_CLEAS_VALUE);
    }

    // Camiones, Aseguradas, Inundacion, DT (Posible Perdida Total y Perdida Total Economica)
    public void downloadCamionAsegInundacionDT(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, true, CHK_CAMION, CHK_INUNDACION, SIN_CLEAS_VALUE);
    }

    // Camiones, Aseguradas, Robo Parcial
    public void downloadCamionAsegRoboParcial(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_CAMION, CHK_ROBO_PAR, SIN_CLEAS_VALUE);
    }

    // Camiones, Aseguradas, Robo Aparecido Parcial
    public void downloadCamionAsegRoboAparecidoParcial(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_CAMION, CHK_ROBO_AP, SIN_CLEAS_VALUE);
    }

    // Camiones, Aseguradas, Robo Aparecido, DT (Posible Perdida Total y Perdida Total Economica)
    public void downloadCamionAsegRoboAparecidoTotal(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, true, CHK_CAMION, CHK_ROBO_AP, SIN_CLEAS_VALUE);
    }

    // Camiones, Aseguradas, Rotura de Cristales
    public void downloadCamionAsegCristales(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_CAMION, CHK_ROTURA_CRISTAL, SIN_CLEAS_VALUE);
    }

    // Camiones, Aseguradas, Robo de Rueda
    public void downloadCamionAsegRoboRueda(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_CAMION, CHK_ROBO_RUE, SIN_CLEAS_VALUE);
    }

    // Camiones, Aseguradas, Sin Tipo Asignado (Danio Parcial)
    public void downloadCamionAsegDanioParcial(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_CAMION, CHK_SIN_TIPO, SIN_CLEAS_VALUE);
    }

    // Camiones, Aseguradas, Sin Tipo Asignado (Danio Parcial), Solo Cleas
    public void downloadCamionAsegDanioParcialCleas(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_CAMION, CHK_SIN_TIPO, CLEAS_VALUE);
    }

    // Camiones, Terceros, Sin Tipo Asignado
    public void downloadCamionTerceros(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_ASEGURADO, false, CHK_CAMION, CHK_SIN_TIPO, SIN_CLEAS_VALUE);
    }
}

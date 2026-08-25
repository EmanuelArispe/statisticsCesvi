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
        download(driver, startDate, endDate, aseguradoExcluido, dt, vehiculo, peritacion, cleasValue,
                SIN_AMPLIACION_VALUE);
    }

    private void download(WebDriver driver, String startDate, String endDate, String aseguradoExcluido,
                           boolean dt, String vehiculo, String peritacion, String cleasValue,
                           String ampliacionValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        resetToSearch(wait);
        generalConfig(driver, wait, startDate, endDate);
        applyFilters(driver, wait, aseguradoExcluido, dt, vehiculo, peritacion, cleasValue, ampliacionValue);
    }

    private void applyFilters(WebDriver driver, WebDriverWait wait, String aseguradoExcluido, boolean dt,
                               String vehiculo, String peritacion, String cleasValue, String ampliacionValue) {
        clickCheckBox(driver, wait, aseguradoExcluido);
        if (dt) {
            clickCheckBox(driver, wait, CHK_PPT);
            clickCheckBox(driver, wait, CHK_NORMAL);
            clickCheckBox(driver, wait, CHK_PTE);
        }
        selectByValue(driver, wait, DDL_AMPLIACION, ampliacionValue);
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

    // Autos, Aseguradas, Sin Tipo Asignado (Danio Parcial), Solo Ampliacion
    public void downloadAutoAsegDanioParcialAmpliacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_SIN_TIPO, SIN_CLEAS_VALUE,
                AMPLIACION_VALUE);
    }

    // Autos, Aseguradas, Sin Tipo Asignado (Danio Parcial), Solo Cleas, Solo Ampliacion
    public void downloadAutoAsegDanioParcialCleasAmpliacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_SIN_TIPO, CLEAS_VALUE,
                AMPLIACION_VALUE);
    }

    // Autos, Aseguradas, Incendio Parcial, Solo Ampliacion
    public void downloadAutoAsegIncendioParcialAmpliacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_INCENDIO, SIN_CLEAS_VALUE,
                AMPLIACION_VALUE);
    }

    // Autos, Aseguradas, Granizo, Solo Ampliacion
    public void downloadAutoAsegGranizoAmpliacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_GRANIZO, SIN_CLEAS_VALUE,
                AMPLIACION_VALUE);
    }

    // Autos, Aseguradas, Robo Parcial, Solo Ampliacion
    public void downloadAutoAsegRoboParcialAmpliacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_AUTO, CHK_ROBO_PAR, SIN_CLEAS_VALUE,
                AMPLIACION_VALUE);
    }

    // Camiones, Aseguradas, Sin Tipo Asignado (Danio Parcial), Solo Ampliacion
    public void downloadCamionAsegDanioParcialAmpliacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_CAMION, CHK_SIN_TIPO, SIN_CLEAS_VALUE,
                AMPLIACION_VALUE);
    }

    // Camiones, Aseguradas, Incendio Parcial, Solo Ampliacion
    public void downloadCamionAsegIncendioParcialAmpliacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_CAMION, CHK_INCENDIO, SIN_CLEAS_VALUE,
                AMPLIACION_VALUE);
    }

    // Camiones, Aseguradas, Granizo, Solo Ampliacion
    public void downloadCamionAsegGranizoAmpliacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_CAMION, CHK_GRANIZO, SIN_CLEAS_VALUE,
                AMPLIACION_VALUE);
    }

    // Camiones, Aseguradas, Robo Parcial, Solo Ampliacion
    public void downloadCamionAsegRoboParcialAmpliacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_CAMION, CHK_ROBO_PAR, SIN_CLEAS_VALUE,
                AMPLIACION_VALUE);
    }

    // Motos, Aseguradas, Sin Tipo Asignado, Solo Ampliacion
    public void downloadMotoAsegSinTipoAmpliacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_MOTO, CHK_SIN_TIPO, null, AMPLIACION_VALUE);
    }

    // Motos, Aseguradas, Incendio Parcial, Solo Ampliacion
    public void downloadMotoAsegIncendioParcialAmpliacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_MOTO, CHK_INCENDIO, null, AMPLIACION_VALUE);
    }

    // Motos, Aseguradas, Granizo, Solo Ampliacion
    public void downloadMotoAsegGranizoAmpliacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_MOTO, CHK_GRANIZO, null, AMPLIACION_VALUE);
    }

    // Motos, Aseguradas, Robo Parcial, Solo Ampliacion
    public void downloadMotoAsegRoboParcialAmpliacion(WebDriver driver, String startDate, String endDate) {
        download(driver, startDate, endDate, CHK_TERCERO, false, CHK_MOTO, CHK_ROBO_PAR, null, AMPLIACION_VALUE);
    }
}

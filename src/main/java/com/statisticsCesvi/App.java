package com.statisticsCesvi;

import java.util.List;

public class App
{
    private static final String URL = "https://www.sistema-orion.com/Portal/Views/Login.aspx";
    private static final String USERNAME = "35418187";
    private static final String PASSWORD = "Em@.!35418187";
    private static final String STARTDATE = "01/06/2026";
    private static final String ENDDATE = "30/06/2026";

    public static void main( String[] args ) throws Exception
    {
        Driver dr = new Driver();
        Login login = new Login();
        Management management = new Management();
        ExcelReader reader = new ExcelReader();

        login.loginUser(dr.getDriver(), URL, USERNAME, PASSWORD);
        login.getManagement(dr.getDriver());

        long downloadStarted = System.currentTimeMillis();
        management.managementDownload(dr.getDriver(), STARTDATE, ENDDATE);

        List<ReportRowDto> rows = reader.read(Driver.DOWNLOAD_DIR, downloadStarted);
        System.out.println("Filas leídas: " + rows.size());

        //dr.tearDown();
    }
}

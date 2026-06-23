package com.statisticsCesvi;
public class App 
{
    private static final String URL = "https://www.sistema-orion.com/Portal/Views/Login.aspx";
    private static final String USERNAME = "35418187";
    private static final String PASSWORD = "Em@.!35418187";
    private static final String STARTDATE = "01/06/2026";
    private static final String ENDDATE = "30/06/2026";

    public static void main( String[] args )
    {
        Driver dr = new Driver();
        Login login = new Login();
        Management management = new Management();

        login.loginUser(dr.getDriver(), URL,USERNAME,PASSWORD);
        login.getManagement(dr.getDriver());
        management.managementDownload(dr.getDriver(),STARTDATE,ENDDATE);
        //dr.tearDown();
    }
}

package utilities;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constant {

	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	static LocalDateTime now = LocalDateTime.now();
	
	public static final String today = dtf.format(now).toString();

	// Constant values used in the test automation are stored in this class

	public static final String baseUrl = "http://demo.guru99.com/test/newtours/";

	public static final String URL1 = "http://newtours.demoaut.com/";

	public static final String UsernameCol = "UserName";

	public static final String PasswordCol = "Password";

	public static final String FirstNameCol = "FirstName";

	public static final String LastNameCol = "LastName";

	public static final String PhoneNumberCol = "Phone";

	public static final String EmailCol = "Email";

	public static final String Address1Col = "Address1";

	public static final String Address2Col = "Address2";

	public static final String CityCol = "City";

	public static final String StateCol = "State";

	public static final String PostalCodeCol = "PostalCode";

	public static final String CountryCol = "Country";

	public static final String TitleCol = "Title";

	public static final String ValidationElementCol = "ValidationElement";
	
	public static final String ValidationElementNewCol = "ValidationElementNew";

	public static final String TypeCol = "Type";

	public static final String PassengersCol = "Passengers";

	public static final String DepartingFromCol = "DepartingFrom";

	public static final String DepartingMonthCol = "DepartingMonth";

	public static final String DepartingDateCol = "DepartingDate";

	public static final String ArrivingInCol = "ArrivingIn";

	public static final String ReturningMonthCol = "ReturningMonth";

	public static final String ReturningDateCol = "ReturningDate";

	public static final String ServiceClassCol = "ServiceClass";

	public static final String AirlineCol = "Airline";

	public static final String Sheet = "TestData_UAT";

	private static final String root = new File("").getAbsolutePath();

	public static final String firefoxPath = root
			+ "\\src\\main\\resources\\geckodriver.exe".replace("\\", File.separator);;

	public static final String chromePath = root
			+ "\\src\\main\\resources\\chromedriver.exe".replace("\\", File.separator);;

	public static final String Path_TestData =
			// new File("").getAbsolutePath() + File.separator + "src" + File.separator +
			// "main" + File.separator +"java" + File.separator + "testdata" +
			// File.separator + "testData.xls" ;
			root + "\\src\\main\\java\\testdata\\TestData.xls".replace("\\", File.separator);
	public static final String File_TestData = "TestData.xls";

	public static final String extentReportsPath = root
			+ "\\Reports\\Automation_Report.html".replace("\\", File.separator);

	public static final String extentReportConfigPath = root + "extent-config.xml".replace("\\", File.separator);

}

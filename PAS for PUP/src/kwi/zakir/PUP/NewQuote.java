package kwi.zakir.PUP;

import java.io.File;

import java.util.concurrent.TimeUnit;
import kwi.zakir.Common.CommonLibrary;
import kwi.zakir.Common.PUPCommon;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import Scenarios.PaymentMethods;

public class NewQuote extends CommonLibrary {

//	public JSONParser parser = new JSONParser();
//	public String jsonFileName;
//	public JSONObject pupJSON, a;
	private WebDriver DRIVER;
	private PUPCommon cmmPUP = new PUPCommon();

	public void Non_CNI_Vehicle() {
		System.out.println("Entering Non CNI Vehicle");
		boolean obj = DRIVER.findElements(By.id("AddVehicle")).size() != 0;
		
		if(obj)
			DoClicki("ContinueLink");
	}

	public void Watercraft() {
		System.out.println("Entering Watercraft");
		boolean obj = DRIVER.findElements(By.id("AddWatercraft")).size() != 0;
		
		if(obj)
			DoClicki("ContinueLink");
	}

	public void Recreational_Vehicle() {
		System.out.println("Entering recreational Vehicle information");
		boolean obj = DRIVER.findElements(By.id("AddRecVehicle")).size() != 0;
		System.out.println("RecVeh size = " + DRIVER.findElements(By.id("AddRecVehicle")).size());
		
		if(obj)
			DoClicki("ContinueLink");
	}

	public void Coverage_Premium() {
		System.out.println("Entering Coverage Premium");
		DoClicki("rdoViewPolicDetail3");
		DoClicki("ContinueLink");
	}

	public void Name_Insured_Trustee() {
		System.out.println("Entering Name Insured & Trustee");
		DoSelecti("InsuredTypeID", cmmPUP.getJSONvalue("InsuredTypeId"));
		DoClicki("MailingAddressSameAsPrimary");

		DoClicki("ContinueLink");
	}


	
 	public void Payment_Method() {
 		System.out.println("Entering Payment Method");
//		DoSelecti("PaymentTypeId", 1);
//		DoSelecti("CardTypeId", 1);
//		DoKeyi("CardNumber", "4111111111111111");
//		DoKeyi("SecurityCode", "111");
//		DoSelecti("Month", 2);
//		DoSelecti("Year", 2);
//		DoSelecti("PaymentTypeId", cmmPUP.getJSONvalue("paymentTypeId"));
//		DoSelecti("CardTypeId", cmmPUP.getJSONvalue("cardTypeId"));
//		DoKeyi("CardNumber", cmmPUP.getJSONvalue("CardNumber"));
//		DoKeyi("SecurityCode", cmmPUP.getJSONvalue("SecurityCode"));
//		DoSelecti("Month", cmmPUP.getJSONvalue("Month"));
//		DoSelecti("Year", cmmPUP.getJSONvalue("Year"));
		
		PaymentMethods pm = new PaymentMethods();
		pm.Payment_Method(Integer.parseInt(cmmPUP.getJSONvalue("paymentTypeId").toString()));
		
 		DoClicki("ContinueLink");
 	}


	public void Properties(int dateValue) {
		System.out.println("Entering Property information");

		DoKeyi("Property_YearBuilt", cmmPUP.getJSONvalue("propYearBuilt"));
		insurance_information(dateValue);

		DoClicki("NoLink");
		WaitProperty_Click("ContinueLink");
	}

	public void insurance_information(int dateValue) {
		DoSelecti("UnderlyingInsurance_LimitTypeId",
				cmmPUP.getJSONvalue("InsLimitTypeId"));
		DoKeyi("UnderlyingInsurance_EffectiveDate",
				CustomDate(dateValue - 7, false));
		DoKeyi("UnderlyingInsurance_Limit", cmmPUP.getJSONvalue("insCLimit"));
		DoSelecti("UnderlyingInsurance_InsuranceCarrierId",
				cmmPUP.getJSONvalue("InsCarrierId"));
		// DoClicki("UnderlyingInsurance_ExpirationDate");
		DoKeyi("UnderlyingInsurance_PolicyNumber", cmmPUP.getJSONvalue("propPolicy"));

	}

	public void Household_Member() {
		System.out.println("Entering household member");

		DoKeyi("HouseholdMember_DOB", cmmPUP.getJSONvalue("hhmDOB"));
		DoClicki("HouseholdMember_FirstName");
		DoClicki("NoLink");
		DoClicki("ContinueLink");
	}

	public void CNI_Auto_Policy(int dateValue) {
		System.out.println("Entering CNI auto policy information");
		DoClicki("NumberOfVehicles");
		DoKeyi("NumberOfVehicles", cmmPUP.getJSONvalue("NoOfCNIVeh"));

		DoKeyi("Limit_octet_1", cmmPUP.getJSONvalue("insSplitLimit1"));
		DoKeyi("Limit_octet_2", cmmPUP.getJSONvalue("insSplitLimit2"));
		DoKeyi("Limit_octet_3", cmmPUP.getJSONvalue("insSplitLimit3"));
		DoKeyi("PersonalAutoPolicyNumber", cmmPUP.getJSONvalue("PAPolicyNumber"));
		DoKeyi("EffectiveDate", CustomDate(dateValue - 7, false));

		WaitProperty_Click("ContinueLink");
	}

	public void enter_no_GAQ() {
		System.out.println("Answering NO to all questions.");
		for (int i = 0; i <= 14; i++) {
			DRIVER.findElement(
					By.xpath("//input[@id='Questions_" + i
							+ "__Answer' and @value='FALSE']")).click();
		}
		// CommonLibrary.addDelay(2000);
		DoClicki("ContinueLink");
	}

	public void Insurance_Product(int dateValue) {
		System.out.println("Selecting Product line = PUP");
		DoKeyi("EffectiveDate", CustomDate(dateValue, true) + Keys.TAB);

		if (dateValue <= 0) {
			addDelay(1000);
			DoClicki("Continue");
		} else
			DoClicki("ContinueLink");
	}

	public void Customer_Information() {
		System.out.println("Entering Customer Information");
		DoKeyi("MiddleName", cmmPUP.getJSONvalue("mName"));
		DoKeyi("FirstName", cmmPUP.getJSONvalue("fName"));
		DoKeyi("LastName", cmmPUP.getJSONvalue("lName"));

		DoSelecti("PrimaryPhone_PhoneTypeId", cmmPUP.getJSONvalue("phnType"));
		DoKeyi("EMail", cmmPUP.getJSONvalue("EMail"));

		DoKeyi("PrimaryAddress_StreetNumber", cmmPUP.getJSONvalue("StreetNumber"));
		DoSelecti("PrimaryAddress_StreetDirectionTypeId", cmmPUP.getJSONvalue("StreetDirection"));
		DoKeyi("PrimaryAddress_StreetName", cmmPUP.getJSONvalue("StreetName"));
		DoSelecti("PrimaryAddress_StreetTypeId", cmmPUP.getJSONvalue("StreetType"));
		DoKeyi("PrimaryAddress_UnitNumber", cmmPUP.getJSONvalue("UnitNumber"));
		DoKeyi("PrimaryAddress_ZipCode", cmmPUP.getJSONvalue("ZipCode"));
		DoKeyi("PrimaryPhone_PhoneNumber", cmmPUP.getJSONvalue("phnNumber"));

		DoClicki("ContinueLink");
	}

	public void GetQuote() {
		System.out.println("Starting Quote");
		DoClicki("actionNewQuote");
	}

	public void AgencySelection() {
		System.out.println("Selecting Agency");
		Long agentID = (Long) cmmPUP.getJSONvalue("AgencyBranchId");
		DoSelectn("AgencyBranch", agentID.intValue());
		DoClicki("ContinueLink");
	}

	public void devLogin(String baseUrl) {

		System.out.println("Login in to Dev site: " + baseUrl);
		DRIVER.get(baseUrl);

	}

	public void setBrowserdriver(String browserDriver) {

		switch (browserDriver) {
		case "IE": {
			File file = new File("S:/Zakir/jre7/bin/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			DRIVER = new InternetExplorerDriver();
			break;
		}
		case "FF": {
			DRIVER = new FirefoxDriver();
			break;
		}
		case "CR": {
			File file = new File("S:/Zakir/jre7/bin/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",
					file.getAbsolutePath());
			DRIVER = new ChromeDriver();
			break;
		}
		default:
			break;
		}

		CommonLibrary.init_Driver(DRIVER);
		DRIVER.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}
	
	public String getQuoteNumber() {
		
		return DRIVER.findElement(By.id("QH_QuoteNumber")).getText();
//		return "No Quote Number found";
	}

}

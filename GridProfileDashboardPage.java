package com.enphase.enlighten.encr.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.enphase.enlighten.utils.data.JsonReader;
import com.enphase.enlighten.web.BasePage;
import com.enphase.enlighten.web.WebHelperObjects;

public class GridProfileDashboardPage extends BasePage {

	private static final Logger logger = LogManager.getLogger(GridProfileDashboardPage.class);

	@FindBy(xpath = "//a[@id='admin_link']")
	private WebElement adminField;

	@FindBy(xpath = "//div[@id='header_container']//a[@id='dashboard_link']")
	private WebElement dashboardField;

	@FindBy(xpath = "//a[@id='add_portlet_button']")
	private WebElement addAWidgetField;

	@FindBy(xpath = "//div[@class='portlet_summary']/div/h2")
	private List<WebElement> dashboardWidgetList;

	@FindBy(xpath = "//input[@id='emu_serial_num']")
	private WebElement envoySerialNumberInputBox;

	@FindBy(xpath = "//input[@id='auth_num']")
	private WebElement authorizationNumberInputBox;

	@FindBy(xpath = "//input[@value='Get Token']")
	private WebElement getTokenBtn;

	@FindBy(xpath = "//p[text()=' Envoy Serial Number  ']")
	private WebElement envoySerialNumberLabel;

	@FindBy(xpath = "//span[text()='add a widget']")
	private WebElement addWidgetButton;

	@FindBy(xpath = "//div[@class = 'portlet_summary']/div//h2[text() = 'Grid Profile Change Token']/following::div/a//span")
	private WebElement addToDashboardForGridProfile;

	@FindBy(xpath = "//div[@class='column ui-sortable']//div//p[text()=' No Envoy found with serial number  ']")
	private WebElement authNumberInputValidationElmt;

	public GridProfileDashboardPage(WebHelperObjects webHelperObjects) {
		super(webHelperObjects);
		logger.info("Initializing GridProfileDashboardPage");
		PageFactory.initElements(driver, this);
	}

	/**
	 * adding widget to dashboard
	 *
	 * @param widgetName
	 */

	private void addWidget(String widgetName) {
		buttonHelper.click(addWidgetButton);
		progressHelper.waitForPageLoad();
		for (WebElement ele : dashboardWidgetList) {
			if (widgetName.equals(ele.getText())) {
				buttonHelper.click(addToDashboardForGridProfile);
				progressHelper.waitForPageLoad();
				break;
			}
		}
	}

	public void verifyInputValuesToAuthorizationNumberFieldSuccessful(String userType) {
		logger.info(
				"Verify admin is able to input values in \"Authorization Number \" field for the ‚ÄúGrid Profile Change Token‚Äù widgets on dashboard page");
		progressHelper.waitForPageLoad();

		progressHelper.waitForElementToDisplay(adminField);

		buttonHelper.click(adminField);
		progressHelper.waitForPageLoad();

		progressHelper.waitForElementToDisplay(dashboardField);

		buttonHelper.click(dashboardField);

		addWidget("Grid Profile Change Token");

		String authNumber = JsonReader.getStringTestData("data", "test_data", "authorization_number");

		progressHelper.waitForElementToDisplay(authorizationNumberInputBox);
		javascriptHelper.scrollIntoView(authorizationNumberInputBox);
		seleniumHelper.enterTextInTextField(authorizationNumberInputBox, authNumber);
		buttonHelper.click(getTokenBtn);

		String authNumberErrorMsg = JsonReader.getStringTestData("data", "test_data", "auth_num_error_msg");
		webAssertHelper.assertEquals(seleniumHelper.getText(authNumberInputValidationElmt), authNumberErrorMsg);
	}

	public void VerifyEnvoySerialNumberLabelIsPresent() {
		logger.info("Verify admin is able to view \"Envoy Serial Number\" label  in the ‚ÄúGrid Profile Change Token‚Äù widgets on dashboard pag");
		progressHelper.waitForPageLoad();

		progressHelper.waitForElementToDisplay(adminField);

		buttonHelper.click(adminField);
		progressHelper.waitForPageLoad();

		progressHelper.waitForElementToDisplay(dashboardField);

		buttonHelper.click(dashboardField);

		buttonHelper.click(dashboardField);
		progressHelper.waitForPageLoad();
		addWidget("Grid Profile Change Token");

		progressHelper.waitForElementToDisplay(envoySerialNumberLabel);
		javascriptHelper.scrollIntoView(envoySerialNumberLabel);

		webAssertHelper.assertElementExists(envoySerialNumberLabel);
	}

	public void VerifyErrorMessageforValidEnvoySNAndInvalidAuthNumber(String userType) {
		logger.info(
				" Verify appropriate error message is shown when admin click on \"Get Token\" button with Valid Envoy Serial Number and blank/Invalid Authorization  in the ‚ÄúGrid Profile Change Token‚Äù widgets on dashboard ");
		progressHelper.waitForPageLoad();

		progressHelper.waitForElementToDisplay(adminField);

		buttonHelper.click(adminField);
		progressHelper.waitForPageLoad();

		progressHelper.waitForElementToDisplay(dashboardField);

		buttonHelper.click(dashboardField);
		progressHelper.waitForPageLoad();
		addWidget("Grid Profile Change Token");

		String envoySN = JsonReader.getStringTestData("data", "test_data", "envoy_serial_number");
		progressHelper.waitForElementToDisplay(envoySerialNumberInputBox);
		javascriptHelper.scrollIntoView(envoySerialNumberInputBox);
		seleniumHelper.enterTextInTextField(envoySerialNumberInputBox, envoySN);
		progressHelper.waitForPageLoad();

		String authNumber = JsonReader.getStringTestData("data", "test_data", "authorization_number");

		seleniumHelper.enterTextInTextField(authorizationNumberInputBox, authNumber);
		progressHelper.waitForPageLoad();

		buttonHelper.click(getTokenBtn);

	}

}

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

public class DashboardPage extends BasePage {
	private static final Logger logger = LogManager.getLogger(DashboardPage.class);

	@FindBy(xpath = "//a[@id='admin_link']")
	private WebElement adminField;

	@FindBy(id = "dashboard_link")
	private WebElement dashboardLink;

	@FindBy(xpath = "//span[text()='add a widget']")
	private WebElement addWidgetButton;

	@FindBy(xpath = "//div[@class = 'portlet_summary']/div//h2[text() = 'Maintenance Alerts']/following::div/a//span")
	private WebElement addToDashoardForMaintAlert;

	@FindBy(id = "config_alert_type")
	private WebElement alertTypeField;

	@FindBy(id = "config_show_map")
	private WebElement showMapCheckbox;

	@FindBy(xpath = "//div[@class = 'portlet_summary']/div//h2[text() = 'Grid Profile Change Token']/following::div/a//span")
	private WebElement addToDashboardForGridProfile;

	@FindBy(xpath = "//div[@id='columns']/form//div[@class='column ui-sortable']//div/form//p//input[@name='emu_serial_num']")
	private WebElement envoyNumTextbox;

	@FindBy(xpath = "//div[@id='columns']/form//div[@class='column ui-sortable']//div/form//p//input[@id='auth_num']")
	private WebElement authNumTextbox;

	@FindBy(xpath = "//div[@id='columns']/form//div[@class='column ui-sortable']//div/form//p//input[@value='Get Token']")
	private WebElement getTokenButton;

	@FindBy(xpath = "//div[@id='add_portlet_dialog']//div[@class='portlet_summary']/div//h2")
	private List<WebElement> dashboardWidgetList;

	@FindBy(xpath = "//p[text()=' Envoy Serial Number  ']")
	private WebElement envoySerialNumberLabel;

	@FindBy(xpath = "//div[@class='column ui-sortable']//div//p[text()=' No Envoy found with serial number  ']")
	private WebElement authNumberInputValidationElmt;

	@FindBy(xpath = "//input[@data-disable-with='Save']")
	private WebElement saveBtn;

	public DashboardPage(WebHelperObjects webHelperObjects) {
		super(webHelperObjects);
		logger.info("Initializing DashBoardPage");
		PageFactory.initElements(driver, this);
	}

	/**
	 * Add widget to dashboard page
	 */
	private void addWidget(String widgetName, WebElement element) {
		buttonHelper.click(addWidgetButton);
		progressHelper.waitForPageLoad();
		for (WebElement ele : dashboardWidgetList) {
			if (widgetName.equals(ele.getText())) {
				buttonHelper.click(element);
				progressHelper.waitForPageLoad();
				break;
			}
		}
	}

	/**
	 * Verifies "Microinverters Not Reporting" option
	 */
	public void verifyMicroinvNotReporing() {
		logger.info(
				"Verify 'Microinverters Not Reporting' option is present under 'Alert Type' field  dropdown for the 'Maintenance Alerts' widgets on dashboard page");
		buttonHelper.clickAction(dashboardLink);
		addWidget("Maintenance Alerts", addToDashoardForMaintAlert);
		buttonHelper.click(alertTypeField);
		dropDownHelper.selectUsingVisibleValue(alertTypeField, "Microinverters Not Reporting");
		String selectedValue = dropDownHelper.getSelectedValue(alertTypeField);
		logger.info("Validating 'Microinverters Not Reporting' Field");
		webAssertHelper.assertEquals("Microinverters Not Reporting", selectedValue);
	}

	/**
	 * Verified "Any Problem' option
	 */
	public void verifyAnyProblemOptionIsPresent() {
		logger.info(
				"Verify 'Any Problem' option is present under 'Alert Type' field  dropdown for the 'Maintenance Alerts' widgets on dashboard page");
		buttonHelper.clickAction(dashboardLink);
		addWidget("Maintenance Alerts", addToDashoardForMaintAlert);
		buttonHelper.click(alertTypeField);
		dropDownHelper.selectUsingVisibleValue(alertTypeField, "Any Problem");
		String selectedValue = dropDownHelper.getSelectedValue(alertTypeField);
		logger.info("Validating 'Any Problem' Option");
		webAssertHelper.assertEquals("Any Problem", selectedValue);
	}

	/**
	 * Verifies "show map" checkbox option
	 */
	public void verifyShowMapIsPresent() {
		logger.info("Verify 'Show Map' checkbox is present in 'Maintenance Alerts' widgets on dashboard page");
		buttonHelper.click(dashboardLink);
		addWidget("Maintenance Alerts", addToDashoardForMaintAlert);
		logger.info("Validating 'Show Map' checkbox");
		webAssertHelper.assertElementExists(showMapCheckbox);
		checkboxRadioButtonHelper.selectCheckBox(showMapCheckbox);
		webAssertHelper.assertEquals(checkboxRadioButtonHelper.isIselected(showMapCheckbox), true);
	}

	/**
	 * Verifies "get token" is visible
	 */
	public void verifyGetTokenButton() {
		logger.info("Verify admin is able to view 'Get Token' button in the ‚'Grid Profile Change Token'widgets on dashboard page");
		buttonHelper.click(dashboardLink);
		addWidget("Grid Profile Change Token", addToDashboardForGridProfile);
		logger.info("Validating 'Get Token' button");
		webAssertHelper.assertElementExists(getTokenButton);
	}

	/**
	 * verifying 'Envoy Serial Number' label
	 */

	public void VerifyEnvoySerialNumberLabelIsPresent() {
		logger.info("Verify admin is able to view \"Envoy Serial Number\" label  in the 'Grid Profile Change Token' widgets on dashboard pag");
		progressHelper.waitForPageLoad();
		progressHelper.waitForElementToDisplay(adminField);
		buttonHelper.click(adminField);
		progressHelper.waitForPageLoad();
		progressHelper.waitForElementToDisplay(dashboardLink);
		buttonHelper.click(dashboardLink);
		progressHelper.waitForPageLoad();
		addWidget("Grid Profile Change Token", addToDashboardForGridProfile);
		logger.info("Validating 'Envoy Serial Number' Label");
		progressHelper.waitForElementToDisplay(envoySerialNumberLabel);
		javascriptHelper.scrollIntoView(envoySerialNumberLabel);
		webAssertHelper.assertElementExists(envoySerialNumberLabel);
	}

	/**
	 * verifying 'Authorization Number' field
	 *
	 * @param userType
	 */

	public void verifyInputValuesToAuthorizationNumberFieldSuccessful(String userType) {
		logger.info(
				"Verify admin is able to input values in \"Authorization Number \" field for the 'Grid Profile Change Token' widgets on dashboard page");
		progressHelper.waitForPageLoad();
		progressHelper.waitForElementToDisplay(adminField);
		buttonHelper.click(adminField);
		progressHelper.waitForPageLoad();
		progressHelper.waitForElementToDisplay(dashboardLink);
		buttonHelper.click(dashboardLink);
		addWidget("Grid Profile Change Token", addToDashboardForGridProfile);
		logger.info("Validating 'Authorization Number' Field");
		String authNumber = JsonReader.getStringTestData("data", "test_data", "authorization_number");
		progressHelper.waitForElementToDisplay(authNumTextbox);
		javascriptHelper.scrollIntoView(authNumTextbox);
		seleniumHelper.enterTextInTextField(authNumTextbox, authNumber);
		buttonHelper.click(getTokenButton);
		String authNumberErrorMsg = JsonReader.getStringTestData("data", "test_data", "auth_num_error_msg");
		webAssertHelper.assertEquals(seleniumHelper.getText(authNumberInputValidationElmt), authNumberErrorMsg);
	}

	/**
	 * verifying 'Envoy Not Reporting' option
	 *
	 * @param usertype
	 */
	public void VerifyEnvoyNotReportingPresentUnderAlertTypeDropdown(String usertype) {
		logger.info(
				"Verify \"Envoy Not Reporting\" option is present under \"Alert Type\" field  dropdown for the \"Maintenance Alerts\" widgets on dashboard page");
		progressHelper.waitForPageLoad();
		progressHelper.waitForElementToDisplay(adminField);
		buttonHelper.click(adminField);
		progressHelper.waitForPageLoad();
		progressHelper.waitForElementToDisplay(dashboardLink);
		buttonHelper.click(dashboardLink);
		progressHelper.waitForPageLoad();
		addWidget("Maintenance Alerts", addToDashoardForMaintAlert);
		logger.info("Validating 'Envoy Not Reporting' present under Alert Type");
		buttonHelper.click(alertTypeField);
		dropDownHelper.selectUsingVisibleValue(alertTypeField, "Envoy Not Reporting");
		String selectedValue = dropDownHelper.getSelectedValue(alertTypeField);
		logger.info("Validating 'Envoy Not Reporting' Field");
		webAssertHelper.assertEquals("Envoy Not Reporting", selectedValue);
	}

	/**
	 * verifying selected Dropdown value
	 *
	 * @param usertype
	 */
	public void VerifyDropdownValueSelectSuccessful(String usertype) {
		logger.info(
				"Verify admin is able to select dropdown option for \"Alert Type\" field for the \"Maintenance Alerts\" widgets on dashboard page");
		progressHelper.waitForPageLoad();
		progressHelper.waitForElementToDisplay(adminField);
		buttonHelper.click(adminField);
		progressHelper.waitForPageLoad();
		progressHelper.waitForElementToDisplay(dashboardLink);
		buttonHelper.click(dashboardLink);
		progressHelper.waitForPageLoad();
		addWidget("Maintenance Alerts", addToDashoardForMaintAlert);
		logger.info("Validating dropdown value selection");
		buttonHelper.click(alertTypeField);
		String alertType = JsonReader.getStringTestData("data", "test_data", "alert_type");
		dropDownHelper.selectUsingVisibleValue(alertTypeField, alertType);
		buttonHelper.click(saveBtn);
		String selectedValue = dropDownHelper.getSelectedValue(alertTypeField);
		webAssertHelper.assertEquals("Envoy Not Reporting", selectedValue);
	}

}

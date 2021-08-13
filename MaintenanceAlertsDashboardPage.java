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

public class MaintenanceAlertsDashboardPage extends BasePage {

	private static final Logger logger = LogManager.getLogger(MaintenanceAlertsDashboardPage.class);

	@FindBy(xpath = "//a[@id='admin_link']")
	private WebElement adminField;

	@FindBy(xpath = "//div[@id='header_container']//a[@id='dashboard_link']")
	private WebElement dashboardField;

	@FindBy(xpath = "//span[text()='add a widget']")
	private WebElement addWidgetButton;

	@FindBy(xpath = "//div[@class = 'portlet_summary']/div//h2[text() = 'Maintenance Alerts']/following::div/a//span")
	private WebElement addToDashoardForMaintAlert;

	@FindBy(id = "config_alert_type")
	private WebElement alertTypeField;

	@FindBy(xpath = "//div[@class='portlet_summary']/div/h2")
	private List<WebElement> dashboardWidgetList;

	@FindBy(xpath = "//input[@data-disable-with='Save']")
	private WebElement saveBtn;

	public MaintenanceAlertsDashboardPage(WebHelperObjects webHelperObjects) {
		super(webHelperObjects);
		logger.info("Initializing DashboardPage");
		PageFactory.initElements(driver, this);
	}

	/**
	 * adding widget to 'Dashboard'
	 *
	 * @param widgetName
	 */
	private void addWidget(String widgetName) {
		buttonHelper.click(addWidgetButton);
		progressHelper.waitForPageLoad();
		for (WebElement ele : dashboardWidgetList) {
			if (widgetName.equals(ele.getText())) {
				buttonHelper.click(addToDashoardForMaintAlert);
				progressHelper.waitForPageLoad();
				break;
			}
		}
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
		progressHelper.waitForElementToDisplay(dashboardField);

		buttonHelper.click(dashboardField);
		progressHelper.waitForPageLoad();
		addWidget("Maintenance Alerts");
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

		progressHelper.waitForElementToDisplay(dashboardField);

		buttonHelper.click(dashboardField);
		progressHelper.waitForPageLoad();

		addWidget("Maintenance Alerts");
		buttonHelper.click(alertTypeField);

		String alertType = JsonReader.getStringTestData("data", "test_data", "alert_type");

		dropDownHelper.selectUsingVisibleValue(alertTypeField, alertType);

		buttonHelper.click(saveBtn);

		String selectedValue = dropDownHelper.getSelectedValue(alertTypeField);

		webAssertHelper.assertEquals("Envoy Not Reporting", selectedValue);
	}

}

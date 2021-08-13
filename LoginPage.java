package com.enphase.enlighten.homeowner.pages.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.enphase.enlighten.mobile.PageObjectBase;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class LoginPage extends PageObjectBase {
	private static final Logger logger = LogManager.getLogger(LoginPage.class);

	private LoginPageElementsRepository lp;

	public LoginPage() {
		System.out.println("INSIDE CUSTUCTOR");

		lp = new LoginPageElementsRepository(driver);
		System.out.println("PAGE ELEMENTS INITALIZED IN CUSTUCTOR");
	}

	public void waitUntilLoginPageIsHidden() {
		waitForElementToBeInvisible(lp.usernameTextField);
	}

	public void waitUntillPwdFieldIsVisible() {
		waitUntilElementIsVisible(lp.passwordTextField);
	}

	public void clickNextBtn() {
		if (driver instanceof AndroidDriver) {
			waitUntilElementtobeClickable(lp.nextBtn);
		}
		lp.nextBtn.click();

	}

	public void enterEmail(String email) {
		lp.usernameTextField.click();
		lp.usernameTextField.clear();
		lp.usernameTextField.sendKeys("sthakkar@enphaseenergy.com");

	}

	public void enterPassword(String password) {
		if (driver instanceof AndroidDriver)
			lp.passwordTextField.click();
		else {
			logger.info("password by Thread " + Thread.currentThread().getId() + " - " + driver);
			driver.getPageSource();
			sleep(2000);
			lp.usernameTextField.click();
			sleep(2000);
			driver.findElement(MobileBy.AccessibilityId("Next")).click();
		}
		lp.passwordTextField.sendKeys("Chocolatecake@123*");
		hideKeyboard();

	}

	public void clickSignInButton() {
		waitUntilElementtobeClickable(lp.Loginbutton);
		lp.Loginbutton.click();

	}

	public void clickAppVersionLink() {
//		waitUntilElementtobeClickable(lp.appVersionLink);
		lp.appVersionLink.click();

	}

	public void clickSwithEnviroment() {
		waitUntilElementtobeClickable(lp.switchEnv);
		lp.switchEnv.click();

	}

	public void clickEnvQA2() {
		waitUntilElementtobeClickable(lp.envQA2);
		lp.envQA2.click();
	}

	public void clickDoneBtn() {
		waitUntilElementtobeClickable(lp.donebtn);
		lp.donebtn.click();

	}

	public void allowButton() {
		waitUntilElementtobeClickable(lp.allowbtn);
		lp.allowbtn.click();

	}

	public void autoDownloadBtn() {
		waitUntilElementIsVisible(lp.autoDownload);
		lp.autoDownload.click();

	}

	public void enableBtn() {
		waitUntilElementtobeClickable(lp.enableBtn);
		lp.enableBtn.click();

	}

	public void gotItBtn() {
		if (driver instanceof IOSDriver) {
			sleep(10000);
		} else {
			waitUntilElementIsVisible(lp.gotItBtn);
		}
		lp.gotItBtn.click();

	}

	public void login(String email, String password, String env) {
		switchToEnv(env);
		System.out.println("LOGIN METHOD ");
		waitUntillPwdFieldIsVisible();
		enterEmail(email);
		System.out.println("Enter Email Id");
		enterPassword(password);
		System.out.println("Enter Email Password");
		clickSignInButton();
		System.out.println("CLICKING ON SIGN IN BUTTON");
		waitUntilLoginPageIsHidden();
		allowButton();
		System.out.println("CLICK ON ALLOW BUTTON");
		autoDownloadBtn();
		System.out.println("CLICK ON AUTODOWNLOAD BUTTON");
		enableBtn();
		System.out.println("CLICK ON ENABLE BUTTON");
		gotItBtn();
		System.out.println("CLICK ON GOT IT BUTTON");
	}

	public void switchToEnv(String env) {
//		try {
//		clickNextBtn();
//		}catch(Exception e) {
//			System.out.println("NO");
//		}
		clickNextBtn();
		System.out.println("CLICKED NEXT BUTTON");
		sleep(10000);
		if (env.equals("qa2") || env.equals("qa4")) {
//			for(int i = 0 ; i < 7; i ++) {
//				try {
//					clickAppVersionLink();
//				}catch(Exception e) {
//					System.out.println("DEV OPTION OPENED");
//				}
//			}
//			sleep(5000);
//		} else if (env.equals("blue")) {
//			sleep(1000);

			if (driver instanceof AndroidDriver) {
				while (isElementVisible(lp.appVersionLink)) {
					try {
						System.out.println("22222222222222222222222222222");
						clickAppVersionLink();
					} catch (Exception e) {
						System.out.println("APP VERSION NOT VISIBLE NOW. MAY BE NAVIGATED");
					}
				}

//			}else {
//				for(int i = 0 ; i < 100 ; i ++) {
//					System.out.println("INSIDE FOR LOOP");
//					clickAppVersionLink();
//
//				}
//
//
//				TouchAction ta = null;
//				try {
//					ta = new TouchAction(driver);
//				}catch(Exception e) {
//					 ta = new TouchAction(driver);
//				}
//				Point po = lp.appVersionLink.getLocation();
//				for(int i = 0 ; i < 15 ; i++) {
//				ta
//				.tap(PointOption.point(po))
//				.tap(PointOption.point(po))
//				.tap(PointOption.point(po))
//				.tap(PointOption.point(po))
//				.tap(PointOption.point(po))
//				.tap(PointOption.point(po))
//				.tap(PointOption.point(po))
//				.tap(PointOption.point(po))

//				.press(PointOption.point(po)).release();
//				}
//				ta.perform();
//				.press(PointOption.point(po))
//				.press(PointOption.point(po))
//				.press(PointOption.point(po))
//				.press(PointOption.point(po))
//				.press(PointOption.point(po))
//				.press(PointOption.point(po))
//				.press(PointOption.point(po))
//				.perform();
//				MultiTouchAction mta = new MultiTouchAction(driver);
//				mta.add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta).add(ta);
//				mta.perform();

//				ta.(lp.appVersionLink).doubleClick().doubleClick().build().perform();
//			}

			}
			if (driver instanceof AndroidDriver) {
				System.out.println("ITS ITSNCE OF ANDROID DRIVER");
				clickSwithEnviroment();
			} else {
				System.out.println("IS NOT ANDROID DRIVER. ITS IOS");
			}
			System.out.println("ENV OPTION OPENED");
			clickEnvQA2();
			System.out.println("QA2 OPTION Select");

			if (driver instanceof AndroidDriver) {
				System.out.println("ITS ITSNCE OF ANDROID DRIVER");
				clickDoneBtn();
				System.out.println("Done Button Select");

			} else {
				System.out.println("IS NOT ANDROID DRIVER. ITS IOS");
			}

		}

	}
}
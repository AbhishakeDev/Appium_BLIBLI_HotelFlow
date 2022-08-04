package com.example.appium.stepDefinition;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MyStepdefs {

    AndroidDriver<MobileElement> androidDriver = null;

    void prerequisite() throws Exception {
//        InputStream inputStream = Thread.currentThread()
//                .getContextClassLoader()
//                .getResourceAsStream("capabilities.properties");
        Properties desiredCapabilitiesProp = new Properties();
        Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/src/test/java/resources/capabilities.properties")).forEach(entry -> {
            desiredCapabilitiesProp.setProperty(entry.split("=")[0], entry.split("=")[1]);
        });
        if (androidDriver == null) {
            // Override
            if (System.getProperty("device.name") != null)
                desiredCapabilitiesProp.setProperty("device.name", System.getProperty("device.name"));
            if (System.getProperty("device.udid") != null)
                desiredCapabilitiesProp.setProperty("device.udid", System.getProperty("device.udid"));
            if (System.getProperty("appium.address") != null)
                desiredCapabilitiesProp.setProperty("appium.address", System.getProperty("appium.address"));
            if (System.getProperty("appium.system.port") != null)
                desiredCapabilitiesProp.setProperty("appium.system.port", System.getProperty("appium.system.port"));
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", desiredCapabilitiesProp.getProperty("device.name"));
            capabilities.setCapability("udid", desiredCapabilitiesProp.getProperty("device.udid"));
            capabilities.setCapability("systemPort", desiredCapabilitiesProp.getProperty("appium.system.port"));
            capabilities.setCapability("deviceOrientation", "portrait");
            capabilities.setCapability("automationName", "uiautomator2");
            capabilities.setCapability("appPackage", "blibli.mobile.commerce");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("appActivity", "blibli.mobile.ng.commerce.core.init.view.SplashActivity");
            capabilities.setCapability("noReset", "true");
            androidDriver = new AndroidDriver<>(new URL(desiredCapabilitiesProp.get("appium.address").toString()), capabilities);
            androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }

    void postrequisite() {
        androidDriver = null;
    }

    @Given("open the App and tap back button")
    public void openTheAppAndTapBackButton() throws Exception {
        prerequisite();
        androidDriver.navigate().back();
//        androidDriver.navigate().back();
    }

    @When("click on categories")
    public void clickOnCategories() {
        MobileElement el = androidDriver.findElement(By.id("blibli.mobile.commerce:id/navigation_category"));
        el.click();
    }

    @And("scroll left container and click Tours and travels")
    public void scrollLeftContainerAndClickToursAndTravels() {
        MobileElement text = androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)." + "resourceId(\"blibli.mobile.commerce:id/rv_category_tabs\"))" + ".scrollIntoView(new UiSelector().textContains(\"Tour & Travel\"))");
        text.click();
    }

    @Then("scroll down right container and Click on Hotel")
    public void scrollDownRightContainerAndClickOnHotel() {
        MobileElement text = androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)." + "resourceId(\"blibli.mobile.commerce:id/rv_category_items\"))" + ".scrollIntoView(new UiSelector().textContains(\"Hotel\"))");
        text.click();
    }

    @When("search for hotel in search bar")
    public void searchForHotelInSearchBar() {
        MobileElement el = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.TextSwitcher/android.widget.TextView\n"));
        el.click();
        MobileElement element1 = androidDriver.findElement(By.id("blibli.mobile.commerce:id/et_search_box"));
        element1.sendKeys("hotel");
    }

    @Then("click on Hotel option")
    public void clickOnHotelOption() throws InterruptedException {
        MobileElement element2 = androidDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]\n"));
        element2.click();
        Thread.sleep(2000);
    }

    @Given("click on Jakarta favorite destination card")
    public void clickOnJakartaFavoriteDestinationCard() {
        androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)." + "resourceId(\"blibli.mobile.commerce:id/rv_hotel_home\"))" + ".scrollIntoView(new UiSelector().textContains(\"Layanan terbaik 24/7\"))");
        androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)." + "resourceId(\"blibli.mobile.commerce:id/recycler_view\"))" + ".scrollIntoView(new UiSelector().textContains(\"JAKARTA\"))").click();

//
    }

    @When("click on first hotel card")
    public void clickOnFirstHotelCard() {
        String lineNumber="Pullman Jakarta Indonesia";
        MobileElement textCheck=androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)."+ "resourceId(\"blibli.mobile.commerce:id/rv_hotel_list\"))"
                + ".scrollIntoView(new UiSelector().textContains(\""+lineNumber+"\"))");
        textCheck.click();
    }

    @And("click room button")
    public void clickRoomButton() {
        androidDriver.findElement(By.xpath("//*[@text='Pilih kamar']")).click();
    }

    @And("click order button")
    public void clickOrderButton() {
        androidDriver.findElement(By.xpath("//*[@text='Pesan']")).click();
    }

    @And("fill in details on order page")
    public void fillInDetailsOnOrderPage() throws InterruptedException {
        Thread.sleep(2000);
            androidDriver.findElement(By.xpath("//*[@text=\"Nama depan\"]")).sendKeys("ABC");
            androidDriver.findElement(By.xpath("//*[@text=\"Nama belakang\"]")).sendKeys("ABCD");
            androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)."+ "resourceId(\"blibli.mobile.commerce:id/nsv_checkout\"))"
                + ".scrollIntoView(new UiSelector().textContains(\""+"Nama tamu"+"\"))");

            androidDriver.findElement(By.xpath("//*[@text=\"Nomor HP\"]")).sendKeys("6393122939");
            androidDriver.findElement(By.xpath("//*[@text=\"Email\"]")).sendKeys("abc@gmail.com");

            androidDriver.findElement(By.xpath("//*[@text=\"Nama lengkap\"]")).sendKeys("ABC");

        androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)."+ "resourceId(\"blibli.mobile.commerce:id/nsv_checkout\"))"
                + ".scrollIntoView(new UiSelector().textContains(\""+"Lanjutkan"+"\"))").click();
    }

    @Then("click on proceed")
    public void clickOnProceed() {
        androidDriver.findElement(By.xpath("//*[@text=\"Yakin, lanjutkan\"]")).click();
    }
}

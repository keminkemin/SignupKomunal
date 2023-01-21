package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;

import static org.testng.Assert.*;

public class Signup {
    private WebDriver driver;
    @Given("I am on Komunal deposit web page")
    public void i_am_on_komunal_deposit_web_page() {
        driver = new ChromeDriver();
        driver.navigate().to("https://staging--depositobpr.netlify.app/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @When("I click on the register link")
    public void i_click_on_the_register_link() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//strong[normalize-space()='Daftar Sekarang'])[1]")).click();
    }

    @Then("I should be taken to the sign up page")
    public void i_should_be_taken_to_the_sign_up_page() {
        System.out.println("I should be taken to the sign up page");
        ChromeDriver driver = new ChromeDriver();
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://staging--depositobpr.netlify.app/";
        assertEquals(actualUrl,expectedUrl);
        driver.quit();
    }
}

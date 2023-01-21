package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Random;
import static org.testng.Assert.*;

public class StepDefinitions {
    private WebDriver driver;
    private static final String[] FIRST_NAMES = { "John", "Jane", "Bob", "Sara" };
    private static final String[] LAST_NAMES = { "Smith", "Doe", "Johnson", "Williams" };

    private final Random random = new Random();
    @Given("I am on Komunal deposit web page")
    public void i_am_on_komunal_deposit_web_page() {
        driver = new ChromeDriver();
        driver.navigate().to("https://staging--depositobpr.netlify.app/");
    }
    @When("I click on the register link")
    public void i_click_on_the_register_link() throws InterruptedException {
        Thread.sleep(10000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//strong[normalize-space()='Daftar Sekarang']")).click();
    }

    @Then("I should be taken to the sign up page")
    public void i_should_be_taken_to_the_sign_up_page() throws InterruptedException {
        Thread.sleep(8000);
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://staging--depositobpr.netlify.app/signup";
        Thread.sleep(5000);
        assertEquals(actualUrl,expectedUrl);
    ;
    }

    @Given("I am on deposan form")
    public void iAmOnDeposanForm() throws InterruptedException{
        driver = new ChromeDriver();
        driver.navigate().to("https://staging--depositobpr.netlify.app/signup");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@class='btn btn-success w-100 pt-3 pb-4']")).click();
    }

    @When("I fill all form with valid data")
    public void iFillAllFormWithValidData() throws InterruptedException, IOException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -20); // subtract 20 years from the current date
        long max = cal.getTimeInMillis();
        cal.add(Calendar.YEAR, -40); // subtract another 40 years from the date 20 years ago
        long min = cal.getTimeInMillis();
        Random random = new Random();
        long randomTimestamp = min + (long)(random.nextDouble()*(max - min));
        Calendar randomCalendar = Calendar.getInstance();
        randomCalendar.setTimeInMillis(randomTimestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        String ddmmyy = dateFormat.format(randomCalendar.getTime());
        SimpleDateFormat dateFormatLong = new SimpleDateFormat("ddMMyyyy");
        String ddmmyyyy = dateFormatLong.format(randomCalendar.getTime());

        int fourDigit = 1000 + random.nextInt(9000);

        String ktp = "340216"+ddmmyy+fourDigit;
        WebElement nik = driver.findElement(By.xpath("//input[@id='no_ktp']"));
        nik.sendKeys(ktp);
        Thread.sleep(3000);

        Random rand = new Random();
        String randomFirstName = FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)];
        String randomLastName = LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
        String nama = randomFirstName+" "+randomLastName;
        WebElement namaLengkap = driver.findElement(By.xpath("//input[@id='nama']"));
        namaLengkap.sendKeys(nama);
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        Thread.sleep(1000);
        WebElement tempatLahir = driver.findElement(By.xpath("//input[@id='tempat_lahir']"));
        tempatLahir.sendKeys("Medan");
        Thread.sleep(1000);
        WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
        email.sendKeys(ddmmyy+ddmmyyyy+"@gmail.com");
        WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
        pwd.sendKeys("Azeroth123");
        Thread.sleep(1000);
        WebElement pwdConf = driver.findElement(By.xpath("//input[@id='confirm_password']"));
        pwdConf.sendKeys("Azeroth123");
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,200)", "");
        driver.findElement(By.xpath("//button[normalize-space()='Lanjutkan']")).click();
        Thread.sleep(3000);
        String hp = "08"+ddmmyy+fourDigit;
        WebElement phoneNumber = driver.findElement(By.xpath("//input[@id='nomor_hp']"));
        phoneNumber.sendKeys(hp);
        Thread.sleep(1000);
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='bank_id']"));
        Select select = new Select(dropdown);
        select.selectByIndex(1);
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,300)", "");
        Thread.sleep(500);
        WebElement accNumb = driver.findElement(By.xpath("//input[@id='nomor_rekening']"));
        accNumb.sendKeys(ddmmyy+fourDigit);
        Thread.sleep(1000);
        WebElement fileInputKtp = driver.findElement(By.xpath("//input[@id='url_foto_ktp']"));
        fileInputKtp.sendKeys("C:/Users/amins/IdeaProjects/Komunal/src/kitty-cat.jpeg");
        Thread.sleep(2000);
        WebElement fileInputSelfie = driver.findElement(By.xpath("//input[@id='url_swafoto']"));
        fileInputSelfie.sendKeys("C:/Users/amins/IdeaProjects/Komunal/src/kitty-cat.jpeg");
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,100)", "");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.xpath("//div[@class='modal-body paper-modal']"));
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", element);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[@class='form-check-label']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//button[normalize-space()='Setujui']")).click();
        Thread.sleep(2000);
    }
    @Then("I should be taken to dashboard page")
    public void iShouldBeTakenToDashboardPage() throws InterruptedException {
        Thread.sleep(10000);
        try {
            driver.findElement(By.xpath("//input[@id='nama_bpr']"));
            Assert.assertTrue(true);
        } catch (NoSuchElementException e) {
            Assert.assertTrue(false);
        }
    }
}

package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private User user;
	private Note note;
	private Credential credential;
	private String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		baseURL = "http://localhost:" + port;
		this.user = new User(1, "admin", "P@$$", "@dm!n", "Yves A", "Cadet");
		this.note = new Note(1,"First note","Description of note #1", 1);
		this.credential = new Credential(1,"http://www.socialworld.net","admin","P@$$","mypass",1);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	/**
	 * 1.1 Unauthorized test
	 * @throws InterruptedException
	 */
	@Test
	public void unauthorizedTest() throws InterruptedException {
		//test access to login
		driver.get(this.baseURL + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
		Thread.sleep(2000);
		//test access to signup
		driver.get(this.baseURL + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		Thread.sleep(2000);
		//test access to home: redirection to login
		driver.get(this.baseURL + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		Thread.sleep(2000);
	}

	/**
	 * 1.2 Singup, Login, Logout, Unauthorized test
	 * @throws InterruptedException
	 */
	@Test
	public void singupLoginLogoutUnauthorizedTest() throws InterruptedException {
		driver.get(this.baseURL + "/signup");
		Thread.sleep(3000);
		//signup
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(this.user);
		Thread.sleep(1000);

		//login
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(this.user);
		Thread.sleep(3000);

		//logout
		HomePage homePage = new HomePage(driver);
		homePage.logout();

		//test access to home: redirection to login
		driver.get(this.baseURL + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		Thread.sleep(2000);
	}

	/**
	 * 2.1 Create note test
	 * @throws InterruptedException
	 */
	@Test
	public void createNoteTest() throws InterruptedException {
		driver.get(this.baseURL + "/signup");
		Thread.sleep(3000);
		//signup
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(this.user);
		Thread.sleep(1000);

		//login
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(this.user);

		//create and verify
		Thread.sleep(2000);
		HomePage homePage = new HomePage(driver);
		Thread.sleep(3000);
		homePage.createNote(note);
		Thread.sleep(3000);
		homePage.verifyNoteExists(note, driver);

		Thread.sleep(2000);
	}

	/**
	 * 2.2 Edit note test
	 * @throws InterruptedException
	 */
	@Test
	public void editNoteTest() throws InterruptedException {
		driver.get(this.baseURL + "/signup");
		Thread.sleep(3000);
		//signup
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(this.user);
		Thread.sleep(1000);

		//login
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(this.user);

		//create and verify
		Thread.sleep(2000);
		HomePage homePage = new HomePage(driver);
		Thread.sleep(3000);
		homePage.createNote(note);
		Thread.sleep(3000);
		homePage.verifyNoteExists(note, driver);

		//edit and verify
		Thread.sleep(2000);
		homePage = new HomePage(driver);
		Thread.sleep(3000);
		homePage.editNote(note, driver);
		Thread.sleep(3000);
		note.setNoteTitle(note.getNoteTitle() + " edited");
		note.setNoteDescription(note.getNoteDescription() + " updated");
		homePage.verifyNoteExists(note, driver);
		Thread.sleep(2000);
	}

	/**
	 * 2.3 Delete note test
	 * @throws InterruptedException
	 */
	@Test
	public void deleteNoteTest() throws InterruptedException {
		driver.get(this.baseURL + "/signup");
		Thread.sleep(3000);
		//signup
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(this.user);
		Thread.sleep(1000);

		//login
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(this.user);

		//create and verify
		Thread.sleep(2000);
		HomePage homePage = new HomePage(driver);
		Thread.sleep(3000);
		homePage.createNote(note);
		Thread.sleep(3000);
		homePage.verifyNoteExists(note, driver);

		//delete and verify
		Thread.sleep(2000);
		homePage = new HomePage(driver);
		Thread.sleep(3000);
		homePage.deleteNote(note, driver);
		Thread.sleep(3000);
		homePage.verifyNoteDeleted(note, driver);
		Thread.sleep(2000);
	}

	/**
	 * 3.1 Create credential test
	 * @throws InterruptedException
	 */
	@Test
	public void createCredentialTest() throws InterruptedException {
		driver.get(this.baseURL + "/signup");
		Thread.sleep(3000);
		//signup
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(this.user);
		Thread.sleep(1000);

		//login
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(this.user);

		//create and verify
		Thread.sleep(2000);
		HomePage homePage = new HomePage(driver);
		Thread.sleep(3000);
		homePage.createCredential(credential);
		Thread.sleep(3000);
		homePage.verifyCredentialExists(credential, driver);

		Thread.sleep(2000);
	}

	/**
	 * 3.2 Edit credential test
	 * @throws InterruptedException
	 */
	@Test
	public void editCredentialTest() throws InterruptedException {
		driver.get(this.baseURL + "/signup");
		Thread.sleep(3000);
		//signup
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(this.user);
		Thread.sleep(1000);

		//login
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(this.user);

		//create and verify
		Thread.sleep(2000);
		HomePage homePage = new HomePage(driver);
		Thread.sleep(3000);
		homePage.createCredential(credential);
		Thread.sleep(3000);
		homePage.verifyCredentialExists(credential, driver);

		//edit and verify
		Thread.sleep(2000);
		homePage = new HomePage(driver);
		Thread.sleep(3000);
		homePage.editCredential(credential, driver);
		Thread.sleep(3000);
		credential.setUrl(credential.getUrl() + "z");
		homePage.verifyCredentialExists(credential, driver);
		Thread.sleep(2000);
	}

	/**
	 * 3.3 Delete credential test
	 * @throws InterruptedException
	 */
	@Test
	public void deleteCredentialTest() throws InterruptedException {
		driver.get(this.baseURL + "/signup");
		Thread.sleep(3000);
		//signup
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(this.user);
		Thread.sleep(1000);

		//login
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(this.user);

		//create and verify
		Thread.sleep(2000);
		HomePage homePage = new HomePage(driver);
		Thread.sleep(3000);
		homePage.createCredential(credential);
		Thread.sleep(3000);
		homePage.verifyCredentialExists(credential, driver);

		//delete and verify
		Thread.sleep(2000);
		homePage = new HomePage(driver);
		Thread.sleep(3000);
		homePage.deleteCredential(credential, driver);
		Thread.sleep(3000);
		homePage.verifyCredentialDeleted(credential, driver);
		Thread.sleep(2000);
	}
}

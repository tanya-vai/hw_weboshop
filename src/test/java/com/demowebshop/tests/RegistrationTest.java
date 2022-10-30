package com.demowebshop.tests;

import org.junit.jupiter.api.DisplayName;
import com.demowebshop.pages.RegisterPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.demowebshop.tests.TestData.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.openqa.selenium.Cookie;
import com.codeborne.selenide.WebDriverRunner;

public class RegistrationTest extends TestBase {

    RegisterPage registerPage = new RegisterPage();

    @DisplayName("Creating new user and updating his name in UI ")
    @Test
    void userRegistrationAndUpdateUiTest() {

        step("Open Registration form page", () -> {
            registerPage.openRegisterPage();
        });

        step("Fill all needed data in registration form", () -> {
            registerPage.setGender(gender)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(userEmailUi)
                    .setPassword(userPassword)
                    .confirmPassword(userPassword)
                    .clickRegisterButton();
        });

        step("Check that user is created and logged in", () -> {
            registerPage.checkUseRegistration(userEmailUi);
        });
        step("Update name of user", () -> {
            registerPage.openProfilePage()
                    .setFirstName(newFirstName)
                    .clickSaveButton();
        });

        step("Check that user's name is successfully updated", () -> {
            registerPage.openProfilePage()
                    .checkUpdatedFirstName(newFirstName);
        });

    }

    @DisplayName("Creating new user(API) and updating his name(UI) ")
    @Test
    public void userRegistrationAndUpdateApiTest() {
        step("Create new user with API", () -> {
            registrationApiTest();
        });
        step("Update name of user", () -> {
            registerPage.openProfilePage()
                    .setFirstName(newFirstName)
                    .clickSaveButton();
        });

        step("Check that user's name is successfully updated", () -> {
            registerPage.openProfilePage()
                    .checkUpdatedFirstName(newFirstName);
        });

    }


    private void registrationApiTest() {
        String registerCookieName = "Nop.customer";

        String authCookieValue = given()
                .when()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam(verificationTokenName, verificationTokenBodyValue)
                .formParam("FirstName", firstName)
                .formParam("LastName", lastName)
                .formParam("Email", userEmailApi)
                .formParam("Password", userPassword)
                .formParam("ConfirmPassword", userPassword)
                .cookie(verificationTokenName, verificationTokenHeaderValue)
                .post("https://demowebshop.tricentis.com/register")
                .then()
                .log().all()
                .extract()
                .cookie(authCookieName);

        open("https://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        Cookie authCookie = new Cookie(authCookieName, authCookieValue);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        open("https://demowebshop.tricentis.com/registerresult/1");
        registerPage.checkUseRegistration(userEmailApi);
    }
}


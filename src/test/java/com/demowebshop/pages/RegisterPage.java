package com.demowebshop.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegisterPage {

    private SelenideElement
            firstNameInput = $("#FirstName"),
            lastNameInput = $("#LastName"),
            emailInput = $("#Email"),
            passwordInput = $("#Password"),
            confirmPasswordInput = $("#ConfirmPassword"),
            registerButton = $("#register-button"),
            profileButton = $(".account"),
    saveButton = $("[value=\"Save\"]");

    public RegisterPage openRegisterPage() {
        open("register");
        return this;
    }

    public RegisterPage setGender(String gender) {
        $("#gender-" + gender).click();
        return this;
    }

    public RegisterPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegisterPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegisterPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegisterPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public RegisterPage confirmPassword(String confirmPassword) {
        confirmPasswordInput.setValue(confirmPassword);
        return this;
    }

    public RegisterPage clickRegisterButton() {
        registerButton.click();
        return this;
    }

    public RegisterPage checkUseRegistration(String userEmail) {
        $(".account").shouldHave(Condition.text(userEmail));
        return this;
    }

    public RegisterPage openProfilePage() {
        profileButton.click();
        return this;
    }

    public RegisterPage clickSaveButton() {
        saveButton.click();
        return this;
    }

    public RegisterPage checkUpdatedFirstName(String newName) {
        firstNameInput.shouldHave(Condition.value(newName));
        return this;
    }


}

package com.demowebshop.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demowebshop.attach.Attach;
import com.demowebshop.config.Browser;
import com.demowebshop.config.ConfigReader;
import com.demowebshop.config.ProjectConfiguration;
import com.demowebshop.config.WebConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {
    private static final WebConfig webConfig = ConfigReader.Instance.read();
    private static ProjectConfiguration projectConfiguration = new ProjectConfiguration(webConfig);


    @BeforeAll
    static void config() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        projectConfiguration.webConfig();
        projectConfiguration.apiConfig();
    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        if (Configuration.browser.equals(Browser.CHROME.name())) {
            Attach.browserConsoleLogs();
        }
        if (projectConfiguration.isRemote()) {
            Attach.addVideo(projectConfiguration.getVideoStorageUrl());
        }
    }
}

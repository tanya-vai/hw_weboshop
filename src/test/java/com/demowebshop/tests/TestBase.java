package com.demowebshop.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.demowebshop.attach.Attach;
import com.demowebshop.config.ConfigReader;
import com.demowebshop.config.ProjectConfiguration;
import com.demowebshop.config.WebConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {
    private static final WebConfig webConfig = ConfigReader.Instance.read();


    @BeforeAll
    static void config() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        ProjectConfiguration projectConfiguration = new ProjectConfiguration(webConfig);
        projectConfiguration.webConfig();
        projectConfiguration.apiConfig();
    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (System.getProperty("remote_url") != null) {
            Attach.addVideo();
        }
    }
}

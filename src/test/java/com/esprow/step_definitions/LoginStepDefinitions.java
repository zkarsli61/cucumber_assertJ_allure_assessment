package com.esprow.step_definitions;

import com.esprow.managers.PageObjectManager;
import com.esprow.pages.LoginPage;
import com.esprow.utilities.ConfigurationReader;
import io.cucumber.java.en.*;

public class LoginStepDefinitions {

    LoginPage loginPage = new PageObjectManager().getLoginPage();

    @Given("sign in with credentials")
    public void user_sign_in_with_credentials() {
        String userName = ConfigurationReader.getProperty("user_name");
        String password = ConfigurationReader.getProperty("password");
        loginPage.login(userName, password);
    }
}


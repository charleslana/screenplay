package com.e2etemplate.questions;

import com.e2etemplate.actions.Action;
import com.e2etemplate.base.TestBase;
import com.e2etemplate.pages.LoginCadastroPage;

public class LoginCadastroQuestion {
    private final Action action;
    private final LoginCadastroPage loginCadastroPage = new LoginCadastroPage();

    public LoginCadastroQuestion(TestBase testBase) {
        action = new Action(testBase);
    }
}

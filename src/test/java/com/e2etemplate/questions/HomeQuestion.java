package com.e2etemplate.questions;

import com.e2etemplate.actions.Action;
import com.e2etemplate.base.TestBase;
import com.e2etemplate.pages.HomePage;

public class HomeQuestion {
    private final Action action;
    private final HomePage homePage = new HomePage();

    public HomeQuestion(TestBase testBase) {
        action = new Action(testBase);
    }

    public void oTituloDaPesquisaCorrespondeA(String nome) {
        action.verificarTemTexto(homePage.tituloDaPesquisa, nome);
    }

    public void oNomeDoUsuarioLogadoCorrespondeA(String nome) {
        action.verificarTemTexto(homePage.nomeDoUsuario, nome);
    }
}

package com.e2etemplate.tasks;

import com.e2etemplate.actions.Action;
import com.e2etemplate.base.TestBase;
import com.e2etemplate.pages.HomePage;
import com.e2etemplate.utils.Utils;

public class HomeTask {

    private final Action action;
    private final HomePage homePage = new HomePage();

    public HomeTask(TestBase testBase) {
        action = new Action(testBase);
    }

    public void acessarPaginaInicial() {
        action.navegar(Utils.getUrl());
    }

    public void pesquisarProduto(String nome) {
        action.digitar(homePage.campoPesquisarProduto, nome);
        action.pressionarEnter(homePage.campoPesquisarProduto);
    }

    public void clicarFazerLoginOuCadastro() {
        action.clicar(homePage.fazerLoginOuCadastro);
    }
}

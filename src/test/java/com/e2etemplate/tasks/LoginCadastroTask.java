package com.e2etemplate.tasks;

import com.e2etemplate.actions.Action;
import com.e2etemplate.base.TestBase;
import com.e2etemplate.pages.LoginCadastroPage;

public class LoginCadastroTask {

    private final Action action;
    private final LoginCadastroPage loginCadastroPage = new LoginCadastroPage();

    public LoginCadastroTask(TestBase testBase) {
        action = new Action(testBase);
    }

    public void clicarNaoTemConta() {
        action.clicar(loginCadastroPage.acaoDeCadastro);
    }

    public void digitarEmail(String email) {
        action.preencher(loginCadastroPage.campoEmail, email);
    }

    public void clicarBotaoContinuar() {
        action.clicar(loginCadastroPage.botaoContinuar);
    }

    public void digitarCpf(String cpf) {
        action.preencher(loginCadastroPage.campoCpf, cpf);
    }

    public void digitarNumeroTelefone(String numeroTelefone) {
        action.preencher(loginCadastroPage.campoNumeroTelefone, numeroTelefone);
    }

    public void digitarNome(String nome) {
        action.preencher(loginCadastroPage.campoNome, nome);
    }

    public void digitarSobrenome(String sobrenome) {
        action.preencher(loginCadastroPage.campoSobrenome, sobrenome);
    }

    public void digitarDataDeNascimento(String dataNascimento) {
        action.preencher(loginCadastroPage.campoDataDeNascimento, dataNascimento);
    }

    public void digitarSenha(String senha) {
        action.preencher(loginCadastroPage.campoSenha, senha);
    }

    public void clicarCaixaDeSelecaoPoliticaDePrivacidade() {
        action.clicar(loginCadastroPage.caixaDeSelecaoPoliticaDePrivacidade, true);
    }

    public void digitarCep(String cep) {
        action.preencher(loginCadastroPage.campoCep, cep);
        action.clicar(loginCadastroPage.campoEndereco);
        action.esperarPorRequisicao(loginCadastroPage.requisicaoCepUrl);
    }

    public void digitarNumeroEndereco(String numeroEndereco) {
        action.preencher(loginCadastroPage.numeroDaResidencia, numeroEndereco);
    }

    public void selecionarCaixaDeSelecaoTipoEndereco() {
        action.clicar(loginCadastroPage.caixaDeSelecaoCasa);
    }

    public void clicarBotaoFinalizar() {
        action.clicar(loginCadastroPage.botaoFinalizar);
    }
}

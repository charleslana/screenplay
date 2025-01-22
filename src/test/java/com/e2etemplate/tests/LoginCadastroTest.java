package com.e2etemplate.tests;

import com.e2etemplate.base.TestBase;
import com.e2etemplate.questions.HomeQuestion;
import com.e2etemplate.tasks.HomeTask;
import com.e2etemplate.tasks.LoginCadastroTask;
import com.e2etemplate.utils.GeraCpfCnpj;
import com.e2etemplate.utils.Utils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginCadastroTest extends TestBase {
    private HomeTask homeTask;
    private LoginCadastroTask loginCadastroTask;
    private HomeQuestion homeQuestion;

    @BeforeMethod
    public void setUp() {
        homeTask = new HomeTask(this);
        loginCadastroTask = new LoginCadastroTask(this);
        homeQuestion = new HomeQuestion(this);
    }

    @Test(priority = 1, description = "Cadastro de usuário com sucesso", groups = "Sucesso")
    public void fazerCadastroDeUsuario() {
        homeTask.acessarPaginaInicial();
        homeTask.clicarFazerLoginOuCadastro();
        loginCadastroTask.clicarNaoTemConta();
        loginCadastroTask.digitarEmail(Utils.gerarEmail());
        loginCadastroTask.clicarBotaoContinuar();
        loginCadastroTask.digitarCpf(GeraCpfCnpj.cpf());
        loginCadastroTask.clicarBotaoContinuar();
        loginCadastroTask.digitarNumeroTelefone(Utils.gerarTelefone());
        String nome = Utils.gerarNome();
        loginCadastroTask.digitarNome(nome);
        loginCadastroTask.digitarSobrenome(Utils.gerarSobrenome());
        loginCadastroTask.digitarDataDeNascimento(Utils.gerarDataNascimento());
        loginCadastroTask.digitarSenha(Utils.gerarSenha());
        loginCadastroTask.clicarCaixaDeSelecaoPoliticaDePrivacidade();
        loginCadastroTask.clicarBotaoContinuar();
        loginCadastroTask.digitarCep(Utils.gerarCep());
        loginCadastroTask.digitarNumeroEndereco(Utils.gerarNumeroEndereco());
        loginCadastroTask.selecionarCaixaDeSelecaoTipoEndereco();
        loginCadastroTask.clicarBotaoFinalizar();
        homeQuestion.oNomeDoUsuarioLogadoCorrespondeA(nome);
    }
}

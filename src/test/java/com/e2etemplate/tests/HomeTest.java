package com.e2etemplate.tests;

import com.e2etemplate.base.TestBase;
import com.e2etemplate.questions.HomeQuestion;
import com.e2etemplate.tasks.HomeTask;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeTest extends TestBase {
    private HomeTask homeTask;
    private HomeQuestion homeQuestion;

    @BeforeMethod
    public void setUp() {
        homeTask = new HomeTask(this);
        homeQuestion = new HomeQuestion(this);
    }

    @Test(priority = 1, description = "Pesquisar dipirona com sucesso", groups = "Sucesso")
    public void pesquisarDipirona() {
        homeTask.acessarPaginaInicial();
        homeTask.pesquisarProduto("dipirona");
        homeQuestion.oTituloDaPesquisaCorrespondeA("dipirona");
    }
}

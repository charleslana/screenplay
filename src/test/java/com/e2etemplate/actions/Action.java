package com.e2etemplate.actions;

import com.aventstack.extentreports.Status;
import com.e2etemplate.base.TestBase;
import com.e2etemplate.report.ExtentManager;
import com.e2etemplate.utils.ConfigProperties;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.WaitUntilState;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Action {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Page page;
    private final Integer defaultTimeout;

    public Action(TestBase testBase) {
        page = testBase.getPage();
        defaultTimeout = ConfigProperties.getIntProperty("default.timeout");
    }

    public void navegar(String url) {
        try {
            logInfo("Navegando para a URL: " + url);
            page.navigate(url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
            logSuccess("Navegação para a URL " + url + " concluída com sucesso.");
        } catch (Exception e) {
            logError("Erro ao navegar para a URL: " + url, e);
            throw e;
        }
    }

    public void digitar(String selector, String text) {
        Locator locator = buscarLocator(selector);
        try {
            logInfo("Digitando o texto '" + text + "' no seletor '" + selector + "'");
            locator.pressSequentially(text, new Locator.PressSequentiallyOptions().setDelay(100));
            logSuccess("Texto '" + text + "' digitado com sucesso no seletor '" + selector + "'");
        } catch (Exception e) {
            logError("Erro ao digitar o texto '" + text + "' no seletor '" + selector + "'", e);
            throw e;
        }
    }

    public void preencher(String selector, String text) {
        try {
            logInfo("Preenchendo o campo '" + selector + "' com o texto '" + text + "'");
            page.fill(selector, text);
            logSuccess("Campo '" + selector + "' preenchido com sucesso com o texto '" + text + "'");
        } catch (Exception e) {
            logError("Erro ao preencher o campo '" + selector + "' com o texto '" + text + "'", e);
            throw e;
        }
    }

    public void sleep(long milliseconds) {
        try {
            logInfo("Aguardando por " + milliseconds + " milissegundos...");
            page.waitForTimeout(milliseconds);
            logSuccess("Espera de " + milliseconds + " milissegundos concluída.");
        } catch (Exception e) {
            logError("Erro durante a espera de " + milliseconds + " milissegundos.", e);
            throw e;
        }
    }

    public void esperarPor(String selector) {
        try {
            logInfo("Esperando pelo seletor '" + selector + "'");
            Locator locator = page.locator(selector);
            locator.waitFor(new Locator.WaitForOptions().setTimeout(defaultTimeout));
            logSuccess("Seletor '" + selector + "' encontrado.");
        } catch (Exception e) {
            logError("Erro ao esperar pelo seletor '" + selector + "'", e);
            throw e;
        }
    }

    public Locator buscarLocator(String selector) {
        try {
            logInfo("Buscando o locator para o seletor '" + selector + "'");
            Locator locator = page.locator(selector);
            locator.waitFor(new Locator.WaitForOptions().setTimeout(defaultTimeout));
            logSuccess("Locator para o seletor '" + selector + "' encontrado com sucesso.");
            return locator;
        } catch (Exception e) {
            logError("Erro ao buscar o locator para o seletor '" + selector + "'", e);
            throw e;
        }
    }

    public void pressionarEnter(String selector) {
        try {
            logInfo("Pressionando a tecla Enter no seletor '" + selector + "'");
            page.press(selector, "Enter");
            logSuccess("Tecla Enter pressionada com sucesso no seletor '" + selector + "'");
        } catch (Exception e) {
            logError("Erro ao pressionar a tecla Enter no seletor '" + selector + "'", e);
            throw e;
        }
    }

    public void clicar(String selector, Boolean isForcado) {
        try {
            logInfo("Clicando no seletor '" + selector + "'");
            page.click(selector, new Page.ClickOptions().setForce(isForcado));
            logSuccess("Clique realizado com sucesso no seletor '" + selector + "'");
        } catch (Exception e) {
            logError("Erro ao clicar no seletor '" + selector + "'", e);
            throw e;
        }
    }

    public void clicar(String selector) {
        clicar(selector, false);
    }

    public void esperarPorRequisicao(String urlParcial, Runnable acao) {
        try {
            logInfo("Esperando pela requisição com parte da URL: " + urlParcial);
            page.waitForResponse(
                    response -> response.url().contains(urlParcial) && response.status() == 200, acao
            );
            logSuccess("Requisição com parte da URL '" + urlParcial + "' interceptada com sucesso.");
        } catch (Exception e) {
            logError("Erro ao esperar pela requisição com parte da URL: " + urlParcial, e);
            throw e;
        }
    }

    public void esperarPorRequisicao(String urlParcial) {
        esperarPorRequisicao(urlParcial, () -> {
        });
    }

    public void verificarTemTexto(String selector, String textoEsperado) {
        Locator locator = buscarLocator(selector);
        try {
            logInfo("Verificando se o elemento '" + selector + "' contém o texto: '" + textoEsperado + "'");
            LocatorAssertions.HasTextOptions options = new LocatorAssertions.HasTextOptions().setTimeout(defaultTimeout);
            assertThat(locator).hasText(textoEsperado, options);
            logSuccess("O elemento '" + selector + "' contém o texto esperado: '" + textoEsperado + "'");
        } catch (AssertionFailedError e) {
            String textoAtual = locator.textContent();
            String mensagemDetalhada = "Falha de validação: O elemento '" + selector +
                    "' deveria conter o texto: '" + textoEsperado + "', mas o texto atual encontrado foi: '" + textoAtual + "'";
            logError(mensagemDetalhada, new Exception(e));
            throw e;
        } catch (Exception e) {
            logError("Erro ao verificar o texto no elemento '" + selector + "'", e);
            throw e;
        }
    }

    private void logInfo(String message) {
        ExtentManager.getTest().log(Status.INFO, "<pre>" + message + "</pre>");
        logger.info(message);
    }

    private void logError(String message, Exception e) {
        String stackTrace = "<pre>" + e + "</pre>";
        ExtentManager.getTest().log(Status.FAIL, "<pre>" + message + "</pre>" + stackTrace);
        logger.error(message, e);
    }

    private void logSuccess(String message) {
        ExtentManager.getTest().log(Status.PASS, "<pre>" + message + "</pre>");
        logger.info(message);
    }
}

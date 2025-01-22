package com.e2etemplate.pages;

public class HomePage {
    public final String campoPesquisarProduto = "//*[@placeholder='Precisou? Araujo Tem']";
    public final String tituloDaPesquisa = "//*[@class='searchResults__title']//h1[contains(text(), '')]";
    public final String fazerLoginOuCadastro = "//*[@class='header__nav__profile']";
    public final String nomeDoUsuario = "//*[@class='profileName' and contains(text(), '')]";
}

package com.e2etemplate.utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Utils {
    private static final Faker faker = new Faker(Locale.forLanguageTag("pt-BR"));

    public static String getUrl() {
        return ConfigProperties.getProperty("url");
    }

    public static String gerarEmail() {
        return faker.internet().emailAddress();
    }

    public static String gerarNome() {
        return faker.address().firstName();
    }

    public static String gerarSobrenome() {
        return faker.address().lastName();
    }

    public static String gerarTelefone() {
        String numeroCelular = "9" + faker.number().digits(8);
        return String.format("(%s) %s-%s", "11", numeroCelular.substring(0, 5), numeroCelular.substring(5));
    }

    public static String gerarDataNascimento() {
        int idadeMinima = 18;
        int idadeMaxima = 40;
        Date dataHoje = new Date();
        long idadeMaximaMillis = dataHoje.getTime() - (long) idadeMaxima * 365 * 24 * 60 * 60 * 1000;
        long idadeMinimaMillis = dataHoje.getTime() - (long) idadeMinima * 365 * 24 * 60 * 60 * 1000;
        long dataAleatoriaMillis = faker.date().between(new Date(idadeMaximaMillis), new Date(idadeMinimaMillis)).getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date(dataAleatoriaMillis));
    }

    public static String gerarSenha() {
        int tamanho = 12;
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numeros = "0123456789";
        String caracteresEspeciais = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";
        StringBuilder senha = new StringBuilder(tamanho);
        Random random = new Random();
        senha.append(letras.charAt(random.nextInt(letras.length())));
        senha.append(numeros.charAt(random.nextInt(numeros.length())));
        String todosCaracteres = letras + numeros + caracteresEspeciais;
        for (int i = senha.length(); i < tamanho; i++) {
            senha.append(todosCaracteres.charAt(random.nextInt(todosCaracteres.length())));
        }
        String senhaFinal = senha.toString();
        char[] senhaArray = senhaFinal.toCharArray();
        for (int i = 0; i < senhaArray.length; i++) {
            int j = random.nextInt(senhaArray.length);
            char temp = senhaArray[i];
            senhaArray[i] = senhaArray[j];
            senhaArray[j] = temp;
        }
        return new String(senhaArray);
    }

    public static String gerarCep() {
        return "12332131";
    }

    public static String gerarNumeroEndereco() {
        int numeroEndereco = faker.number().numberBetween(1, 100000);
        return String.valueOf(numeroEndereco);
    }

    public static void main(String[] args) {
        System.out.println(gerarSenha());
    }
}

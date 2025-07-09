package br.com.alura.moneify.service;

import br.com.alura.moneify.model.Conversao;
import br.com.alura.moneify.util.Mensagens;

public class ConversorMoedas {

    private ConversorMoedas() {}

    public static Conversao converter(String moedaOrigem, String moedaDestino, double valor) {
        if (valor <= 0) {
            System.out.println(Mensagens.VALOR_NEGATIVO);
            return null;
        }

        double cotacao;
        try {
            cotacao = ApiConversor.buscarCotacao(moedaOrigem, moedaDestino);
        } catch (Exception e) {
            System.out.printf(Mensagens.ERRO_COTACAO + "%n", moedaOrigem, moedaDestino);
            return null;
        }

        if (cotacao <= 0) {
            System.out.println(Mensagens.ERRO_CONVERSAO);
            return null;
        }

        double resultado = valor * cotacao;
        return new Conversao(moedaOrigem, moedaDestino, valor, resultado);
    }
}

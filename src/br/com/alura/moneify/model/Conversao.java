package br.com.alura.moneify.model;

public record Conversao(
        String moedaOrigem,
        String moedaDestino,
        double valor,
        double resultado
) {}

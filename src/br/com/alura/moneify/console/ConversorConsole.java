package br.com.alura.moneify.console;

import br.com.alura.moneify.model.Conversao;
import br.com.alura.moneify.service.ConversorMoedas;
import br.com.alura.moneify.util.Mensagens;

import java.util.Scanner;

import java.text.DecimalFormat;

public class ConversorConsole {

    private final Scanner scanner = new Scanner(System.in);

    public void executar() {
        System.out.println("🖥️ Bem-vindo ao Moneify (modo Console)");

        boolean continuar = true;

        while (continuar) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("7")) {
                System.out.println(Mensagens.SAINDO);
                break;
            }

            String moedaOrigem = "";
            String moedaDestino = "";

            switch (opcao) {
                case "1" -> { moedaOrigem = "USD"; moedaDestino = "ARS"; }
                case "2" -> { moedaOrigem = "USD"; moedaDestino = "BRL"; }
                case "3" -> { moedaOrigem = "USD"; moedaDestino = "COP"; }
                case "4" -> { moedaOrigem = "ARS"; moedaDestino = "BRL"; }
                case "5" -> { moedaOrigem = "COP"; moedaDestino = "BRL"; }
                case "6" -> { moedaOrigem = "BRL"; moedaDestino = "USD"; }
                default -> {
                    System.out.println(Mensagens.VALOR_INVALIDO);
                    continue;
                }
            }

            System.out.print(Mensagens.TEXTO_INSERIR_VALOR + " ");
            try {
                double valor = Double.parseDouble(scanner.nextLine());
                Conversao conversao = ConversorMoedas.converter(moedaOrigem, moedaDestino, valor);

                if (conversao != null) {
                    DecimalFormat formatoBr = new DecimalFormat("###,###,##0.00");
                    System.out.printf("💱 %s %s equivalem a %s %s%n",
                            formatoBr.format(conversao.valor()),
                            conversao.moedaOrigem(),
                            formatoBr.format(conversao.resultado()),
                            conversao.moedaDestino());
                }
            } catch (NumberFormatException e) {
                System.out.println(Mensagens.VALOR_INVALIDO);
            }

            System.out.println("\nDeseja realizar outra conversão?");
            System.out.print("Digite 1 para continuar ou 0 para encerrar: ");
            String resposta = scanner.nextLine();

            if (!resposta.equals("1")) {
                System.out.println(Mensagens.SAINDO);
                continuar = false;
            }
        }
    }

    private void exibirMenu() {
        System.out.print(Mensagens.MENU_OPCOES);
    }
}

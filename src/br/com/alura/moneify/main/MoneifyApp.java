package br.com.alura.moneify.main;

import br.com.alura.moneify.console.ConversorConsole;
import br.com.alura.moneify.gui.ConversorGUI;

import java.util.Scanner;

public class MoneifyApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("""
            ✨ Boas-vindas ao Moneify, seu Conversor de Moedas 💶💷💴
            Escolha o modo de uso:
            1) Console
            2) Interface Gráfica (GUI)
            """);

        String escolha = scanner.nextLine();

        switch (escolha) {
            case "1" -> {
                ConversorConsole console = new ConversorConsole();
                console.executar();
            }
            case "2" -> {
                ConversorGUI gui = new ConversorGUI();
                gui.executar();
            }
            default -> System.out.println("Opção inválida. Tente novamente!");
        }
    }
}

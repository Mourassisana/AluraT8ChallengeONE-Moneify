package br.com.alura.moneify.util;

public class Mensagens {

    private Mensagens() {}

    public static final String MENU_OPCOES = """
        \n********** Moneify, Converta, comande, controle. **********
                         Seu bolso, sua linguagem.
        ____________________
        Escolha uma opção:
        1) USD >> ARS
        2) USD >> BRL
        3) USD >> COP
        4) ARS >> BRL
        5) COP >> BRL
        6) BRL >> USD
        7) Sair
        ____________________       
        """;

    public static final String TEXTO_INSERIR_VALOR =
            "Insira o valor para conversão (use ponto para centavos e casas decimais):";

    public static final String VALOR_INVALIDO =
            "Valor inválido! Digite um número válido.";

    public static final String VALOR_NEGATIVO =
            "O valor deve ser maior que zero.";

    public static final String SAINDO =
            "Encerrando o Moneify... até mais!";

    public static final String ERRO_CONVERSAO =
            "Não foi possível realizar a conversão.";

    public static final String ERRO_COTACAO =
            "Erro ao buscar cotação de %s para %s.";

    public static final String RESULTADO_CONVERSAO =
            "💱 %.2f %s equivalem a %.2f %s%n";

    public static final String TEXTO_CREDITOS =
            "Challenge Alura ONE - T8";

    public static final String TITULO_JANELA =
            "Moneify - Conversor de Moedas 💶💷💴";

    public static final String MOEDAS_REPETIDAS = "Ops! Escolha moedas diferentes para fazer a conversão.";

}

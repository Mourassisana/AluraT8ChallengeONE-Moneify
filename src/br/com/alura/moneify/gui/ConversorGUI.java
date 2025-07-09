package br.com.alura.moneify.gui;

import br.com.alura.moneify.model.Conversao;
import br.com.alura.moneify.service.ConversorMoedas;
import br.com.alura.moneify.util.Mensagens;

import javax.swing.*;
import java.awt.*;

import java.text.DecimalFormat;

public class ConversorGUI {

    private JComboBox<String> comboOrigem;
    private JComboBox<String> comboDestino;
    private JTextField campoValor;
    private JButton botaoConverter;
    private JLabel labelValorResultado;

    public void executar() {
        JFrame frame = new JFrame(Mensagens.TITULO_JANELA);
        frame.setMinimumSize(new Dimension(400, 320));
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Font fontePadrao = new Font("SansSerif", Font.PLAIN, 12);

        JLabel labelOrigem = new JLabel("Moeda de origem:");
        labelOrigem.setBounds(25, 20, 120, 20);
        frame.add(labelOrigem);

        String[] moedas = {"USD", "BRL", "ARS", "COP"};
        comboOrigem = new JComboBox<>(moedas);
        comboOrigem.setBounds(25 + 130, 20, 120, 20);
        comboOrigem.setFont(fontePadrao);
        frame.add(comboOrigem);

        JLabel labelDestino = new JLabel("Moeda de destino:");
        labelDestino.setBounds(25, 60, 120, 20);
        frame.add(labelDestino);

        comboDestino = new JComboBox<>(moedas);
        comboDestino.setBounds(25 + 130, 60, 120, 20);
        comboDestino.setFont(fontePadrao);
        frame.add(comboDestino);

        JLabel labelValor = new JLabel("Valor:");
        labelValor.setBounds(25, 100, 120, 23);
        frame.add(labelValor);

        campoValor = new JTextField();
        campoValor.setBounds(25 + 130, 100, 121, 23);
        frame.add(campoValor);

        botaoConverter = new JButton("Converter");
        botaoConverter.setBounds(140, 155, 120, 30);
        botaoConverter.setEnabled(false);
        frame.add(botaoConverter);

        JLabel labelTituloResultado = new JLabel("Resultado:");
        labelTituloResultado.setBounds(25, 195, 120, 20);
        labelTituloResultado.setFont(new Font("SansSerif", Font.BOLD, 13));
        frame.add(labelTituloResultado);

        labelValorResultado = new JLabel("");
        labelValorResultado.setBounds(22, 215, 340, 25);
        labelValorResultado.setFont(new Font("SansSerif", Font.PLAIN, 14));
        labelValorResultado.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        labelValorResultado.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(labelValorResultado);

        JLabel creditos = new JLabel(Mensagens.TEXTO_CREDITOS);
        creditos.setBounds(25, 255, 340, 20);
        creditos.setFont(new Font("SansSerif", Font.PLAIN, 11));
        creditos.setHorizontalAlignment(SwingConstants.CENTER);
        creditos.setForeground(Color.DARK_GRAY);
        frame.add(creditos);

        campoValor.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { validarCampos(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { validarCampos(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}
        });

        comboOrigem.addActionListener(e -> botaoConverter.setEnabled(valido()));
        comboDestino.addActionListener(e -> botaoConverter.setEnabled(valido()));

        botaoConverter.addActionListener(e -> {
            String moedaO = comboOrigem.getSelectedItem().toString();
            String moedaD = comboDestino.getSelectedItem().toString();

            if (moedaO.equals(moedaD)) {
                JOptionPane.showMessageDialog(frame, Mensagens.MOEDAS_REPETIDAS, "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                double valor = Double.parseDouble(campoValor.getText().replace(",", "."));
                if (valor <= 0) {
                    labelValorResultado.setText(Mensagens.VALOR_NEGATIVO);
                    return;
                }

                Conversao conversao = ConversorMoedas.converter(moedaO, moedaD, valor);

                if (conversao != null) {
                    DecimalFormat formatoBr = new DecimalFormat("###,###,##0.00");
                    String texto = formatoBr.format(conversao.valor()) + " " + moedaO +
                            " = " +
                            formatoBr.format(conversao.resultado()) + " " + moedaD;

                    labelValorResultado.setText(texto);
                } else {
                    labelValorResultado.setText(Mensagens.ERRO_CONVERSAO);
                }

            } catch (NumberFormatException ex) {
                labelValorResultado.setText(Mensagens.VALOR_INVALIDO);
            }

            campoValor.setText("");
            botaoConverter.setEnabled(false);
        });

        frame.setVisible(true);
    }

    private void validarCampos() {
        botaoConverter.setEnabled(valido());
    }

    private boolean valido() {
        String origem = comboOrigem.getSelectedItem().toString();
        String destino = comboDestino.getSelectedItem().toString();
        boolean moedasValidas = !origem.equals(destino);
        boolean valorPresente = !campoValor.getText().trim().isEmpty();
        return moedasValidas && valorPresente;
    }
}

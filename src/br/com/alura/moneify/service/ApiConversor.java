package br.com.alura.moneify.service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import org.json.JSONObject;

public class ApiConversor {

    private ApiConversor() {}

    public static double buscarCotacao(String moedaOrigem, String moedaDestino) throws IOException {
        String apiKey = carregarChaveApi();

        String urlStr = String.format(
                "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s",
                apiKey, moedaOrigem, moedaDestino
        );

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder resposta = new StringBuilder();
        String linha;
        while ((linha = reader.readLine()) != null) {
            resposta.append(linha);
        }
        reader.close();
        conn.disconnect();

        JSONObject json = new JSONObject(resposta.toString());

        if (json.has("conversion_rate")) {
            return json.getDouble("conversion_rate");
        } else {
            throw new IOException("Cotação não encontrada para " + moedaOrigem + " → " + moedaDestino);
        }
    }

    private static String carregarChaveApi() throws IOException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("config/config.properties");
        props.load(fis);
        return props.getProperty("apiKey");
    }
}

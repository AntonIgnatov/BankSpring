package net.bigmir.venzor.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.bigmir.venzor.enteties.Curensy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurensyJson {
    private HashMap<String, Double> rates;


    public CurensyJson(HashMap<String, Double> rates) {
        this.rates = rates;
    }

    public HashMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, Double> rates) {
        this.rates = rates;
    }

    public static List<Curensy> initCurensy() {
        List<Curensy> ratesList = new ArrayList<>();
        String request = "http://data.fixer.io/api/latest?access_key=2f09b70e4b7f77b4cf0901246f459e96&format=1";
        String result = null;
        try {
            result = performRequest(request);
        } catch (IOException e){
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().create();
        CurensyJson cur = gson.fromJson(result, CurensyJson.class);
        double curUAH = cur.getRates().get("UAH");
        double curUSD = cur.getRates().get("USD");
        ratesList.add(new Curensy("UAH", 1));
        ratesList.add(new Curensy("USD", curUSD/curUAH));
        ratesList.add(new Curensy("EUR", 1/curUAH));
        return ratesList;

    }

    public static String performRequest(String urlAddress) throws IOException {
        String result = "";
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String temp = "";
                for (; (temp = br.readLine()) != null; ) {
                    result += temp;
                    result += System.lineSeparator();
                }
            }
        } catch (IOException e) {
            throw e;
        }
        return result;
    }
}

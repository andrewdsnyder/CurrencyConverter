package com;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttpClient;
import org.json.JSONObject;

public class CurrencyConverter {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type currency code to convert from");
        String convertFrom = scanner.nextLine();
        System.out.println("Type currency code to convert to");
        String convertTo = scanner.nextLine();
        System.out.println("enter the amount of currency");
        BigDecimal amount = scanner.nextBigDecimal();

        String urlString = "https://api.frankfurter.app/latest?base=" + convertFrom.toUpperCase();

        //makes request to external API
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlString)
                .get()
                .build();

        //extract JSON response from external API
        Response response = client.newCall(request).execute();
        String stringResponse = response.body().string();
        JSONObject jsonObject = new JSONObject(stringResponse);
        JSONObject ratesObject = jsonObject.getJSONObject("rates");
        BigDecimal rate = ratesObject.getBigDecimal(convertTo.toUpperCase());

        BigDecimal result = rate.multiply(amount);
        System.out.println(result);
    }
}

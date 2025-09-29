package net.weesli.ravenurelicenseapi.http;

import net.weesli.ravenurelicenseapi.enums.LicenseStatus;
import net.weesli.ravenurelicenseapi.model.LicenseResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestHandler {

    public static LicenseResponse handleRequest(String key) {
        try {
            var client = HttpClient.newHttpClient();

            var request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(String.format("https://license.ravenure.com/api/public/license/validate/%s", key)));
            var response = client.send(request.build(), HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200){
                return  new LicenseResponse(LicenseStatus.FAILED, parse(response.body()));
            }else {
                return new LicenseResponse(LicenseStatus.VALID, parse(response.body()));
            }
        }catch (Exception e){
            return new LicenseResponse(LicenseStatus.FAILED, e.getMessage());
        }
    }

    private static String parse(String body){
        return body.split("\\|")[1];
    }
}

package net.weesli.ravenurelicenseapi;

import net.weesli.ravenurelicenseapi.enums.LicenseStatus;
import net.weesli.ravenurelicenseapi.http.RequestHandler;
import net.weesli.ravenurelicenseapi.model.LicenseResponse;

import java.io.IOException;
import java.io.InputStream;

public class RavenureLicenseAPI {

    public static boolean check(String key){
        return RequestHandler.handleRequest(key).status() == LicenseStatus.VALID;
    }

    public static boolean check(InputStream inputStream){
        try {
            String fileContent = new String(inputStream.readAllBytes());
            String[] lines = fileContent.split("\n");
            String key = lines[0].split("=")[1].trim();
            return check(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static LicenseResponse getResponse(InputStream inputStream){
        try {
            String fileContent = new String(inputStream.readAllBytes());
            String[] lines = fileContent.split("\n");
            String key = lines[0].split("=")[1].trim();
            return getResponse(key);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String getMessage(String key){
        return RequestHandler.handleRequest(key).message();
    }
    public static LicenseResponse getResponse(String key){
        return RequestHandler.handleRequest(key);
    }
}

package com.example.simo.juzawo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Httpmanager {

    public static String getData(String uri){

        HttpURLConnection httpURLConnection;
        BufferedReader reader = null;

        try {
            URL url = new URL(uri);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if (reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }

}



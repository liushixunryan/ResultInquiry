package com.example.ryan.resultinquiry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ryan on 2018/11/22.
 */

public class Fetcher {
    private String address;


    public Fetcher(String address){
        this.address=address;
    }

    public String fetcher(){

        String info="";
        try {
            URL url=new URL(address);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream in=con.getInputStream();
            //String encoding=con.getContentEncoding();
            BufferedReader buf=new BufferedReader(new InputStreamReader(in));
            String s=buf.readLine();
            info=new String(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return info;
    }
}

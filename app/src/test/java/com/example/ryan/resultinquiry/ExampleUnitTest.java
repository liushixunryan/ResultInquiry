package com.example.ryan.resultinquiry;

import android.util.Log;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test() {
        try {
            URL url = new URL("http://10.1.33.18:8080/test");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream in = con.getInputStream();
            //String encoding=con.getContentEncoding();
            BufferedReader buf = new BufferedReader(new InputStreamReader(in));
            String s = buf.readLine();

            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
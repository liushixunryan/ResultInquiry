package com.example.ryan.resultinquiry;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import app.mrquan.factory.Factory;
import app.mrquan.pojo.Score;
//http://10.1.33.18:8080/test  这是idea服务器抓的
public class MainActivity extends AppCompatActivity {
    private String address="https://api.myjson.com/bins/a0m4q";
    private ListView listView_item;
    private List<Score> scores;
    private ScoreAdapter adapter;
    private Handler handler;
    private List<String> ss=new ArrayList<String>();
    private ArrayAdapter<String> adapter1;
    private AutoCompleteTextView actv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data();

        actv=findViewById(R.id.actv);
        listView_item=findViewById(R.id.list_item);
        scores=new ArrayList<Score>();
        adapter=new ScoreAdapter(this,R.layout.score_layout,scores);
        listView_item.setAdapter(adapter);
        adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,ss);
        actv.setAdapter(adapter1);//这个adapter应该只传一个名字

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what==1){
                    adapter.notifyDataSetChanged();
                    adapter1.notifyDataSetChanged();
                }
            }
        };
    }


    public void chaxun(View view) {
        new Thread(){
            @Override
            public void run() {
                String info = new Fetcher("https://api.myjson.com/bins/a0m4q").fetcher();
                try {
                    scores.clear();
                    JSONArray jsa = new JSONArray(info);

                    for (int i=0;i<jsa.length();i++){
                        JSONObject jsonObject=jsa.getJSONObject(i);
                        String name1=actv.getText().toString();
                        String namee=jsonObject.getString("name");

                        if (namee.equals(name1)){
                        Score s=new Score();
                        s.setClassName(jsonObject.getString("className"));
                        s.setName(jsonObject.getString("name"));
                        s.setGrade(jsonObject.getInt("grade"));
                        scores.add(s);
                        }
                        if (name1.equals("")){
                            Score s=new Score();
                            s.setClassName(jsonObject.getString("className"));
                            s.setName(jsonObject.getString("name"));
                            s.setGrade(jsonObject.getInt("grade"));
                            scores.add(s);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(1);
            }
        }.start();
    }
    public String  data(){
        new Thread(){
            @Override
            public void run() {
                try {
                    String ii=new Fetcher("https://api.myjson.com/bins/a0m4q").fetcher();
                    JSONArray jso = new JSONArray(ii);
                    for (int i=0;i<jso.length();i++) {
                        JSONObject jsonObject = jso.getJSONObject(i);
                        String namee = jsonObject.getString("name");
                        ss.add(namee);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return String.valueOf(ss);
    }
}

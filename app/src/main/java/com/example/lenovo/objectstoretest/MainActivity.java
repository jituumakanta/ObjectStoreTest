package com.example.lenovo.objectstoretest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Connection;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonRetrive = findViewById(R.id.buttonRetrive);


        final String KEY_CONNECTIONS = "KEY_CONNECTIONS";
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();

        List<Bean> objlist = new LinkedList<>();

        Bean bean;
        for(int i=0;i<10;i++) {
            bean=new Bean();
            String n=Integer.toString(i);
            bean.setA(n+"abc");
            bean.setB(n+"xyz");
            objlist.add(bean);
        }

        String connectionsJSONString = new Gson().toJson(objlist);
        Log.d(TAG, "onCreate: "+connectionsJSONString);
        editor.putString(KEY_CONNECTIONS, connectionsJSONString);
        editor.commit();


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonRetrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String connectionsJSONString = getPreferences(MODE_PRIVATE).getString(KEY_CONNECTIONS, null);

                Example[] example = new Gson().fromJson(connectionsJSONString, Example[].class);
                Log.d(TAG, "onClick: "+example);
                for(int j=0;j<10;j++) {
                    String output=example[j].getA();
                    Log.d(TAG, "onClick: " +output );
                }

            }
        });


    }
}

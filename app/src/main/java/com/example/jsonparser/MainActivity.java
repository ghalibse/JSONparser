package com.example.jsonparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";

    private String jsonString = "[{\"name\":\"Juan\",\"age\":20,\"grade\":8.1},{\"name\":\"Miguel\",\"age\":23,\"grade\":8.3},{\"name\":\"Roberto\",\"age\":39,\"grade\":9.3},{\"name\":\"Luis\",\"age\":19,\"grade\":6.9},{\"name\":\"Gaudencio\",\"age\":25,\"grade\":4.3}]";
    private String jsonString2 = "{\"students\":[{\"name\":\"Juan\",\"age\":20,\"grade\":8.1},{\"name\":\"Miguel\",\"age\":23,\"grade\":8.3},{\"name\":\"Roberto\",\"age\":39,\"grade\":9.3},{\"name\":\"Luis\",\"age\":19,\"grade\":6.9},{\"name\":\"Gaudencio\",\"age\":25,\"grade\":4.3}]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doMagic(View view) {


        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {

//                Log.d(TAG, "doMagic: " + i + " " +jsonArray.get(i));

                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());

                String name = jsonObject.getString("name");
                String age = jsonObject.getString("age");

                String grade = jsonObject.getString("grade");

                Log.d(TAG, "doMagic: " + i + " " + name + " " + grade + " " + age);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void doMagic2(View view) {

        try {
            JSONObject jsonObject = new JSONObject(jsonString2);

            JSONArray jsonArray = jsonObject.getJSONArray("students");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject js = new JSONObject(jsonArray.get(i).toString());

                String name = js.getString("name");
                Double grd = js.getDouble("grade");
                int age = js.getInt("age");

                Log.d(TAG, "doMagic: " + i + " " + name + " " + grd + " " + age);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void doMagic3(View view) {

        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<List<Student>>() {
        }.getType();

        ArrayList<Student> students = gson.fromJson(jsonString, listType);

        for (Student student : students) {
            Log.d(TAG, "doMagic3: "
                    + student.name + " "
                    + student.age + " "
                    + student.grade);
        }
    }

    public void doMagic4(View view) {

        Gson gson = new GsonBuilder().create();
        Result result = gson.fromJson(jsonString2, Result.class);

        for (Student student : result.students) {

            Log.d(TAG, "doMagic4: "
                    + student.name + " "
                    + student.age + " "
                    + student.grade);
        }
    }
}

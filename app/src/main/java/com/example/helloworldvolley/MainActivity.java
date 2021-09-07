package com.example.helloworldvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity<TAG> extends AppCompatActivity {

    TextView helo;
    String url="https://6131f725ab7b1e001799b275.mockapi.io/test/volley";
    String name;
    String TAG="users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helo=findViewById(R.id.hello);
        //https://www.tutorialspoint.com/how-to-use-simple-volley-request-in-android
        //https://www.section.io/engineering-education/making-api-requests-using-volley-android/
        StringRequest stringRequest=new StringRequest(Request.Method.GET,url,response -> {
            try {
                Log.e(TAG,"Response"+response);
                JSONArray jsonArray=new JSONArray(response);
                JSONObject jsonObject=jsonArray.getJSONObject(0);
                JSONArray jsonArray1=jsonObject.getJSONArray("users");
                JSONObject jsonObject1=jsonArray1.getJSONObject(0);
                name=jsonObject1.getString("name");
                helo.setText(name);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        },
                VolleyError-> Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show());
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}
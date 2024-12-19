package com.example.userapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewActivity extends AppCompatActivity {

    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);
        tv1=(TextView) findViewById(R.id.textid);
        callApi();
    }

    private void callApi() {
        String apiUrl="https://jsonplaceholder.typicode.com/users";
        JsonArrayRequest request=new JsonArrayRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        StringBuilder result=new StringBuilder();
                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject ob=response.getJSONObject(i);
                                String getName=ob.getString("name");
                                String getEmail=ob.getString("email");
                                String getPhone=ob.getString("phone");
                                String getWebsite=ob.getString("website");
                                result.append("Name: ").append(getName).append("\n");
                                result.append("Email: ").append(getEmail).append("\n");
                                result.append("Phone: ").append(getPhone).append("\n");
                                result.append("Website: ").append(getWebsite).append("\n");
                                result.append("-----------------------------------------------------------\n");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            tv1.setText(result.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Something Went Wrong!",Toast.LENGTH_LONG).show();
                    }
                }

        );
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}
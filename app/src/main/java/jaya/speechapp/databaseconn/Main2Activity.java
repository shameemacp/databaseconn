package jaya.speechapp.databaseconn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    Button btn;
    EditText ed1,ed2;
    TextView t1,t2;
String a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn=findViewById(R.id.button2);
        t1=findViewById(R.id.textView2);
        t2=findViewById(R.id.textView3);
        ed1=findViewById(R.id.editText5);
        ed2=findViewById(R.id.editText6);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://appealable-merchant.000webhostapp.com/login.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//If we are getting success from server

                                Toast.makeText(Main2Activity.this,response,Toast.LENGTH_LONG).show();

                                try {
                                    JSONArray jsonArray=new JSONArray(response);
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject json_obj = jsonArray.getJSONObject(i);
                                        a= json_obj.getString("Username");
                                        b=json_obj.getString("Email");
                                        t1.setText(a);
                                        t2.setText(b);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                            }

                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
//Adding parameters to request


                        params.put("username",ed1.getText().toString());
                       params.put("password",ed2.getText().toString());
//returning parameter
                        return params;
                    }
                };

//Adding the string request to the queue
                RequestQueue requestQueue = Volley.newRequestQueue(Main2Activity.this);
                requestQueue.add(stringRequest);
            }
        });
    }
}

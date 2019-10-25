package jaya.speechapp.databaseconn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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



import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
Button btn;
EditText t1,t2,t3,t4;
TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        t=findViewById(R.id.textView);
        t1=findViewById(R.id.editText);
        t2=findViewById(R.id.editText2);
        t3=findViewById(R.id.editText3);
        t4=findViewById(R.id.editText4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if(!(t1.getText().toString().isEmpty()||t2.getText().toString().isEmpty()||t3.getText().toString().isEmpty()||t4.getText().toString().isEmpty())&&t3.getText().toString().equals(t4.getText().toString()))
{
    StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://appealable-merchant.000webhostapp.com/reg.php",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
//If we are getting success from server

Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
if(response.equals("success"))
{
    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
    startActivity(intent);
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

params.put("name",t1.getText().toString());
params.put("email",t2.getText().toString());
params.put("password",t3.getText().toString());
//returning parameter
            return params;
        }
    };

//Adding the string request to the queue
    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
    requestQueue.add(stringRequest);
}
else
{
    Toast.makeText(MainActivity.this,"not matched ",Toast.LENGTH_LONG).show();

}

            }
        });
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

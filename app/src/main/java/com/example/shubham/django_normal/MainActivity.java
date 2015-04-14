package com.example.shubham.django_normal;

import android.app.Activity;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shubham.django_normal.ServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity{

    TextView tv_name,tv_address, tv_email, tv_phone;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_name = (TextView) findViewById(R.id.Name);
        tv_address = (TextView) findViewById(R.id.Address);
        tv_email = (TextView) findViewById(R.id.Email);
        tv_phone = (TextView) findViewById(R.id.Phone);
        iv = (ImageView) findViewById(R.id.Photo);
        ServiceHandler serviceHandler = new ServiceHandler();
        AsyncTask<String, Void, String> asyncTask = (serviceHandler.execute("http://10.0.2.2:8000/basics/entries/"));
        try {
            String response = asyncTask.get();
            Log.d("response_main",response);
            JSONArray jsonArray = new JSONArray(response);
            Log.d("response",jsonArray.toString());
            for (int i=0; i < jsonArray.length() ; i++) {
                tv_name.setText("Name : " + jsonArray.getJSONObject(i).getJSONObject("fields").getString("name"));
                tv_address.setText("Address : " + jsonArray.getJSONObject(i).getJSONObject("fields").getString("address"));
                tv_phone.setText("Phone : " + jsonArray.getJSONObject(i).getJSONObject("fields").getString("phone"));
                tv_email.setText("Email : " + jsonArray.getJSONObject(i).getJSONObject("fields").getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

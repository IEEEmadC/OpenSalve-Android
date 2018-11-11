package com.agzuniverse.agz.opensalve;

import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.agzuniverse.agz.opensalve.Modals.GetHelpData;
import com.agzuniverse.agz.opensalve.ViewModels.GetHelpViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

public class GetHelp extends AppCompatActivity {
    private EditText desc;
    private EditText name;
    private EditText contact;
    private int id;
    private GetHelpData data;
    private GetHelpViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id", 0);
        if (id == 0) {
            setContentView(R.layout.get_help);
            desc = findViewById(R.id.desc);
            name = findViewById(R.id.name);
            contact = findViewById(R.id.contact);
        } else {
            setContentView(R.layout.get_help_view);
            model = ViewModelProviders.of(this).get(GetHelpViewModel.class);
            fetchGetHelpDataAsync(id);
        }
    }

    private void fetchGetHelpDataAsync(int id) {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                setGetHelpData();
            }
        };
        Runnable runnable = () -> {
            data = model.getHelpData(id, getResources().getString(R.string.base_api_url));
            handler.sendEmptyMessage(0);
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void setGetHelpData() {
        TextView t = findViewById(R.id.name_view);
        t.setText(data.getName());
        t = findViewById(R.id.contact_view);
        t.setText(data.getContact());
        t = findViewById(R.id.desc_view);
        t.setText(data.getDesc());
        t = findViewById(R.id.req_status_view);
        t.setText(data.getStatus());
    }

    public void submitRequest(View v) {
        final String n = name.getText().toString();
        final String c = contact.getText().toString();
        final String nDesc = desc.getText().toString();

        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        public void onSuccess(Location location) {
                            if (location != null) {
                                JSONObject locObj = new JSONObject();
                                JSONObject userObj = new JSONObject();
                                JSONObject resObj = new JSONObject();
                                JSONObject obj = new JSONObject();
                                try {
                                    locObj.put("latitude", location.getLatitude());
                                    locObj.put("longitude", location.getLongitude());
                                    userObj.put("name", n);
                                    userObj.put("phone", c);
                                    resObj.put("evac", String.valueOf(((CheckBox) findViewById(R.id.evac)).isChecked()));
                                    resObj.put("foodwater", String.valueOf(((CheckBox) findViewById(R.id.foodwater)).isChecked()));
                                    resObj.put("medical", String.valueOf(((CheckBox) findViewById(R.id.medical)).isChecked()));
                                    resObj.put("firstaid", String.valueOf(((CheckBox) findViewById(R.id.firstaid)).isChecked()));
                                    resObj.put("transport", String.valueOf(((CheckBox) findViewById(R.id.transport)).isChecked()));

                                    resObj.put("desc", nDesc);
                                    obj.put("user", userObj);
                                    obj.put("location", locObj);
                                    obj.put("resources", resObj);
                                    //TODO make a POST request to the backend
//                                      NetworkPost net = new NetworkPost();
//                                      net.execute("https://postman-echo.com/post", String.valueOf(obj));
                                    showToast();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
        }

        super.finish();
    }

    public void showToast() {
        Toast.makeText(this, "Request submitted successfully", Toast.LENGTH_LONG).show();
    }
}

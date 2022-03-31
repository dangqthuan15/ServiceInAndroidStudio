package com.example.databinding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.databinding.service.BoundService;
import com.example.databinding.service.StartService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnstart,btnstop;
    public static final String ONE = "onetwothree";
    private boolean boundService = false;
    private BoundService idboundService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnstart =(Button) findViewById(R.id.btn_start_service);
        btnstop =(Button) findViewById(R.id.btn_stop);

        btnstart.setOnClickListener(this);
        btnstop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent1 = new Intent(this, StartService.class);
        intent1.putExtra(ONE,"onetwothree.com");
        switch (view.getId()){
            case R.id.btn_start_service:
//                startService(intent1);
                bindService(intent1,serviceConnection,BIND_AUTO_CREATE);
                break;

            case R.id.btn_stop:
//                stopService(intent1);
                if(boundService) {
                    unbindService(serviceConnection);
                    boundService = false;
                }break;


        }
    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.BoundExample bider = (BoundService.BoundExample) iBinder;
            idboundService = bider.getService();
            Toast.makeText(MainActivity.this,"ServiceConnect"+idboundService.getCurrentTime(),Toast.LENGTH_SHORT).show();
            boundService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Toast.makeText(MainActivity.this,"ServiceDisconnect",Toast.LENGTH_SHORT).show();
            boundService = false;
        }
    };

}
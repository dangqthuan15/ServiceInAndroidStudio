package com.example.databinding.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BoundService extends Service {
    private  IBinder iBinder = new BoundExample();

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this,"onBind",Toast.LENGTH_SHORT).show();
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this,"unBind",Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    public class BoundExample extends Binder{
        public BoundService getService(){
            return BoundService.this;
        }
    }
    public String getCurrentTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yy", Locale.US);
        return (dateFormat.format(new Date()));
    }
}

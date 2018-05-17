package com.example.marwa.bound;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MyService extends Service {
    private  final  IBinder myBinder=new MyLocalBinder();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return myBinder;
    }
    public  String getCurrentTime(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss MM/dd/yyyy",Locale.US);
        return  (dateFormat.format(new Date()));
    }

    public  class  MyLocalBinder extends Binder{
        MyService getService(){
            return  MyService.this;
        }
    }
}

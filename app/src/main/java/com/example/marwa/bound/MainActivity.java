package com.example.marwa.bound;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    MyService myService;
 boolean isBound=false;
 Button btn;

 private ServiceConnection myconnection=new ServiceConnection() {
     @Override
     public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
         MyService.MyLocalBinder binder=(MyService.MyLocalBinder) iBinder;
         myService=binder.getService();
         isBound=true;
     }

     @Override
     public void onServiceDisconnected(ComponentName componentName) {
    isBound=false;
     }
 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this,  MyService.class);
        bindService(intent,myconnection, getApplicationContext().BIND_AUTO_CREATE);
        btn=findViewById(R.id.timer);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTime(view);
            }
        });
    }
    public  void showTime(View view){
        String currentTime=myService.getCurrentTime();
        TextView myTxtt=(TextView)findViewById(R.id.mytxt);
        myTxtt.setText(currentTime);
    }
}

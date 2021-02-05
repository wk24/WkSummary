package com.wuk.wkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wuk.wk.IMyAidlInte;
import com.wuk.wk.R;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private EditText et_send_input,nub_1,nub_2;
    private TextView tv_send;
    private TextView tv_bind;
    private IMyAidlInte iImoocAIDLService;

    private ServiceConnection conn = new ServiceConnection() {

        //绑定服务，回调onBind()方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iImoocAIDLService = IMyAidlInte.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iImoocAIDLService = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService();

        et_send_input = findViewById(R.id.et_send_input);
        nub_1 = findViewById(R.id.nub_1);
        nub_2 = findViewById(R.id.nub_2);
        tv_send = findViewById(R.id.tv_send);
        tv_bind = findViewById(R.id.tv_bind);

        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int nub1 = Integer.parseInt(nub_1.getText().toString().trim());
                    int nub2 = Integer.parseInt(nub_2.getText().toString().trim());

                    int result = iImoocAIDLService.add(nub1,nub2);
                    et_send_input.setText(result+"");
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        tv_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService();
                Log.e(TAG,"bindService"+iImoocAIDLService);
            }
        });
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.wuk.wk.IRemoteService");
//        //新版本（5.0后）必须显式intent启动 绑定服务
        intent.setComponent(new ComponentName("com.wuk.wk","com.wuk.wk.IRemoteService"));
        //绑定的时候服务端自动创建
        boolean b = bindService(intent, conn, Context.BIND_AUTO_CREATE);
        Log.e(TAG, "bindService: 11111111111111111111"+b );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
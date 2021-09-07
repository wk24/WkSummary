package com.wuk.wk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * @author wuk
 * @date 2021/2/5
 */
public class IRemoteService extends Service {
    public static final String TAG = "IRemoteService";
    //客户端绑定service时会执行
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder = new com.wuk.wk.IMyAidlInte.Stub() {
        @Override
        public int add(int num1, int num2) throws RemoteException {
            Log.e(TAG,"收到了来自客户端的请求" + num1 + "+" + num2 );
            return num1 + num2;
        }

    };
}
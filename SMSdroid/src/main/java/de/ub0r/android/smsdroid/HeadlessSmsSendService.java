package de.ub0r.android.smsdroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
public class HeadlessSmsSendService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

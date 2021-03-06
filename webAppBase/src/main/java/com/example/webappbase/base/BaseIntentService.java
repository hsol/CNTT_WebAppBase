/*
* Copyright 2016 HansolLim
* Released under the MIT license
* http://hsol.github.io/
*/

package com.example.webappbase.base;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.fingerpush.android.FingerNotification;
import com.fingerpush.android.FingerPushListener;
import com.fingerpush.android.FingerPushManager;

import java.util.Iterator;

import com.example.webappbase.R;
import com.example.webappbase.common.MainActivity;
import com.example.webappbase.lib.Utils;

/**
 * Created by hansollim on 2016-07-28.
 */
public class BaseIntentService extends FingerPushListener {
    private static final String TAG = "BaseIntentService";
    FingerPushManager manager;

    @Override
    public void onMessage(Context context, Bundle data) {
        Iterator<String> iterator = data.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = data.get(key).toString();
            Utils.Logger(TAG, context, "D", "onMessage ::: key:" + key + ", value:" + value);
        }

        try {
            manager = FingerPushManager.getInstance(BaseIntentService.this);

            setNotification(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setNotification(final Bundle data) {
        Intent intent = new Intent(BaseIntentService.this, MainActivity.class);
        intent.putExtra(getString(R.string.boot_by_push_message), true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pi = PendingIntent.getActivity(BaseIntentService.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        FingerNotification noti = new FingerNotification(BaseIntentService.this);
        noti.setColor(Color.TRANSPARENT);
        noti.setNofiticaionIdentifier((int) System.currentTimeMillis());
        noti.showNotification(data, pi);
    }
}
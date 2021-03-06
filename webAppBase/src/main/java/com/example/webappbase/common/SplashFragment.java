/*
* Copyright 2016 HansolLim
* Released under the MIT license
* http://hsol.github.io/
*/

package com.example.webappbase.common;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.webappbase.R;
import com.example.webappbase.base.BaseFragment;
import com.example.webappbase.base.BaseURLConnection;
import com.example.webappbase.lib.Utils;

/**
 * Created by hansollim on 2016-07-26.
 */
public class SplashFragment extends BaseFragment {
    @Override
    protected int getBaseFragment() { return R.layout.fragment_splash; }

    @Override
    protected void onInit() {
        BaseAsyncTask baseAsyncTask = new BaseAsyncTask();
        baseAsyncTask.execute(5,4);
    }

    @Override
    protected void onClick(int viewId) {

    }

    public class BaseAsyncTask extends AsyncTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object... params) {
            int first = (int) params[0];
            int second = (int) params[1];

            BaseURLConnection connection = new BaseURLConnection();
            Utils.HttpObject httpObject = new Utils.HttpObject();
            httpObject.setMethod("GET");
            httpObject.setUrlString("http://hsol.dothome.co.kr/webappbase/?first=" + first + "&second=" + second);
            String test = connection.getResponseFromHttpUrlConnection(getContext(), httpObject);
            try {
                JSONObject jsonObject = new JSONObject(test);
                String test2 = jsonObject.get("data_result").toString();
                if(test2.equals("20"))
                {
                    replaceFragment("WebViewFragment", new WebViewFragment(), false);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return test;
        }

    }
}

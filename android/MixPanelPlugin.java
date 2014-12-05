package com.tealeaf.plugin.plugins;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.tealeaf.logger;
import com.tealeaf.TeaLeaf;
import com.tealeaf.plugin.IPlugin;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.tealeaf.util.HTTP;
import java.net.URI;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import com.mixpanel.android.mpmetrics.MixpanelAPI;


public class MixPanelPlugin implements IPlugin {
    Activity activity;
    Context context;
    MixpanelAPI mixpanel;

    public MixPanelPlugin() { }

    public void onCreateApplication(Context applicationContext) {
        this.context = applicationContext;
    }

    public void onCreate(Activity activity, Bundle savedInstanceState) {
        this.activity = activity;
    }

    public void onResume() { }

    public void onStart() {
        PackageManager manager = activity.getPackageManager();
        String token = "";
        try {
            Bundle meta = manager.getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA).metaData;
            if (meta != null) {
                token = meta.getString("MIXPANEL_TOKEN");
            }
        } catch (Exception e) {
            android.util.Log.d("EXCEPTION", "" + e.getMessage());
        }

    this.mixpanel = MixpanelAPI.getInstance(context, token);
    }

    public void setIdentity(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            String value = obj.getString("value");

      mixpanel.identify(value);
        } catch (JSONException e) {
            android.util.Log.d("EXCEPTION", "" + e.getMessage());
        }
    }

    public void setGlobalProperty(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            String key = obj.getString("key");
            String value = obj.getString("value");

            JSONObject props = new JSONObject();
            props.put(key, value);

            mixpanel.registerSuperProperties(props);
        } catch (JSONException e) {
            android.util.Log.d("EXCEPTION", "" + e.getMessage());
        }
    }

    public void track(String json) {
        String eventName = "noName";
        try {
            JSONObject props = new JSONObject();

            JSONObject obj = new JSONObject(json);
            eventName = obj.getString("eventName");
            JSONObject paramsObj = obj.getJSONObject("params");
            Iterator<String> iter = paramsObj.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                String value = null;
                try {
                    value = paramsObj.getString(key);
                } catch (JSONException e) {
                    logger.log("{mixpanel} track - failure: " + eventName + " - " + e.getMessage());
                }

                if (value != null) {
                  props.put(key, value);
                }
            }

            mixpanel.track(eventName, props);
            logger.log("{mixpanel} track - success: " + eventName);
        } catch (JSONException e) {
            logger.log("{mixpanel} track - failure: " + eventName + " - " + e.getMessage());
        }
    }

    public void onPause() {

    }

    public void onStop() {
    }

    public void onDestroy() {
      mixpanel.flush();
    }

    public void onNewIntent(Intent intent) {

    }

    public void setInstallReferrer(String referrer) {

    }

    public void onActivityResult(Integer request, Integer result, Intent data) {

    }

    public boolean consumeOnBackPressed() {
        return true;
    }

    public void onBackPressed() {
    }
}


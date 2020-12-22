package com.dawntechs.gebeyaEcommerceApp;

import android.app.Application;
import android.content.Context;

import com.dawntechs.gebeyaEcommerceApp.restApi.Api;
import com.dawntechs.gebeyaEcommerceApp.restApi.ApiFactory;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class MainApp extends Application {

    private Api restApi;
    private Scheduler scheduler;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    private static MainApp get(Context context) {
        return (MainApp) context.getApplicationContext();
    }

    public static MainApp create(Context context) {
        return MainApp.get(context);
    }

    public Api getRestApi() {
        if(restApi == null) {
            restApi = ApiFactory.create();
        }
        return restApi;
    }

    public void setRestApi(Api restApi) {
        this.restApi = restApi;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}

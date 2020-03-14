package com.edicionsoft.mvp.Model;

import com.edicionsoft.mvp.Enums.Enums;
import com.edicionsoft.mvp.Helpers.URLS;
import com.edicionsoft.mvp.Presenter.Presenter;
import com.edicionsoft.mvp.View.View;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONException;
import org.json.JSONObject;


import cz.msebera.android.httpclient.Header;

public class Model implements Presenter {
    View mView;
    private static AsyncHttpClient client = new AsyncHttpClient();
    private String key ;


    public Model(View loginview){
        this.mView =loginview;

    }

    @Override
    public void performLogin(String username, String password) {
        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("password", password);
        client.addHeader("Content-Type","application/x-www-form-urlencoded");
        client.post(URLS.urlNgrok + Enums.login, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject array = new JSONObject(new String(responseBody));
                    key = array.getString("token");
                    mView.loginSucces(key);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                mView.loginError();
            }
        });

    }


}

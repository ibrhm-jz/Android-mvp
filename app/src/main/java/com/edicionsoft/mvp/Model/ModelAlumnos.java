package com.edicionsoft.mvp.Model;

import com.edicionsoft.mvp.Helpers.URLS;
import com.edicionsoft.mvp.Presenter.PresenterAlumnos;
import com.edicionsoft.mvp.View.ViewAlumnos;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;
public class ModelAlumnos implements PresenterAlumnos {
    ViewAlumnos mView;
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static String url = URLS.urlNgrok+"api/v1/alumno/alumno_lista/";
    private ArrayList nombres = new ArrayList();
    private ArrayList ids = new ArrayList();
    private ArrayList apellidop = new ArrayList();
    private ArrayList apellidom = new ArrayList();
    private ArrayList edad = new ArrayList();
    private ArrayList sexo = new ArrayList();
    private ArrayList direccion = new ArrayList();
    private ArrayList carrera = new ArrayList();
    private static String urldelete = URLS.urlNgrok+"api/v1/alumno/alumno_detail/";

    public ModelAlumnos(ViewAlumnos AlumnosView){
        this.mView =AlumnosView;

    }


    @Override
    public void getAlumnos(String key) {
        client.addHeader("Content-Type","application/x-www-form-urlencoded");
        client.addHeader("Authorization","Token "+key);
        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    nombres.clear();
                    ids.clear();
                    apellidom.clear();
                    apellidop.clear();
                    edad.clear();
                    sexo.clear();
                    direccion.clear();
                    JSONArray arr=new JSONArray(new String(responseBody));
                    for (int x =0;x<arr.length();x++){
                        nombres.add(arr.getJSONObject(x).get("nombre"));
                        ids.add(arr.getJSONObject(x).get("id"));
                        apellidop.add(arr.getJSONObject(x).get("apellidoPaterno"));
                        apellidom.add(arr.getJSONObject(x).get("apellidoMaterno"));
                        edad.add(arr.getJSONObject(x).get("edad"));
                        sexo.add(arr.getJSONObject(x).get("sexo"));
                        direccion.add(arr.getJSONObject(x).get("direccion"));
                        carrera.add(arr.getJSONObject(x).get("carrera"));

                    }
                    mView.showAlumnos(nombres,ids,apellidop,apellidom,edad,sexo,direccion,carrera);


                }  catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {


            }

        });

    }

    @Override
    public void deleteAlumnos(String id,String key) {
        client.addHeader("Content-Type","application/x-www-form-urlencoded");
        client.addHeader("Authorization","Token "+key);
        client.delete(urldelete+id+"/", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {


            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

    }


}

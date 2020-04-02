package com.edicionsoft.mvp.Presenter;

import com.loopj.android.http.RequestParams;

public interface PresenterAlumnos {
    void getAlumnos(String key);
    void deleteAlumnos(String id,String key);
    void addAlumnos( String key, RequestParams params);
    void updateAlumnos( String id,String key,RequestParams params );
}

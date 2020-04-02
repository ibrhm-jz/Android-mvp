package com.edicionsoft.mvp.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.edicionsoft.mvp.Model.ModelAlumnos;
import com.edicionsoft.mvp.Presenter.PresenterAlumnos;
import com.edicionsoft.mvp.R;
import com.edicionsoft.mvp.View.ViewAlumnos;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;


public class AgregarAlumnos extends AppCompatActivity implements ViewAlumnos {
    private EditText nombre;
    private EditText ap;
    private EditText am;
    private EditText edad;
    private EditText sexo;
    private EditText carrera;
    private EditText direccion;
    private Button Agregar;
    private  String key ="";
    private PresenterAlumnos mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_agregar_alumnos);
        mPresenter = new ModelAlumnos(AgregarAlumnos.this);
        key= getIntent().getExtras().getString("key");
        nombre = findViewById(R.id.agAl_nombre);
        ap=findViewById(R.id.agAl_apellidop);
        am=findViewById(R.id.agAl_apellidom);
        edad=findViewById(R.id.agAl_edad);
        sexo=findViewById(R.id.agAl_sexo);
        carrera=findViewById(R.id.agAl_carrera);
        direccion=findViewById(R.id.agAl_direccion);
        Agregar=findViewById(R.id.btn_agregar);

        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestParams params = new RequestParams();
                params.put("nombre", nombre.getText());
                params.put("apellidoPaterno", ap.getText());
                params.put("apellidoMaterno", am.getText());
                params.put("edad", edad.getText());
                params.put("carrera", carrera.getText());
                params.put("direccion", direccion.getText());
                params.put("sexo", sexo.getText());
                mPresenter.addAlumnos( key,params);


            }
        });



    }

    @Override
    public void showAlumnos(ArrayList nombre, ArrayList ids, ArrayList ap, ArrayList am, ArrayList edad, ArrayList sexo, ArrayList direccion, ArrayList carrera) {

    }
}

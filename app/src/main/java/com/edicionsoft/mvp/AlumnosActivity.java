package com.edicionsoft.mvp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.edicionsoft.mvp.Model.ModelAlumnos;
import com.edicionsoft.mvp.Presenter.PresenterAlumnos;
import com.edicionsoft.mvp.View.ViewAlumnos;
import java.util.ArrayList;

public class AlumnosActivity extends AppCompatActivity implements ViewAlumnos {
    private String key ="";
    private ArrayAdapter adapter;
    private ListView lvone;
    private ListView lvtwo;
    private PresenterAlumnos mPresenter;
    private TextView title1;
    private  TextView title2;
    private EditText nombreEt;
    private EditText apellidopEt;
    private EditText apellidomEt;
    private EditText edadEt;
    private EditText sexoEt;
    private EditText direccionEt;
    private EditText carreraEt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);
        getSupportActionBar().hide();
        mPresenter = new ModelAlumnos(AlumnosActivity.this);
        lvone=findViewById(R.id.lst_userone);
        lvtwo=findViewById(R.id.lst_usertwo);
        title1 =findViewById(R.id.txt_user1);
        title2 = findViewById(R.id.txt_user2);
        key= getIntent().getExtras().getString("key");
        mPresenter.getAlumnos(key);

    }

    @Override
    public void showAlumnos(final ArrayList nombre, final ArrayList ids, final ArrayList ap, final ArrayList am, final ArrayList edad, final ArrayList sexo, final ArrayList direccion, final ArrayList carrera) {
        if(key.equals("c2ad7aa18de9204deced845d3cc56dae2cc74520")){
            title1.setText("Usuario 1");
            adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,nombre);
            lvone.setAdapter(adapter);
            lvone.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    final int  pos =position;

                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(AlumnosActivity.this);
                    dialogo1.setTitle("Importante");
                    dialogo1.setMessage("¿ Seguro que desea borrar el registro ?");
                    dialogo1.setCancelable(false);
                    dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                           mPresenter.deleteAlumnos(ids.get(pos).toString(),key);
                           mPresenter.getAlumnos(key);
                        }
                    });
                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            dialogo1.dismiss();
                        }
                    });
                    dialogo1.show();
                    return true;

                }
            });

            lvone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    viewAlumno(nombre.get(position).toString(),ap.get(position).toString(),am.get(position).toString(),edad.get(position).toString(),
                            sexo.get(position).toString(), carrera.get(position).toString(),direccion.get(position).toString());

                }
            });


        }
        else{
            title2.setText("Usuario 2");

            adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,ap);
            lvtwo.setAdapter(adapter);
            lvtwo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(),"No tienes aceeso",Toast.LENGTH_LONG).show();
                    return false;
                }
            });
            lvtwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    viewAlumno(nombre.get(position).toString(),ap.get(position).toString(),am.get(position).toString(),edad.get(position).toString(),
                            "","","");

                }
            });

        }
    }

    private void viewAlumno(String nombre, String apellidop,String apellidom,String edad,String sexo,String carrera,String direccion){
        final AlertDialog.Builder mibuilder= new AlertDialog.Builder(AlumnosActivity.this);
        final View viewClientes = getLayoutInflater().inflate(R.layout.alumno_detail,null);
        nombreEt= viewClientes.findViewById(R.id.dAl_nombre);
        apellidopEt=viewClientes.findViewById(R.id.dAl_apellidop);
        apellidomEt=viewClientes.findViewById(R.id.dAl_apellidom);
        edadEt=viewClientes.findViewById(R.id.dAl_edad);
        sexoEt=viewClientes.findViewById(R.id.dAl_sexo);
        carreraEt=viewClientes.findViewById(R.id.dAl_carrera);
        direccionEt=viewClientes.findViewById(R.id.dAl_direccion);
        nombreEt.setText(nombre);
        apellidopEt.setText(apellidop);
        apellidomEt.setText(apellidom);
        edadEt.setText(edad);
        sexoEt.setText(sexo);
        carreraEt.setText(carrera);
        direccionEt.setText(direccion);
        mibuilder.setView(viewClientes);
        final AlertDialog miAlert = mibuilder.create();
        miAlert.show();
    }


}

package com.example.estudiante.integracionredesandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText nombre;
    EditText contraseña;
    Conexion c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = (EditText) findViewById(R.id.nombre);
        contraseña = (EditText) findViewById(R.id.contraseña);
        c= Conexion.getInstance();
        System.out.println("borren esto");

    }

    public void onEnviar(View v) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                String mensaje= "hola";
                c.enviar(mensaje);
                System.out.println("----------enviado");
            }
        });
        t.start();


    }


}

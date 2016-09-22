package com.example.estudiante.integracionredesandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText nombre;
    EditText contraseña;
    Conexion c = Conexion.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = (EditText) findViewById(R.id.nombre);
        contraseña = (EditText) findViewById(R.id.contraseña);

        System.out.println("borren esto");

    }

    public void onEnviar(View v) {
        nombre = (EditText) findViewById(R.id.nombre);
        contraseña = (EditText) findViewById(R.id.contraseña);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                // User user = new User(nombre.getText().toString(),contraseña.getText().toString());
                if (nombre.length() > 0 && contraseña.length() > 0) {
                    Usuario user = new Usuario(nombre.getText().toString(), contraseña.getText().toString());
                    byte[] serialized= c.serializar(user);
                    c.sendMessage(serialized);

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(getApplicationContext(), "Debes introducir un Nombre y una contraseña", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });

                }



            }
        });
        t.start();


    }


}

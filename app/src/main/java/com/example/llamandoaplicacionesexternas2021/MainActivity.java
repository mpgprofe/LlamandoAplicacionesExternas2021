package com.example.llamandoaplicacionesexternas2021;

import static android.view.Gravity.CENTER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button buttonLanzar, buttonMapa, buttonWhatsApp, buttonEnviarEmail;
    EditText editTextWeb, editTextAsunto, editTextCuerpo, editTextEmail;
    String coordenadasPeñarroya = "38.2937926,-5.2583539";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLanzar = findViewById(R.id.buttonLanzarWeb);
        editTextWeb = findViewById(R.id.editTextWeb);
        buttonMapa = findViewById(R.id.buttonMapa);
        buttonWhatsApp = findViewById(R.id.buttonWhatsApp);
        buttonEnviarEmail = findViewById(R.id.buttonEnviarMail);
        editTextAsunto = findViewById(R.id.editTextAsunto);
        editTextCuerpo = findViewById(R.id.editTextCuerpo);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);

        buttonLanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(editTextWeb.getText().toString()));
                Intent seleccionador = Intent.createChooser(intent, "Elige tu navegador favorito");
                try {
                    startActivity(seleccionador);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, getResources().getText(R.string.mensajeError), Toast.LENGTH_LONG).show();
                }

            }
        });

        buttonMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:" + coordenadasPeñarroya));
                startActivity(intent);
            }
        });

        buttonWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Hola qué tal?");
                intent.setPackage("com.whatsapp");
                startActivity(intent);

                if (getPackageManager().getLaunchIntentForPackage("com.whatsapp") != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Instala whastsapp para que rule", Toast.LENGTH_LONG).show();
                }


            }
        });

        buttonEnviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentoEnviarEmail = new Intent(Intent.ACTION_SEND);
                intentoEnviarEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{editTextEmail.getText().toString()});
                intentoEnviarEmail.putExtra(Intent.EXTRA_SUBJECT, editTextAsunto.getText().toString());
                intentoEnviarEmail.putExtra(Intent.EXTRA_TEXT, editTextCuerpo.getText().toString());
                intentoEnviarEmail.setType("message/rfc822");
                try {
                    startActivity(Intent.createChooser(intentoEnviarEmail, "Escoge tu programa favorito de envío de emails"));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Intala una app de envío de email", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private long diasParaMiCumple(){
        Calendar hoy = Calendar.getInstance();
        int año = 2022;
        int mes = 6; //0 enero -- 11 diciembre
        int dia = 28;
        Calendar miCumple = Calendar.getInstance();
        miCumple.set(año, mes, dia);

        long diferenciaEnMilisegundos = miCumple.getTimeInMillis() - hoy.getTimeInMillis();

        long diferenciaEnDías = diferenciaEnMilisegundos/1000/60/60/24;

        return diferenciaEnDías;


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(MainActivity.this, "Días para mi cumple: "+diasParaMiCumple(), Toast.LENGTH_SHORT).show();
    }
}
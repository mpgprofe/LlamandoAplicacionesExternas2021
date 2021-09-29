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

public class MainActivity extends AppCompatActivity {

    Button buttonLanzar,buttonMapa, buttonWhatsApp;
    EditText editTextWeb;
    String coordenadasPeñarroya = "38.2937926,-5.2583539";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLanzar = findViewById(R.id.buttonLanzarWeb);
        editTextWeb = findViewById(R.id.editTextWeb);
        buttonMapa = findViewById(R.id.buttonMapa);
        buttonWhatsApp = findViewById(R.id.buttonWhatsApp);

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
                intent.setData(Uri.parse("geo:"+coordenadasPeñarroya));
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

                if (getPackageManager().getLaunchIntentForPackage("com.whatsapp")!=null) {
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Instala whastsapp para que rule", Toast.LENGTH_LONG).show();
                }


            }
        });


    }
}
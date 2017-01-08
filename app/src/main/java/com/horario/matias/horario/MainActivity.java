package com.horario.matias.horario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvGrup, tvDia;

    /**
     * Método onCreate que recupera las SharedPreferences en caso de tenerlas,
     * sinó se ejecuta la aplicación con normalidad, es decir, tendremos que
     * seleccionar el radio button normal y pulsar el botón.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);
        tvGrup = (TextView) findViewById(R.id.tvGrup);
        tvDia = (TextView) findViewById(R.id.tvDia);
        /*
        switch (intentClaseRb) {
            case ("A1"): {
                tvGrup.setText("A1");
                break;
            }
            case ("A2"): {
                tvGrup.setText("A2");
                break;
            }
        }*/

    }


    /**
     * Para abrir el menú de arriba a la derecha de "configuración".
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuConfiguracion) {
            startActivity(new Intent(this, Configuracio.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

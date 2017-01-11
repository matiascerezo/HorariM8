package com.horario.matias.horario;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvGrup, tvDia;
    private SQLiteDatabase db;

    /**
     * Métode onCreate que obté el dia actual i el posa al TextView, també mostrarà el grup una vegada
     * l'usuari l'ha introduït. A més comprova si el "Tema fosc" ha estat seleccionat i en cas afirmatiu
     * canvia el fons fosc i la lletra en blanc. També al iniciar la app mostra un Toast dient-li al
     * usuari que introduiexi les preferencies la primera vegada que l'obre i quan ja les té introduides
     * mostra un toast donant la benvinguda i "nom usuari".
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);

        BDActivity bdActivity = new BDActivity(this, "BDHorari", null, 1);
        db = bdActivity.getWritableDatabase();

        //Toast.makeText(this, bdActivity.getProfessor(1),Toast.LENGTH_LONG).show();

        tvGrup = (TextView) findViewById(R.id.tvGrup);
        tvDia = (TextView) findViewById(R.id.tvDia);

        tvDia.setText(getDiaSetmana());
        if (SPConfActivity.getString(this, SPConfActivity.getNombre()).isEmpty() &&
                SPConfActivity.getString(this, SPConfActivity.getGrup()).isEmpty() &&
                !SPConfActivity.getBoolean(this, SPConfActivity.getTemaFosc(), false)) {
           Toast.makeText(this, "Has d'introduir les teves preferencies", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Benvingut " + SPConfActivity.getString(this, SPConfActivity.getNombre()), Toast.LENGTH_LONG).show();
                tvGrup.setText(SPConfActivity.getString(this, SPConfActivity.getGrup()));

            if (SPConfActivity.getBoolean(this, SPConfActivity.getTemaFosc(), false)) {
                tvGrup.setTextColor(Color.WHITE);
                tvDia.setTextColor(Color.WHITE);
                findViewById(R.id.activity_horario).setBackgroundColor(Color.rgb(169, 169, 169));
            }
        }
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

    /**
     * Per obtenir el dia de la semana actual.
     *
     * @return
     */
    public String getDiaSetmana() {
        Calendar cal = Calendar.getInstance();
        String[] diesSetmana = new String[]{"Diumenge", "Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres", "Dissabte"};
        int numeroDia = cal.get(Calendar.DAY_OF_WEEK);
        String dia = diesSetmana[numeroDia - 1];
        return dia;
    }
}
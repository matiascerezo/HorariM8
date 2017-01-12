package com.horario.matias.horario;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvGrup, tvDia, tvAssignatura, tvProfessor, tvHoraInici, tvClasse, tvHoraFi, tvSeparacio;
    //Horari horari = new Horari();
    SQLiteDatabase db;

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

        //horari.consultaValors(); --> Mi idea es: creo BD i hago consulta añadiendolos en un array de String
        //los valores que salen en la consulta
        //horari.setTV(); --> Setteo los TextViews con los valores anteriores.
        Toast.makeText(this, new Horari().getDiaSetmanaSistema(), Toast.LENGTH_LONG).show();

        tvGrup = (TextView) findViewById(R.id.tvGrup);
        tvDia = (TextView) findViewById(R.id.tvDia);
        tvAssignatura = (TextView) findViewById(R.id.tvAssignatura);
        tvProfessor = (TextView) findViewById(R.id.tvProfessor);
        tvHoraFi = (TextView) findViewById(R.id.tvHoraFi);
        tvHoraInici = (TextView) findViewById(R.id.tvHoraInici);
        tvClasse = (TextView) findViewById(R.id.tvClase);
        tvSeparacio = (TextView) findViewById(R.id.tvSeparacio);

        tvDia.setText(new Horari().getDiaSetmanaSistema());
        if (SPConfActivity.getString(this, SPConfActivity.getNombre()).isEmpty() &&
                SPConfActivity.getString(this, SPConfActivity.getGrup()).isEmpty() &&
                !SPConfActivity.getBoolean(this, SPConfActivity.getTemaFosc(), false)) {
            Toast.makeText(this, getString(R.string.introdPrefs), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Benvingut " + SPConfActivity.getString(this, SPConfActivity.getNombre()), Toast.LENGTH_LONG).show();
            tvGrup.setText(SPConfActivity.getString(this, SPConfActivity.getGrup()));
            retornarGrup(tvGrup.getText().toString());

            if (SPConfActivity.getBoolean(this, SPConfActivity.getTemaFosc(), false)) {
                tvGrup.setTextColor(Color.WHITE);
                tvDia.setTextColor(Color.WHITE);
                findViewById(R.id.activity_horario).setBackgroundColor(Color.rgb(169, 169, 169));
            }
        }
    }

    /**
     * Per obrir el menú de dalt a la dreta de "configuració".
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Para iniciar la Activity de configuració al fer click.
     *
     * @param item
     * @return
     */
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
     * Retornem el grup a la activity del horari
     *
     * @param grup
     */
    public void retornarGrup(String grup) {
        Intent iHorari = new Intent(this, Horari.class);
        iHorari.putExtra("grup", grup);
    }

    public void botoActualitzarValors(View v) {
        switch (v.getId()){
            //case
        }
    }

    @Override
    public void onClick(View view) {

    }
}
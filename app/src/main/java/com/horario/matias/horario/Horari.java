package com.horario.matias.horario;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Horari extends AppCompatActivity {

    static SQLiteDatabase db;
    static BDActivity bdActivity;
    ArrayList<String> valors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Per reiniciar l'aplicació quan modifiquem les preferencies per que tinguin efecte.
     */
    public void reiniciarApp() {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    /**
     * Per obtenir el dia de la semana actual del sistema.
     *
     * @return
     */
    public static String getDiaSetmanaSistema() {
        Calendar cal = Calendar.getInstance();
        String[] diesSetmana = new String[]{"Diumenge", "Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres", "Dissabte"};
        int numeroDia = cal.get(Calendar.DAY_OF_WEEK);
        String dia = diesSetmana[numeroDia - 1];
        return dia;
    }

    /**
     * Per obtenir l'hora actual del sistema.
     *
     * @return
     */
    public static String getHoraSistema() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

   /* public void crearBD() {
        bdActivity = new BDActivity(this, "BDHorari", null, 1);
        db = bdActivity.getWritableDatabase();
    }*/

    /**
     * Retornem els valors de la consulta i el retornem en un arraylist.
     */
    public ArrayList<String> consultaValors() {

        bdActivity = new BDActivity(this, "BDHorari", null, 1);
        db = bdActivity.getWritableDatabase();


        String grupPrefs = getIntent().getStringExtra("grup");

        //String grupPrefs = "A2";

        if (db != null) {
            String[] args = new String[]{getHoraSistema(), grupPrefs, getDiaSetmanaSistema()};
            Cursor c = db.rawQuery("SELECT * FROM THoraris WHERE ? BETWEEN horaInici AND horaFi AND grup = ? AND dia = ?", args);
            if (c.moveToFirst()) {
                do {
                    valors.add(bdActivity.getProfOAss(Integer.parseInt(c.getString(1)), false));//afegeix nom assignatura a l'array.
                    valors.add(c.getString(2)); // afegeix l'hora d'inici a l'array.
                    valors.add(c.getString(3)); //afegeix l'hora final a l'array.
                    valors.add(bdActivity.getProfOAss(Integer.parseInt(c.getString(4)), true)); // afegeix el nom del professor a l'array
                    valors.add(c.getString(5)); //afegeix la classe a l'array
                } while (c.moveToNext());
            }
        } else {
            Toast.makeText(this, "No hi ha classe en aquest moment.", Toast.LENGTH_SHORT).show();
        }
        return valors;
    }

    public void setTV() {
        MainActivity ma = new MainActivity();
        ma.tvClasse.setText(valors.get(0));
        ma.tvHoraInici.setText(valors.get(1));
        ma.tvHoraFi.setText(valors.get(2));
        ma.tvProfessor.setText(valors.get(3));
        ma.tvClasse.setText(valors.get(4));
        ma.tvSeparacio.setText("-");
    }
}


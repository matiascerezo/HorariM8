package com.horario.matias.horario;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Horari extends AppCompatActivity {

    private String assignatura;
    private String professor;
    private String classe;
    private String horaInici;
    private String horaFinal;

    public Horari(String assignatura, String professor, String classe, String horaInici, String horaFinal) {
        this.assignatura = assignatura;
        this.professor = professor;
        this.classe = classe;
        this.horaInici = horaInici;
        this.horaFinal = horaFinal;
    }

    public String getAssignatura() {
        return assignatura;
    }

    public String getProfessor() {
        return professor;
    }

    public String getClasse() {
        return classe;
    }

    public String getHoraInici() {
        return horaInici;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
     * Per reiniciar l'aplicació quan modifiquem les preferencies per que tinguin efecte.
     */
    public void reiniciarApp() {
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
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
}


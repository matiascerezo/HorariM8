package com.horario.matias.horario;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class BDActivity extends SQLiteOpenHelper {

    //Creació de las bases de dades que guarden els professors, les asignatures i els horaris de les classes.
    final String THORARIS = "CREATE TABLE THoraris(idHorari INTEGER PRIMARY KEY, grup TEXT, idAssignatura INTEGER, horaInici TEXT, horaFi TEXT, dia TEXT, idProfessor INTEGER, classe TEXT)";
    final String TPROFESSORS = "CREATE TABLE TProfessors(idProfessor INTEGER PRIMARY KEY, nomProfe TEXT)";
    final String TASSIGNATURES = "CREATE TABLE TAssignatura(idAssignatura INTEGER PRIMARY KEY, modul TEXT, idProfessor INTEGER)";
    final String TGRUPS = "CREATE TABLE TGrups(nomGrup TEXT)";

    private String columnes = "idHorari, grup, modul, nomProfe, classe, horaInici, horaFi, dia";
    private String taules = " THoraris H, TAssignatura A, TProfessors P, TGrups G";
    private String where = "H.grup = G.nomGrup AND H.idAssignatura = A.idAssignatura AND H.idProfessor = P.idProfessor";
    Context context;

    /**
     * Constructor de BDActivity
     * @param context
     */
    public BDActivity(Context context) {
        super(context, "DBHorari", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Fem l'execucció de les creacions de les taules anteriors.

        sqLiteDatabase.execSQL(TPROFESSORS);
        sqLiteDatabase.execSQL(TASSIGNATURES);
        sqLiteDatabase.execSQL(TGRUPS);
        sqLiteDatabase.execSQL(THORARIS);

        //Fem els inserts into de les taules.
        //Insertem els professors a la taula "TProfessors".
        sqLiteDatabase.execSQL("INSERT INTO TProfessors VALUES(1, 'Leo')");
        sqLiteDatabase.execSQL("INSERT INTO TProfessors VALUES(2, 'Josefa')");
        sqLiteDatabase.execSQL("INSERT INTO TProfessors VALUES(3, 'Lluís')");
        sqLiteDatabase.execSQL("INSERT INTO TProfessors VALUES(4, 'Marta')");
        sqLiteDatabase.execSQL("INSERT INTO TProfessors VALUES(5, 'Jorge')");
        sqLiteDatabase.execSQL("INSERT INTO TProfessors VALUES(6, 'Cap')");

        //Insertem els professors a la taula "TGrups".
        sqLiteDatabase.execSQL("INSERT INTO TGrups VALUES('A1')");
        sqLiteDatabase.execSQL("INSERT INTO TGrups VALUES('A2')");

        //Insertem les assignatures a la taula "TAsignatures".
        sqLiteDatabase.execSQL("INSERT INTO TAssignatura VALUES(1, 'M07. Des. de interfícies', 1)");
        sqLiteDatabase.execSQL("INSERT INTO TAssignatura VALUES(2, 'Tutoria', 2)");
        sqLiteDatabase.execSQL("INSERT INTO TAssignatura VALUES(3, 'M08. Prog. de d. mòbils', 3)");
        sqLiteDatabase.execSQL("INSERT INTO TAssignatura VALUES(4, 'M10. Sist. de gest. empresarial', 4)");
        sqLiteDatabase.execSQL("INSERT INTO TAssignatura VALUES(5, 'M02/06. BD i Accés a dades', 5)");
        sqLiteDatabase.execSQL("INSERT INTO TAssignatura VALUES(6, 'M09. Prog. de processos', 5)");
        sqLiteDatabase.execSQL("INSERT INTO TAssignatura VALUES(7, 'M03. Programació bàsica', 2)");
        sqLiteDatabase.execSQL("INSERT INTO TAssignatura VALUES(8, 'Pati', 6)");

        //------------------------------------------> DILLUNS
        //Insertem el Dilluns a la taula d'horaris grup A1.
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (1, 'A1', '1', '15:00', '18:00', 'Dilluns', 1, '201') ");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (2, 'A1', '8', '18:01', '18:20', 'Dilluns', 6, 'Pati Interior o Exterior')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (3, 'A1', '2', '18:21', '19:20', 'Dilluns', 2, '201 o casa')");
        //Insertem el Dilluns a la taula d'horaris grup A2.
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (4, 'A2', '1', '15:00', '18:00', 'Dilluns', 1, '201') ");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (5, 'A2', '8', '18:01', '18:20', 'Dilluns', 6, 'Pati Interior o Exterior')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (6, 'A2', '2', '18:21', '19:20', 'Dilluns', 2, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (7, 'A2', '7', '19:21', '20:20', 'Dilluns', 2, '201')");

        //------------------------------------------> DIMARTS
        //Insertem el Dimarts a la taula d'horaris grup A1.
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (8, 'A1', '7', '15:00', '17:00', 'Dimarts', 2, '208') ");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (9, 'A1', '4', '17:01', '18:00', 'Dimarts', 4, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (10, 'A1', '8', '18:01', '18:20', 'Dimarts', 6, 'Pati Interior o Exterior')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (11, 'A1', '4', '18:21', '19:20', 'Dimarts', 4, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (12, 'A1', '5', '19:21', '21:20', 'Dimarts', 5, '201')");
        //Insertem el Dimarts a la taula d'horaris grup A2.
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (13, 'A2', '3', '15:00', '17:00', 'Dimarts', 3, '201') ");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (14, 'A2', '4', '17:01', '18:00', 'Dimarts', 4, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (15, 'A2', '8', '18:01', '18:20', 'Dimarts', 6, 'Pati Interior o Exterior')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (16, 'A2', '4', '18:21', '19:20', 'Dimarts', 4, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (17, 'A2', '5', '19:21', '21:20', 'Dimarts', 5, '201')");

        //------------------------------------------> DIMECRES
        //Insertem el Dimecres a la taula d'horaris grup A1.
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (18, 'A1', '5', '16:00', '17:00', 'Dimecres', 5, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (19, 'A1', '6', '17:01', '18:00', 'Dimecres', 5, '208')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (20, 'A1', '8', '18:01', '18:20', 'Dimecres', 6, 'Pati Interior o Exterior')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (21, 'A1', '6', '18:21', '19:20', 'Dimecres', 5, '208')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (22, 'A1', '7', '19:21', '21:20', 'Dimecres', 2, '208')");
        //Insertem el Dimecres a la taula d'horaris grup A2.
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (23, 'A2', '5', '16:00', '17:00', 'Dimecres', 5, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (24, 'A2', '3', '17:01', '18:00', 'Dimecres', 3, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (25, 'A2', '8', '18:01', '18:20', 'Dimecres', 6, 'Pati Interior o Exterior')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (26, 'A2', '3', '18:21', '19:20', 'Dimecres', 3, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (27, 'A2', '6', '19:21', '20:20', 'Dimecres', 5, '201')");

        //------------------------------------------> DIJOUS
        //Insertem el Dijous a la taula d'horaris grup A1.
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (28, 'A1', '6', '15:00', '16:00', 'Dijous', 5, '208')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (29, 'A1', '3', '16:01', '18:00', 'Dijous', 3, '208')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (30, 'A1', '8', '18:01', '18:20', 'Dijous', 6, 'Pati Interior o Exterior')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (31, 'A1', '5', '18:21', '21:20', 'Dijous', 5, '201')");
        //Insertem el Dijous a la taula d'horaris grup A2.
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (32, 'A2', '7', '16:00', '18:00', 'Dijous', 2, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (33, 'A2', '8', '18:01', '18:20', 'Dijous', 6, 'Pati Interior o Exterior')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (34, 'A2', '5', '18:21', '21:20', 'Dijous', 5, '201')");

        //------------------------------------------> DIVENDRES
        //Insertem el Divendres a la taula d'horaris grup A1.
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (35, 'A1', '4', '15:00', '17:00', 'Divendres', 4, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (36, 'A1', '3', '17:01', '18:00', 'Divendres', 3, '208')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (37, 'A1', '8', '18:01', '18:20', 'Divendres', 6, 'Pati Interior o Exterior')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (38, 'A1', '3', '18:21', '19:20', 'Divendres', 3, '208')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (39, 'A1', '5', '19:21', '21:20', 'Divendres', 5, '201')");
        //Insertem el Divendres a la taula d'horaris grup A2.
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (40, 'A2', '4', '15:00', '17:00', 'Divendres', 4, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (41, 'A2', '6', '17:01', '18:00', 'Divendres', 5, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (42, 'A2', '8', '18:01', '18:20', 'Divendres', 6, 'Pati Interior o Exterior')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (43, 'A2', '6', '18:21', '19:20', 'Divendres', 5, '201')");
        sqLiteDatabase.execSQL("INSERT INTO THoraris VALUES (44, 'A2', '5', '19:21', '21:20', 'Divendres', 5, '201')");
    }

    /**
     * Fem la consulta a la BD.
     *
     * @return
     */
    public Horari getHorariPerHora() {

        String diaSemana = Horari.getDiaSetmanaSistema();
        String horaSistema = Horari.getHoraSistema();
        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(context);
        String grup = shaPref.getString("grup","A1");
        //SharedPreferences shaPrefs = context.getSharedPreferences("shaPrefs", Context.MODE_PRIVATE);
        //String grup = sharedPreferences.getString("grup","A1");

        String[] valors = {diaSemana, grup, horaSistema};

        Cursor c = this.getReadableDatabase().rawQuery("SELECT " + columnes + " FROM " + taules +
                " WHERE " + where + " AND dia = ? AND nomGrup = ? AND ? BETWEEN horaInici AND horaFi", valors);

        ArrayList<Horari> horaris = getHorariPorCursor(c);
        return (horaris.size() == 0 ? null : horaris.get(0));
    }

    /**
     * Afegim a l'Arraylist els valors.
     * @param c
     * @return
     */
    private ArrayList<Horari> getHorariPorCursor(Cursor c) {
        ArrayList<Horari> horaris = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                horaris.add(new Horari(c.getString(2), c.getString(3), c.getString(4)
                        , c.getString(5), c.getString(6)));
            } while (c.moveToNext());
        }
        c.close();
        return horaris;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
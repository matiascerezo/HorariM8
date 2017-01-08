package com.horario.matias.horario;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SPConfActivity extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String NOM = "etNom";
    private static final String GRUP = "grup";
    private static final String TEMAFOSC = "temaFosc";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    /**
     * Per opcions String
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, final String key) {
        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(context);
        return shaPref.getString(key, "");
    }
    public static void setString (Context context, final String key, final String valor) {
        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = shaPref.edit();
        editor.putString(key, valor);
        editor.commit();
    }

    /**
     * Per valors boolean
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(Context context, final String key, boolean defaultValue) {
        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(context);
        return shaPref.getBoolean(key, defaultValue);
    }
    public static void setBoolean (Context context, final String key, final boolean valor) {
        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = shaPref.edit();
        editor.putBoolean(key, valor);
        editor.commit();
    }


    public static String getNombre() {
        return NOM;
    }

    public static String getGrup() {
        return GRUP;
    }

    public String getTemaFosc() {
        return TEMAFOSC;
    }
    /**
     * Per quan es canvii alguna cosa desde el menu de preferencies
     * @param shaPrefs
     * @param key
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences shaPrefs, String key) {
        shaPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        switch (key) {
            case NOM:
                String nom ="" + shaPrefs.getString(NOM, "NULL");
                Toast.makeText(getActivity().getApplicationContext(), "Guardat correctament", Toast.LENGTH_SHORT).show();
                break;
            case GRUP:
                String grup = "" + shaPrefs.getString(GRUP, "NULL");
                //TextView tvGrup = (TextView) getActivity().findViewById(R.id.tvGrup);
                //tvGrup.setTextColor(Color.WHITE);
                //tvGrup.setText(grup);
                Toast.makeText(getActivity().getApplicationContext(), "Guardat correctament", Toast.LENGTH_SHORT).show();
                break;
            case TEMAFOSC:
                boolean temaFosc = shaPrefs.getBoolean(TEMAFOSC, false);
                if(temaFosc){
                    Toast.makeText(getActivity().getApplicationContext(), "Tema fosc activat", Toast.LENGTH_SHORT).show();
                }else {
                    //getActivity().findViewById(R.id.activity_main).setBackgroundColor(Color.rgb(69,69,69));
                    Toast.makeText(getActivity().getApplicationContext(), "Tema fosc desactivat", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

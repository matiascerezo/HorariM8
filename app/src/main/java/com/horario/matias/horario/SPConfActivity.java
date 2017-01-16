package com.horario.matias.horario;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class SPConfActivity extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String NOM = "etNom";
    private static final String GRUP = "grup";
    private static final String TEMAFOSC = "temaFosc";
    Horari horari;


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
    /*
    public static void setString (Context context, final String key, final String valor) {
        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = shaPref.edit();
        editor.putString(key, valor);
        editor.commit();
    }*/

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
    /*
    public static void setBoolean (Context context, final String key, final boolean valor) {
        SharedPreferences shaPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = shaPref.edit();
        editor.putBoolean(key, valor);
        editor.commit();
    }*/


    public static String getNombre() {
        return NOM;
    }

    public static String getGrup() {
        return GRUP;
    }

    public static String getTemaFosc() {
        return TEMAFOSC;
    }

    /**
     * Per quan es canvii alguna cosa desde el menu de preferencies mostrarem uns Toasts.
     * @param shaPrefs
     * @param key
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences shaPrefs, String key) {
        shaPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        switch (key) {
            case NOM:
                String nom ="" + shaPrefs.getString(NOM, "NULL");
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.guardatOk), Toast.LENGTH_SHORT).show();
                break;
            case GRUP:
                String grup = "" + shaPrefs.getString(GRUP, "NULL");
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.guardatOk), Toast.LENGTH_SHORT).show();
                horari.reiniciarApp();
                break;
            case TEMAFOSC:
                boolean temaFosc = shaPrefs.getBoolean(TEMAFOSC, false);
                if(temaFosc){
                    Toast.makeText(getActivity().getApplicationContext(), getString(R.string.foscActivat), Toast.LENGTH_SHORT).show();
                    horari.reiniciarApp();
                }else {
                    Toast.makeText(getActivity().getApplicationContext(), getString(R.string.foscDesactivat), Toast.LENGTH_SHORT).show();
                    horari.reiniciarApp();
                }
                break;
        }
    }
}

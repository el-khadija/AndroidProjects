package android.example.loadingDataBySQLite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class DAO {
    private DBTache helper;
    private SQLiteDatabase db;
    private final String nom_table = "tache";

    public DAO(DBTache helper) {
        this.helper = helper;
        db = helper.getWritableDatabase();
    }

    public long addTaches(Tache e) {
        ContentValues values = new ContentValues();
        values.put("titre", e.getTitre());
        values.put("date", e.getDate());
        values.put("status", e.getStatus());
        long idp = db.insert(nom_table, null, values);
        return idp;
    }

    public void deleteAllTaches() {
        db.delete(nom_table, null, null);
    }

    public ArrayList<Tache> getAllTaches() {
        ArrayList<Tache> liste = new ArrayList<>();
        Cursor cursor = db.query(nom_table, new String[]{"id", "titre", "date", "status"},
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String titre = cursor.getString(cursor.getColumnIndex("titre"));
            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
            @SuppressLint("Range") String status = cursor.getString(cursor.getColumnIndex("status"));
            Tache t = new Tache(titre, date, status);
            t.setId(id);
            liste.add(t);
            cursor.moveToNext();
        }
        cursor.close();
        return liste;
    }
}

package android.example.loadingDataBySQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBTache extends SQLiteOpenHelper {
    private final String dbName="dbtaches.db";
    private final int version=1;


    public DBTache(Context context) {
        super(context,"dbtaches.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String req="create table tache(id integer primary key autoincrement," +
                "titre text,date text,status text);";
        sqLiteDatabase.execSQL(req);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists tache");
        onCreate(sqLiteDatabase);
    }
}

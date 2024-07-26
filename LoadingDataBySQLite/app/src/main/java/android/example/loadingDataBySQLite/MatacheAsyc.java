package android.example.loadingDataBySQLite;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

public class MatacheAsyc extends AsyncTask<Void, Void, ArrayList<Tache>> {
    private Context context;

    public MatacheAsyc(Context context) {
        this.context = context;
    }

    @Override
    protected ArrayList<Tache> doInBackground(Void... voids) {
        // Appel de la méthode loadData() pour récupérer les données depuis l'API REST
        String jsonData = LoadData.loadData();

        // Convertir le flux JSON en une liste d'objets Tache
        ArrayList<Tache> taches = LoadData.fromJsonToData(jsonData);

        return taches;
    }

    @Override
    protected void onPostExecute(ArrayList<Tache> taches) {
        super.onPostExecute(taches);

        // Réinitialiser la base de données avec la liste des taches téléchargées
        if (context != null) {
            DAO dao = new DAO(new DBTache(context));
            dao.deleteAllTaches();
            for (Tache tache : taches) {
                dao.addTaches(tache);
            }

            Log.d("LoadDataTask", "Tâches téléchargées et ajoutées à la base de données");
        } else {
            Log.e("LoadDataTask", "Contexte null, impossible d'accéder à la base de données");
        }
    }
}

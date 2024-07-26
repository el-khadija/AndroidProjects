package android.example.loadingDataBySQLite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoadData {
    // Méthode pour charger les données depuis l'API REST et retourner le flux JSON sous forme de chaîne
    public static String loadData() {
        StringBuilder result = new StringBuilder();
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL("https://my-json-server.typicode.com/youssefyazidi/api3/taches");
            urlConnection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return result.toString();
    }

    // Méthode pour convertir le flux JSON en une liste de tâches
    public static ArrayList<Tache> fromJsonToData(String json) {
        ArrayList<Tache> taches = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String titre = jsonObject.getString("titre");
                String date = jsonObject.getString("date");
                String statut = jsonObject.getString("status"); // Utilisation de la clé "status" au lieu de "statut"

                Tache tache = new Tache(titre, date, statut); // Utilisation du constructeur sans l'ID
                taches.add(tache);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return taches;
    }
}

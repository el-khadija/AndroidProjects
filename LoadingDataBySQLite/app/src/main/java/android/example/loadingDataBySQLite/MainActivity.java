package android.example.loadingDataBySQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.example.loadingDataBySQLite.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button loadDataButton;
    private ListView listView;
    private TacheAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadDataButton = findViewById(R.id.loadDataButton);
        listView = findViewById(R.id.listView);

        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer la tâche asynchrone pour charger les données depuis l'API REST
                new LoadDataTask().execute();
            }
        });

        adapter = new TacheAdapter(MainActivity.this, new ArrayList<Tache>());
        listView.setAdapter(adapter);
    }

    private class LoadDataTask extends AsyncTask<Void, Void, ArrayList<Tache>> {
        @Override
        protected ArrayList<Tache> doInBackground(Void... voids) {
            // Charger les données depuis l'API REST
            String jsonData = LoadData.loadData();
            // Transformer les données JSON en objets Tache
            return LoadData.fromJsonToData(jsonData);
        }

        @Override
        protected void onPostExecute(ArrayList<Tache> taches) {
            super.onPostExecute(taches);
            // Mettre à jour l'adaptateur avec les nouvelles données
            adapter.clear();
            adapter.addAll(taches);
        }
    }
}

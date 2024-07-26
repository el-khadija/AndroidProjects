package android.example.swipe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.example.swipe.R;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity3 extends AppCompatActivity {
    private TextView afficheNom;
    private TextView afficheAdress;
    private TextView afficheTele;
    private TextView afficheDateNaiss;
    private TextView affichePays;
    private Button btn_valide;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Récupérer les données transmises via l'Intent
        Intent intent = getIntent();
        String nom = intent.getStringExtra("selectedNom");
        String adr = intent.getStringExtra("selectedAdr");
        String phone = intent.getStringExtra("selectedPhone");
        String dateNaiss = intent.getStringExtra("selectedDate");
        String pays = intent.getStringExtra("selectedCountry");

        // Initialiser les TextViews avec les données récupérées
        afficheNom = findViewById(R.id.affiche_nom);
        afficheNom.setText(nom);

        afficheAdress = findViewById(R.id.affiche_adress);
        afficheAdress.setText(adr);

        afficheTele = findViewById(R.id.affiche_tele);
        afficheTele.setText(phone);

        afficheDateNaiss = findViewById(R.id.affiche_date_naiss);
        afficheDateNaiss.setText(dateNaiss);

        affichePays = findViewById(R.id.affiche_pays);
        affichePays.setText(pays);

        btn_valide = findViewById(R.id.valideButton);
        btn_valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sauvegarderDonnees(nom, adr, phone, dateNaiss, pays);
            }
        });
    }

    private void sauvegarderDonnees(String nom, String adr, String phone, String dateNaiss, String pays) {
        // Créer une chaîne de données à sauvegarder dans le fichier
        String data = "Nom: " + nom + "\n" +
                "Adresse: " + adr + "\n" +
                "Téléphone: " + phone + "\n" +
                "Date de naissance: " + dateNaiss + "\n" +
                "Pays: " + pays;

        // Vérifier si le stockage externe est disponible en écriture
        if (isExternalStorageWritable()) {
            // Écrire les données dans le stockage externe
            File file = new File(getExternalFilesDir(null), "donnees.txt");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data.getBytes());
                fos.close();
                Toast.makeText(this, "Données sauvegardées avec succès", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Le stockage externe n'est pas disponible", Toast.LENGTH_SHORT).show();
        }
    }

    // Vérifier si le stockage externe est disponible en écriture
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}

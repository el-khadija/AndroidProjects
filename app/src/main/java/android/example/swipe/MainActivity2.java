package android.example.swipe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;

import android.example.swipe.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    private Spinner spinner;
    private ArrayAdapter arrayAdapter;
    private Button btn_date;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        spinner = findViewById(R.id.spinner);
        btn_date = findViewById(R.id.dateButton);
        textView = findViewById(R.id.dateSelect);

        // Récupération de valeurs de pays
        String[] countryNames = getResources().getStringArray(R.array.country_names);

        // Créer un ArrayAdapter pour les noms des pays
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryNames);

        // Spécifier le style du Spinner déroulant
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Définir l'ArrayAdapter sur le Spinner
        spinner.setAdapter(adapter);

        // Ajouter un écouteur de clic au bouton pour afficher le DatePickerDialog
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Ajouter un écouteur de glissement (swipe) vers la droite
        findViewById(R.id.activity2).setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public boolean onSwipeRight() {
                // Récupérer les données nécessaires
                String selectedDate = textView.getText().toString();
                String selectedCountry = spinner.getSelectedItem().toString();

                // Passer les données à MainActivity3
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("selectedCountry", selectedCountry);
                startActivity(intent);

                return true;
            }
            @Override
            public boolean onSwipeLeft() {
                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }

    private void showDatePickerDialog() {
        // Récupérer la date actuelle
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Créer un DatePickerDialog et le montrer
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Mettre à jour le TextView avec la date sélectionnée
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        textView.setText(selectedDate);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }
}

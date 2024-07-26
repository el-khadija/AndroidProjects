package android.example.swipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.swipe.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mEditTextnom;
    private EditText mEditTextadr;
    private EditText mEditTextphone;
    private View activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity=findViewById(R.id.activity1);
        mEditTextadr=findViewById(R.id.edit_adr);
        mEditTextnom=findViewById(R.id.edit_nom);
        mEditTextphone=findViewById(R.id.edit_tel);
        //swipe
        activity.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public boolean onSwipeRight() {
                // Récupérer les données saisies
                String nom = mEditTextnom.getText().toString();
                String adr = mEditTextadr.getText().toString();
                String phone = mEditTextphone.getText().toString();

                // Créer un Intent pour démarrer MainActivity2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                // Ajouter les données dans l'Intent
                intent.putExtra("nom", nom);
                intent.putExtra("adr", adr);
                intent.putExtra("phone", phone);

                // Démarrer MainActivity2
                startActivity(intent);

                return true;
            }
        });
    }

}
package android.example.loadingDataBySQLite;

import android.content.Context;
import android.example.loadingDataBySQLite.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class TacheAdapter extends ArrayAdapter<Tache> {

    public TacheAdapter(Context context, ArrayList<Tache> taches) {
        super(context, 0, taches);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Récupérer la tâche pour cette position
        Tache tache = getItem(position);

        // Vérifier si une vue réutilisable existe, sinon en créer une nouvelle
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);
        }

        // Récupérer les vues à remplir avec les données de la tâche
        TextView titreTextView = convertView.findViewById(R.id.textViewtitre);
        TextView dateTextView = convertView.findViewById(R.id.TextViewdate);
        ImageView statutImageView = convertView.findViewById(R.id.ImageViewstatut);

        // Remplir les vues avec les données de la tâche
        if (tache != null) {
            titreTextView.setText(tache.getTitre());
            dateTextView.setText(tache.getDate());

            // Affichage de l'icône en fonction du statut de la tâche
            if (tache.getStatus().equals("En cours")) {
                statutImageView.setImageResource(R.drawable.pending);
            } else if (tache.getStatus().equals("terminé")) {
                statutImageView.setImageResource(R.drawable.termine);
            }
        }

        return convertView;
    }
}


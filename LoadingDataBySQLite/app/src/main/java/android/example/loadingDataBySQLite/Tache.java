package android.example.loadingDataBySQLite;

public class Tache {
    //private static int cpt = 0;
    private int id;
    private String titre;
    private String date;
    private String status;

    public Tache(String titre, String date, String status) {
        this.titre = titre;
        this.date = date;
        this.status = status;
    }

    /*
    public Tache() {
        this.titre = "";
        this.date = "";
        this.status = "";
    }
*/
    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

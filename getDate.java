import java.util.Date;
import java.text.SimpleDateFormat;

public class getDate {
    public static String getCurrentDate() {

        Date currentDate = new Date(); //On récupere la date

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //On la formate

        return dateFormat.format(currentDate); //On la retourne

    }
}
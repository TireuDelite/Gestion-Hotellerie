import java.io.IOException;

public class Clear {
    public final static void clear()  
    {  
        try {
            if (System.getProperty("os.name").contains("Windows")) // Si l'utilisateur est sous Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor(); // Si l'utilisateur est sous Linux
        } catch (IOException | InterruptedException ex) {}
    }  
}

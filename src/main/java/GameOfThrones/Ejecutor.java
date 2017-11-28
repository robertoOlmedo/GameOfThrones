package GameOfThrones;
import java.io.IOException;

public class Ejecutor {
    
    
  
    public static void main(String[] args) throws IOException {
        GameOfThrones gameOfThrones = new GameOfThrones();
        gameOfThrones.loadFile("C:\\Users\\xx514\\IdeaProjects\\GameOfThrones\\src\\main\\java\\resources\\listado.txt");
        gameOfThrones.getFamily("Stark");
    }
}

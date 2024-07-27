import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day02_part2 {

    private static String FILE_NAME = "src/inputs/day02.txt";
    private static int MAX_NUM_GREEN = 13;
    private static int MAX_NUM_RED = 12;
    private static int MAX_NUM_BLUE = 14;

    public static void main(String[] args) throws IOException {
        List<Game> games = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Game")) {
                    
                    int gameId = Integer.parseInt(line.split(" ")[1].split(":")[0]);
                    Game game = new Game(gameId);
                    String[] plays = line.split(": ")[1].split("; ");
                    for (String play : plays) {
                        game.addPlay(parsePlay(play));
                    }
                    games.add(game);
                }
            }
        }

        // Print the parsed games
        for (Game game : games) {
            System.out.println(game);
        }

        int totalSum = 0;

        for(Game g : games) {
            int maxNumGreen = 0;
            int maxNumRed = 0;
            int maxNumBlue = 0;
            for(CubePlay p : g.plays) {
                maxNumGreen = Math.max(p.green, maxNumGreen);
                maxNumRed = Math.max(p.red, maxNumRed);
                maxNumBlue = Math.max(p.blue, maxNumBlue);
            }

            totalSum += maxNumGreen * maxNumBlue * maxNumRed;
  
        }
        System.out.println("Solution: " + totalSum);
    }

    private static CubePlay parsePlay(String play) {
        int blue = 0, red = 0, green = 0;
        String[] parts = play.split(", ");
        for (String part : parts) {
            String[] tokens = part.split(" ");
            int count = Integer.parseInt(tokens[0]);
            String color = tokens[1];
            switch (color) {
                case "blue":
                    blue += count;
                    break;
                case "red":
                    red += count;
                    break;
                case "green":
                    green += count;
                    break;
            }
        }
        return new CubePlay(blue, red, green);
    }
}

class CubePlay {
    public int blue;
    public int red;
    public int green;

    public CubePlay(int blue, int red, int green) {
        this.blue = blue;
        this.red = red;
        this.green = green;
    }

    @Override
    public String toString() {
        return "Blue: " + blue + ", Red: " + red + ", Green: " + green;
    }
}

class Game {
    public List<CubePlay> plays;
    public int id;

    public Game(int id) {
        this.id = id;
        this.plays = new ArrayList<>();
    }

    public void addPlay(CubePlay play) {
        plays.add(play);
    }

    @Override
    public String toString() {
        return "Plays: " + plays;
    }
}
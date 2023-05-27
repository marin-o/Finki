import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Partial exam II 2016/2017
 */

class Team{
    String teamName;
    int totalGoals=0;
    int wins=0,draws=0,losses=0;
    int goalsTaken=0;
    int gamesPlayed=0;

    public Team( String teamName ) {
        this.teamName=teamName;
    }

    public int getPoints(){
        return wins*3+draws;
    }
    public int getGoalDifference(){
        return totalGoals-goalsTaken;
    }

    @Override
    public String toString(){
        return String.format("%-15s%5d%5d%5d%5d%5d", teamName, gamesPlayed,wins,draws,losses,getPoints());
    }
}

class FootballTable{
    Map<String,Team> teams = new LinkedHashMap<>();

    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals){
        Team home = teams.computeIfAbsent(homeTeam,key -> new Team(homeTeam));
        Team away = teams.computeIfAbsent(awayTeam,key -> new Team(awayTeam));
        home.totalGoals+=homeGoals;
        home.goalsTaken+=awayGoals;
        away.totalGoals+=awayGoals;
        away.goalsTaken+=homeGoals;
        home.gamesPlayed++;
        away.gamesPlayed++;
        if(homeGoals>awayGoals){
            home.wins++;
            away.losses++;
        } else if ( homeGoals<awayGoals ){
            home.losses++;
            away.wins++;
        } else {
            home.draws++;
            away.draws++;
        }
    }

    public void printTable(){
       List<Team> teamsList = teams.values()
                .stream()
                .sorted(Comparator.comparing(Team::getPoints)
                        .thenComparing(Team::getGoalDifference).reversed()
                        .thenComparing(team -> team.teamName))
                .collect(Collectors.toList());
        IntStream.range(0,teamsList.size())
                .forEach(i -> System.out.printf("%2d. %s\n",i+1,teamsList.get(i)));

    }
}
public class FootballTableTest {
    public static void main(String[] args) throws IOException {
        FootballTable table = new FootballTable();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.lines()
                .map(line -> line.split(";"))
                .forEach(parts -> table.addGame(parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])));
        reader.close();
        System.out.println("=== TABLE ===");
        System.out.printf("%-19s%5s%5s%5s%5s%5s\n", "Team", "P", "W", "D", "L", "PTS");
        table.printTable();
    }
}

// Your code here


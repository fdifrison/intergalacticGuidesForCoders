public class Main {
    public static void main(String[] args) {
        LeagueTable<FootballTeam> footballLeague = new LeagueTable();
        LeagueTable<BasketballTeam> basketball = new LeagueTable();

        FootballTeam team1 = new FootballTeam("Juventus", 10);
        FootballTeam team2 = new FootballTeam("Inter", 4);
        FootballTeam team3 = new FootballTeam("Milan", 15);

        BasketballTeam shouldNotWorkTeam = new BasketballTeam("Fortitudo", 23);

        footballLeague.addTeam(team1);
        footballLeague.addTeam(team2);
        footballLeague.addTeam(team3);
        // footballLeague.addTeam(shouldNotWorkTeam);

        footballLeague.printSortedTable();

    }
}
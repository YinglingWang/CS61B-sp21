package deque;

import org.junit.Test;

import java.util.Comparator;
import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    @Test
    public void maxTest() {
        MaxArrayDeque<Player> players = new MaxArrayDeque<>(Player.getRankingComparator());
        players.addFirst(new Player("p1", 1));
        players.addLast(new Player("p2", 2));
        players.addLast(new Player("p3", 4));
        assertEquals(4, players.max().getRanking());
        assertEquals("p3", players.max().getName());
    }

}

class Player {
    private int ranking;
    private String name;

    public Player(String name, int ranking) {
        this.name = name;
        this.ranking = ranking;
    }

    public String getName() {
        return this.name;
    }

    public int getRanking() {
        return this.ranking;
    }

    public static Comparator<Player> getRankingComparator() {
        return new PlayerRankingComparator();
    }

    public static class PlayerRankingComparator implements Comparator<Player> {
        @Override
        public int compare(Player firstPlayer, Player secondPlayer) {
            return Integer.compare(firstPlayer.getRanking(), secondPlayer.getRanking());
        }
    }
}



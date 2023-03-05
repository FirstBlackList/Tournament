package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Player;
import ru.netology.exception.NotRegisteredException;
import ru.netology.exception.AlreadyRegisteredException;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ManagerGame {
    private List<Player> players = new ArrayList<>();

    public void register(Player player) {
        players.add(player);
    }

    public int round(String playerName1, String playerName2) {

        int index_1 = foundIndex(playerName1);
        int index_2 = foundIndex(playerName2);
        if (index_1 == -1) {
            throw new NotRegisteredException("The first player is not registered!");
        } else if (index_2 == -1) {
            throw new NotRegisteredException("The second player is not registered!");
        } else if (index_1 == index_2) {
            throw new AlreadyRegisteredException("A player with that name is already registered");
        }
        if (players.get(index_1).getStrength() > players.get(index_2).getStrength()) {
            return 1;
        } else if (players.get(index_1).getStrength() < players.get(index_2).getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }

    public int foundIndex(String playerName) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                return players.indexOf(player);
            }
        }
        return -1;
    }
}

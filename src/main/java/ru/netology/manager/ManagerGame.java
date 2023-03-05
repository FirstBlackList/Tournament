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

        Player player_1 = foundIndex(playerName1);
        Player player_2 = foundIndex(playerName2);
        if (player_1 == null) {
            throw new NotRegisteredException("The first player is not registered!");
        } else if (player_2 == null) {
            throw new NotRegisteredException("The second player is not registered!");
        } else if (player_1 == player_2) {
            throw new AlreadyRegisteredException("A player with that name is already registered");
        }
        if (player_1.getStrength() > player_2.getStrength()) {
            return 1;
        } else if (player_1.getStrength() < player_2.getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }

    public Player foundIndex(String playerName) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                return player;
            }
        }
        return null;
    }
}

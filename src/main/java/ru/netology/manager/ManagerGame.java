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

        if (foundPlayer(playerName1) == null) {
            throw new NotRegisteredException("The first player is not registered!");
        } else if (foundPlayer(playerName2) == null) {
            throw new NotRegisteredException("The second player is not registered!");
        } else if (foundPlayer(playerName1) == foundPlayer(playerName2)) {
            throw new AlreadyRegisteredException("A player with that name is already registered");
        }
        if (foundPlayer(playerName1).getStrength() > foundPlayer(playerName2).getStrength()) {
            return 1;
        } else if (foundPlayer(playerName1).getStrength() < foundPlayer(playerName2).getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }

    public Player foundPlayer(String playerName) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                return player;
            }
        }
        return null;
    }
}

package ru.netology.TestManagerGame;

import ru.netology.manager.ManagerGame;
import ru.netology.domain.Player;
import ru.netology.exception.AlreadyRegisteredException;
import ru.netology.exception.NotRegisteredException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TestManagerGame {

    ManagerGame manager = new ManagerGame();
    Player player_1 = new Player(1, "Rubicon", 99);
    Player player_2 = new Player(2, "G-guRda", 99);
    Player player_3 = new Player(3, "BlackList", 100);
    Player player_4 = new Player(4, "_FranK_", 80);

    @Test
    void shouldFirstPlayerWin() {
        manager.register(player_1);
        manager.register(player_2);
        manager.register(player_3);
        manager.register(player_4);
        Assertions.assertEquals(1, manager.round("blacklist", "Rubicon"));
    }

    @Test
    void shouldSecondPlayerWin() {
        manager.register(player_1);
        manager.register(player_2);
        manager.register(player_3);
        manager.register(player_4);
        Assertions.assertEquals(2, manager.round("_FRANK_", "Rubicon"));
    }

    @Test
    void shouldDraw() {
        manager.register(player_1);
        manager.register(player_2);
        manager.register(player_3);
        manager.register(player_4);
        Assertions.assertEquals(0, manager.round("G-guRdA", "RUBICON"));
    }

    @Test
    void testAlreadyRegisteredExceptionPlayerName() {
        manager.register(player_1);
        manager.register(player_2);
        manager.register(player_3);
        manager.register(player_4);
        Assertions.assertThrows(AlreadyRegisteredException.class, () -> {
            manager.round("RUBICON", "RUBICON");
        });
    }

    @Test
    void testAlreadyRegisteredExceptionPlayerNameIgnoreCase() {
        manager.register(player_1);
        manager.register(player_2);
        manager.register(player_3);
        manager.register(player_4);
        Assertions.assertThrows(AlreadyRegisteredException.class, () -> {
            manager.round("_FranK_", "_FRANK_");
        });
    }

    @Test
    void testNotRegisteredExceptionFirstPlayerIsNotRegistered() {
        manager.register(player_1);
        manager.register(player_2);
        manager.register(player_3);
        manager.register(player_4);
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            manager.round("Sub-Zero", "RUBICON");
        });
    }

    @Test
    void testNotRegisteredExceptionSecondPlayerIsNotRegistered() {
        manager.register(player_1);
        manager.register(player_2);
        manager.register(player_3);
        manager.register(player_4);
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            manager.round("BLACKLIST", "SUB-ZERO");
        });
    }

    @Test
    void testNotRegisteredExceptionTheSecondPlayerIsMissing() {
        manager.register(player_1);
        manager.register(player_2);
        manager.register(player_3);
        manager.register(player_4);
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            manager.round("BlackList", "");
        });
    }

    @Test
    void testNotRegisteredExceptionTheFirstPlayerIsMissing() {
        manager.register(player_1);
        manager.register(player_2);
        manager.register(player_3);
        manager.register(player_4);
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            manager.round("", "_frank_");
        });
    }

    @Test
    void testNotRegisteredExceptionBothPlayersAreMissing() {
        manager.register(player_1);
        manager.register(player_2);
        manager.register(player_3);
        manager.register(player_4);
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            manager.round("", "");
        });
    }
}

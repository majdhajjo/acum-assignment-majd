package org.example.service;

import org.player.domain.Player;

import java.io.IOException;
import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers() throws IOException;

    Player getPlayerByID(String playerID) throws IOException;
}

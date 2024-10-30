package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.player.domain.Player;
import org.player.exceptions.NoPlayerDataException;
import org.player.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static org.player.config.SaveDataConfiguration.playerHashMap;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);

//    private static final HashMap<String, Player> playerHashMap = new HashMap<>();

    @Autowired
    private Environment environment;


    @Override
    public List<Player> getAllPlayers() throws IOException {
        log.debug("start get all player ");
        List<Player> players = new ArrayList<>();
        players= new ArrayList<>(playerHashMap.values());
//        CSVParser csvParser = getCsvParser();
//        for (CSVRecord csvRecord : csvParser) {
//            Player playerData = buildPlayer(csvRecord);
//            players.add(playerData);
////            playerHashMap.put(playerData.getPlayerID(), playerData);
//        }

//        csvParser.close();

        log.debug("players data: " + players);
        if (players.isEmpty()) {
            log.error("no data found in csv file");
            throw new NoPlayerDataException("no data found");
        }
        return players;
    }


    @Override
    public Player getPlayerByID(String playerID) throws IOException {
        log.debug("start get  player data for player:  " + playerID);
        if (playerHashMap.containsKey(playerID))
            return playerHashMap.get(playerID);
//        CSVParser csvParser = getCsvParser();
//        for (CSVRecord csvRecord : csvParser) {
//            if (Objects.equals(csvRecord.get("playerID"), playerID)) {
//                Player player = buildPlayer(csvRecord);
//                csvParser.close();
//                return player;
//            }
//        }
//        csvParser.close();
        log.error("no data found in csv file for player : " + playerID);
        throw new NoPlayerDataException("data not found for player : " + playerID);
    }

    private CSVParser getCsvParser() throws IOException {
        String fileName = environment.getProperty("FILE_NAME");
        if (fileName == null || fileName.isEmpty()) {
            log.error("failed to parse csv file. file name is missing");
            throw new NoPlayerDataException("missing file name");
        }
        Reader fileReader = new FileReader(fileName);
        return new CSVParser(fileReader, CSVFormat.DEFAULT.withHeader());
    }

    private Player buildPlayer(CSVRecord csvRecord) {
        // build player object for each row on csv file
        return Player.builder().playerID(csvRecord.get("playerID")).birthYear(csvRecord.get("birthYear"))
                .birthMonth(csvRecord.get("birthMonth")).birthDay(csvRecord.get("birthDay"))
                .birthCountry(csvRecord.get("birthCountry")).deathYear(csvRecord.get("deathYear"))
                .deathMonth(csvRecord.get("deathMonth")).deathCountry(csvRecord.get("deathCountry"))
                .deathDay(csvRecord.get("deathDay")).deathCity(csvRecord.get("deathCity"))
                .deathState(csvRecord.get("deathState")).nameFirst(csvRecord.get("nameFirst")).nameLast(csvRecord.get("nameLast"))
                .nameGiven(csvRecord.get("nameGiven")).weight(csvRecord.get("weight")).height(csvRecord.get("height"))
                .bats(csvRecord.get("bats")).thr(csvRecord.get("throws")).debut(csvRecord.get("debut"))
                .finalGame(csvRecord.get("finalGame")).retroID(csvRecord.get("retroID")).bbrefID(csvRecord.get("bbrefID")).build();
    }
}

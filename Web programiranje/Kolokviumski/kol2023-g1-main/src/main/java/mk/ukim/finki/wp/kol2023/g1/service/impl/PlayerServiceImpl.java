package mk.ukim.finki.wp.kol2023.g1.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.kol2023.g1.model.Player;
import mk.ukim.finki.wp.kol2023.g1.model.PlayerPosition;
import mk.ukim.finki.wp.kol2023.g1.model.Team;
import mk.ukim.finki.wp.kol2023.g1.model.exceptions.InvalidPlayerIdException;
import mk.ukim.finki.wp.kol2023.g1.model.exceptions.InvalidTeamIdException;
import mk.ukim.finki.wp.kol2023.g1.repository.PlayerRepository;
import mk.ukim.finki.wp.kol2023.g1.repository.TeamRepository;
import mk.ukim.finki.wp.kol2023.g1.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    @Override
    public List<Player> listAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById( Long id ) {
        return playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
    }

    @Override
    public Player create( String name, String bio, Double pointsPerGame, PlayerPosition position, Long team ) {
        Team t = teamRepository.findById(team).orElseThrow(InvalidTeamIdException::new);
        Player p = new Player(name,bio,pointsPerGame,position,t);
        return playerRepository.save(p);
    }

    @Override
    public Player update( Long id, String name, String bio, Double pointsPerGame, PlayerPosition position, Long team ) {
        Player p = this.findById(id);
        p.setName(name);
        p.setBio(bio);
        p.setPointsPerGame(pointsPerGame);
        p.setPosition(position);
        Team t = teamRepository.findById(team).orElseThrow(InvalidTeamIdException::new);
        p.setTeam(t);
        return playerRepository.save(p);
    }

    @Override
    public Player delete( Long id ) {
        Player p = this.findById(id);
        playerRepository.delete(p);
        return p;
    }

    @Override
    public Player vote( Long id ) {
        Player p = this.findById(id);
        Integer votes = p.getVotes();
        votes++;
        p.setVotes(votes);
        return playerRepository.save(p);
    }

    @Override
    public List<Player> listPlayersWithPointsLessThanAndPosition( Double pointsPerGame, PlayerPosition position ) {
        return playerRepository.findAllByPointsPerGameLessThanAndPositionEquals(pointsPerGame,position);
    }

    @Override
    public List<Player> listPlayersWithPointsLessThan( Double pointsPerGame ) {
        return playerRepository.findAllByPointsPerGameLessThan(pointsPerGame);
    }

    @Override
    public List<Player> listPlayersWithPosition( PlayerPosition position ) {
        return playerRepository.findAllByPositionEquals(position);
    }
}

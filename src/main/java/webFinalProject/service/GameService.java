package webFinalProject.service;

import webFinalProject.entity.Games;
import webFinalProject.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameService {

    private final GamesRepository gamesRepository;

    @Autowired
    public GameService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public Games findById(Long id){
        return gamesRepository.getOne(id);
    }

    public List<Games> findAll(){
        return gamesRepository.findAll();
    }

    public void saveGame(Games film) {
        gamesRepository.save(film);
    }

    public void deleteById(Long id){
        gamesRepository.deleteById(id);
    }
}

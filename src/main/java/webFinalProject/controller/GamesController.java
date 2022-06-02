package webFinalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import webFinalProject.entity.Games;
import webFinalProject.repository.GamesRepository;
import webFinalProject.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EnableSwagger2
@RestController
public class GamesController {

    private final GameService gameService;
    private final GamesRepository gamesRepository;

    @Autowired
    public GamesController(GameService gameService, GamesRepository gamesRepository) {
        this.gameService = gameService;
        this.gamesRepository = gamesRepository;
    }

    @GetMapping("/games")
    public String findAll(Model model){
        List<Games> games = gameService.findAll();
        model.addAttribute("games", games);
        return "game-list";
    }

    @GetMapping("/game-add")
    public String createGameForm(Games game){
        return "game-add";
    }

    @PostMapping("/game-add")
    public String createGame(Games game){

        gameService.saveGame(game);
        return "redirect:/games";
    }

    @GetMapping("game-delete/{id}")
    public String deleteGame(@PathVariable("id") Long id){
        gameService.deleteById(id);
        return "redirect:/games";
    }

    @GetMapping("/game-update/{id}")
    public String updateGameForm(@PathVariable("id") Long id, Model model){
        Games game = gameService.findById(id);
        model.addAttribute("game", game);
        return "game-update";
    }

    @PostMapping("/game-update")
    public String updateGame(Games game){

            gameService.saveGame(game);
            return "redirect:/games";

    }

    @GetMapping("/game/{id}")
    public String gameInfo(@PathVariable(value = "id") Long id, Model model) {
        if(!gamesRepository.existsById(id)) {
            return"redirect:/games";
        }
        Optional<Games> game = gamesRepository.findById(id);
        ArrayList<Games> res = new ArrayList<>();
        game.ifPresent(res::add);
        model.addAttribute("game", res);
        return"games-description";
    }
}
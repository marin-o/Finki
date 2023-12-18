package mk.ukim.finki.wp.kol2023.g1.web;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.kol2023.g1.model.PlayerPosition;
import mk.ukim.finki.wp.kol2023.g1.service.PlayerService;
import mk.ukim.finki.wp.kol2023.g1.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping({"/","/players"})
public class PlayersController {

    private final PlayerService playerService;
    private final TeamService teamService;


    /**
     * This method should use the "list.html" template to display all players.
     * The method should be mapped on paths '/' and '/players'.
     * The arguments that this method takes are optional and can be 'null'.
     * In the case when the arguments are not passed (both are 'null') all players should be displayed.
     *      * If one, or both of the arguments are not 'null', the players that are the result of the call
     *      * to the method 'listPlayersWithPointsLessThanAndPosition' from the PlayerService should be displayed.
     *
     * @param pointsPerGame
     * @param position
     * @return The view "list.html".
     */
    @GetMapping
    public String showPlayers( @RequestParam(required=false) Double pointsPerGame,
                               @RequestParam(required=false) PlayerPosition position,
                               Model model ) {
        if (pointsPerGame == null && position == null) {
            model.addAttribute("players",this.playerService.listAllPlayers());
        } else if(position == null){
            model.addAttribute("players",this.playerService.listPlayersWithPointsLessThan(pointsPerGame));
        } else if(pointsPerGame == null){
            model.addAttribute("players",this.playerService.listPlayersWithPosition(position));
        }
        else {
            model.addAttribute("players",this.playerService.listPlayersWithPointsLessThanAndPosition(pointsPerGame, position));
        }
        model.addAttribute("positions",PlayerPosition.values());
        return "list";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/players/add'.
     *
     * @return The view "form.html".
     */
    @GetMapping("/players/add")
    public String showAdd(Model model) {
        model.addAttribute("positions",PlayerPosition.values());
        model.addAttribute("teams",teamService.listAll());
        return "form";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the player that is updated.
     * The method should be mapped on path '/players/[id]/edit'.
     *
     * @return The view "form.html".
     */
    @GetMapping("/players/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("player",this.playerService.findById(id));
        model.addAttribute("positions",PlayerPosition.values());
        model.addAttribute("teams",teamService.listAll());
        return "form";
    }

    /**
     * This method should create a player given the arguments it takes.
     * The method should be mapped on path '/players'.
     * After the player is created, all players should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/players")
    public String create(@RequestParam String name,
                         @RequestParam String bio,
                         @RequestParam Double pointsPerGame,
                         @RequestParam PlayerPosition position,
                         @RequestParam Long team) {
        this.playerService.create(name, bio, pointsPerGame, position, team);
        return "redirect:/players";
    }

    /**
     * This method should update a player given the arguments it takes.
     * The method should be mapped on path '/players/[id]'.
     * After the player is updated, all players should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/players/{id}")
    public String update(@PathVariable Long id, String name, String bio, Double pointsPerGame, PlayerPosition position, Long team) {
        this.playerService.update(id, name, bio, pointsPerGame, position, team);
        return "redirect:/players";
    }

    /**
     * This method should delete the player that has the appropriate identifier.
     * The method should be mapped on path '/players/[id]/delete'.
     * After the player is deleted, all players should be displayed.
     *
     * @return The view "list.html".
     */
    @PostMapping("/players/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.playerService.delete(id);
        return "redirect:/players";
    }

    /**
     * This method should increase the number of votes of the appropriate player by 1.
     * The method should be mapped on path '/players/[id]/vote'.
     * After the operation, all players should be displayed.
     *
     * @return The view "list.html".
     */
    public String vote(Long id) {
        this.playerService.vote(id);
        return "";
    }
}

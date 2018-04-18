package pl.wojtacka.agata.angular2springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.wojtacka.agata.angular2springboot.model.Hero;
import pl.wojtacka.agata.angular2springboot.service.HeroesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HeroesController {

    private final HeroesService heroesService;

    @RequestMapping("/heroes")
    public List<Hero> heroes() {
        return heroesService.getHeroes();
    }

    @RequestMapping("/heroes/{id}")
    public Hero hero(@PathVariable  final int id) {
        return heroesService.getHero(id);
    }

    @RequestMapping(value = "/heroes", method = RequestMethod.PUT)
    public Hero updateHero(@RequestBody final Hero hero) {
        return heroesService.updateHero(hero);
    }

    @RequestMapping(value = "/heroes", method = RequestMethod.POST)
    public Hero addHero(@RequestBody final Hero hero){
        return heroesService.addHero(hero);
    }

    @RequestMapping(value = "/heroes/{id}", method = RequestMethod.DELETE)
    public Hero deleteHero(@PathVariable final int id) {
        return heroesService.deleteHero(id);
    }

    @RequestMapping("/heroes/name/{term}")
    public List<Hero> searchHero(@PathVariable final String term) {
        return heroesService.searchHero(term);
    }
}

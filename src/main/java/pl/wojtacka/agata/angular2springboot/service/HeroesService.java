package pl.wojtacka.agata.angular2springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.wojtacka.agata.angular2springboot.model.Hero;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Component
public class HeroesService {

    private final List<Hero> heroes = new ArrayList<>();

    @PostConstruct
    private void init() {
        heroes.add(new Hero(11, "Mr. Nice"));
        heroes.add(new Hero(12, "Narco"));
        heroes.add(new Hero(13, "Bombasto"));
        heroes.add(new Hero(14, "Celeritas"));
        heroes.add(new Hero(15, "Magneta"));
        heroes.add(new Hero(16, "RubberMan"));
        heroes.add(new Hero(17, "Dynama"));
        heroes.add(new Hero(18, "Dr IQ"));
        heroes.add(new Hero(19, "Magma"));
        heroes.add(new Hero(20, "Tornado"));
    }

    public List<Hero> getHeroes() {
        log.debug("Getting heroes");
        return heroes;
    }

    public Hero getHero(final int id) {
        log.debug("Getting hero: " + id);
        return heroes.stream()
            .filter(hero -> hero.getId() == id)
            .findFirst()
            .get();
    }

    public Hero updateHero(final Hero hero) {
        log.debug("Updating hero: {}", hero);
        final Hero originHero = getHero(hero.getId());
        originHero.setName(hero.getName());
        return originHero;
    }

    public Hero addHero(final Hero hero) {
        log.debug("Adding hero: {}", hero);
        hero.setId(generateId());
        heroes.add(hero);
        return hero;
    }

    private int generateId() {
        final int maxId = heroes.stream()
            .mapToInt(Hero::getId)
            .max()
            .getAsInt();
        return maxId + 1;
    }

    public Hero deleteHero(final int id) {
        final Hero hero = getHero(id);
        log.debug("Deleting hero: {}", hero);
        heroes.remove(hero);
        return hero;
    }

    public List<Hero> searchHero(final String term) {
        log.debug("Searching heroes with term: {}", term);
        return heroes.stream()
            .filter(hero -> hero.getName().toLowerCase().contains(term.toLowerCase()))
            .collect(toList());
    }
}

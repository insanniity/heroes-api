package com.insannity.heroes.controller;

import static com.insannity.heroes.constans.HeroesConstant.HEROES_ENDPOINT_LOCAL;

import com.insannity.heroes.document.Heroes;
import com.insannity.heroes.repository.HeroesRepository;
import com.insannity.heroes.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@RestController
@Slf4j
public class HeroesController {

    HeroesService heroesService;
    HeroesRepository heroesRepository;
    //private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger( HeroesController.class );

    public HeroesController(HeroesService heroesService, HeroesRepository heroesRepository) {
        this.heroesService = heroesService;
        this.heroesRepository = heroesRepository;
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Heroes> getAllItems(){
        log.info( "Resquete all heroes" );
        return heroesService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL+"/id")
    public Mono<ResponseEntity<Heroes>> findHeroById(@PathVariable String id){
        log.info( "Request Hero with id {}", id );
        return heroesService.findHeroById( id ).map((item)-> new ResponseEntity<>( item, HttpStatus.OK )).defaultIfEmpty(new ResponseEntity<>( HttpStatus.NOT_FOUND )  );
    }


    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code=HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes){
        log.info( "New hero was created" );
        return heroesService.save(heroes);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL+"/id")
    @ResponseStatus(code=HttpStatus.CONTINUE)
    public Mono<HttpStatus> deleteHeroById(@PathVariable String id){
        heroesService.deleteHeroById(id);
        log.info( "Hero {} has bem deleted", id);
        return Mono.just( HttpStatus.CONTINUE);
    }




}

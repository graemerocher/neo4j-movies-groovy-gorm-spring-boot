package neo4j.movies.controller

import groovy.transform.CompileStatic
import neo4j.movies.domain.CastMember
import neo4j.movies.domain.Movie
import neo4j.movies.services.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CompileStatic
class MovieController {

    final MovieService movieService

    @Autowired
    MovieController(MovieService movieService) {
        this.movieService = movieService
    }

    @RequestMapping("/graph")
    Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
        return movieService.graph(limit == null ? 100 : limit)
    }

    @RequestMapping("/movie/{title}")
    @ResponseBody Map<String, Object> findByTitle(@PathVariable String title) {
        Movie movie = this.movieService.find(title)
        return [
            title: movie.title,
            cast: movie.cast.collect { CastMember cm ->
                [ name: cm.from.name,
                  job: cm.type == CastMember.RoleType.ACTED_IN.name() ? "acted" : cm.type.toLowerCase(),
                  role: cm.roles
                ]
            }
        ]
    }

    @RequestMapping("/search")
    @ResponseBody Iterable<Movie> search(@RequestParam String q, @RequestParam(value = "limit",required = false) Integer limit) {
        return movieService.search(q, limit == null ? 100 : limit)
    }
}
package neo4j.movies

import grails.gorm.transactions.Rollback
import neo4j.movies.domain.Movie
import neo4j.movies.services.MovieService
import org.grails.datastore.gorm.neo4j.Neo4jDatastore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(classes = SampleMovieApplication)
class MovieServiceSpec extends Specification {

    @Autowired
    MovieService service

    @Rollback
    void "Test search movies"() {
        given:
        new Movie(title: "The Matrix", tagline: "Free your mind", released: 1999).save(flush:true)

        expect:"to find results"
        service.search("Matr")
        service.search("The")
        !service.search("The Wrestler")
    }

    @Rollback
    void "Test find movie by title"() {
        given:
        new Movie(title: "The Matrix", tagline: "Free your mind", released: 1999).save(flush:true)

        expect:"The find movies by title"
        service.find("The Matrix")
        !service.find("The Wrestler")
    }
}

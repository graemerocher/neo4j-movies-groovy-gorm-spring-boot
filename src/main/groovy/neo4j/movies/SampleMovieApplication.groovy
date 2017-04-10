package neo4j.movies

import org.grails.datastore.gorm.neo4j.Neo4jDatastore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SampleMovieApplication {

    static void main(String[] args) {
        SpringApplication.run(SampleMovieApplication.class, args)
    }

    @Autowired
    Neo4jDatastore neo4jDatastore

}
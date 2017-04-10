package neo4j.movies.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import grails.gorm.annotation.Entity
import grails.neo4j.Node

/**
 * Models a movie node in the graph database
 */
@Entity
class Movie implements Node<Movie> {
    String title
    String tagline
    int released

    @JsonIgnore
    Set<CastMember> cast

    static hasMany = [cast: CastMember]

    static constraints = {
        released min:1900
        title blank:false
    }
}

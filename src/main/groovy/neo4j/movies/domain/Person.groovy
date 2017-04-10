package neo4j.movies.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import grails.gorm.annotation.Entity
import grails.neo4j.Node

/**
 * Models a Person node in the graph database
 */
@Entity
class Person implements Node<Person> {
    String name
    int born

    @JsonIgnore
    Set<CastMember> appearances

    static constraints = {
        name blank:false
        born min:1900
    }
}

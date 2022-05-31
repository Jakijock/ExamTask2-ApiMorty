package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "characters"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode {

    @JsonProperty("id")
    Integer id;

    @JsonProperty("name")
    String name;

    @JsonProperty("characters")
    List<String> characters;

    public String getName() {
        return name;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", characters=" + characters +
                '}';
    }
}

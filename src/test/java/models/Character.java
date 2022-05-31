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
        "status",
        "species",
        "gender",
        "location",
        "episode",
        "url"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {
    @JsonProperty("id")
    Integer id;

    @JsonProperty("name")
    String name;

    @JsonProperty("status")
    String status;

    @JsonProperty("species")
    String species;

    @JsonProperty("gender")
    String gender;

    @JsonProperty("location")
    Location location;

    @JsonProperty("episode")
    List<String> episode;

    @JsonProperty("url")
    String url;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getGender() {
        return gender;
    }

    public Location getLocation() {
        return location;
    }

    public List<String> getEpisode() {
        return episode;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", species='" + species + '\'' +
                ", gender='" + gender + '\'' +
                ", location=" + location +
                ", episode=" + episode +
                '}';
    }
}



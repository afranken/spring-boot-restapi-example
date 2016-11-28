package com.github.afranken.boot.restapi.connector;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalResponse {

  @JsonProperty("name")
  private String name;

  @JsonProperty("watchers")
  private Long watchers;

  @JsonProperty("owner")
  private Owner owner;

  ExternalResponse() {
    //needed for Jackson
  }

  ExternalResponse(String name, Owner owner) {
    this.name = name;
    this.owner = owner;
  }

  public String getName() {
    return name;
  }

  public Long getWatchers() {
    return watchers;
  }

  public Owner getOwner() {
    return owner;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ExternalResponse that = (ExternalResponse) o;

    if (!name.equals(that.name)) {
      return false;
    }
    return owner.equals(that.owner);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + owner.hashCode();
    return result;
  }

  public static class Owner {

    @JsonProperty("login")
    private String login;

    Owner() {
      //needed for Jackson
    }

    public Owner(String login) {
      this.login = login;
    }

    public String getLogin() {
      return login;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Owner owner = (Owner) o;

      return login.equals(owner.login);
    }

    @Override
    public int hashCode() {
      return login.hashCode();
    }
  }
}

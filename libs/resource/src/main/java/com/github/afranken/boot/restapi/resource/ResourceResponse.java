package com.github.afranken.boot.restapi.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceResponse
{
  @JsonProperty
  private String name;

  @JsonProperty
  private String owner;

  @JsonProperty
  private Long watchers;

  ResourceResponse() {
    //needed for Jackson
  }

  public ResourceResponse(String name, String owner, Long watchers) {
    this.name = name;
    this.owner = owner;
    this.watchers = watchers;
  }

  public String getName() {
    return name;
  }

  public String getOwner() {
    return owner;
  }

  public Long getWatchers() {
    return watchers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ResourceResponse that = (ResourceResponse) o;

    if (!name.equals(that.name)) {
      return false;
    }
    if (!owner.equals(that.owner)) {
      return false;
    }
    return watchers.equals(that.watchers);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + owner.hashCode();
    result = 31 * result + watchers.hashCode();
    return result;
  }
}

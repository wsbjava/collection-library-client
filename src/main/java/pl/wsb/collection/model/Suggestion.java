/*
 * collection-library-server-API
 * API for collection-library-server
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package pl.wsb.collection.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

/**
 * Suggestion
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class Suggestion   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("item_to_add")
  private ItemRequest itemToAdd = null;

  public Suggestion id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Id of the suggestion
   * @return id
   **/
  @JsonProperty("id")
  @ApiModelProperty(required = true, value = "Id of the suggestion")
  @NotNull
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Suggestion itemToAdd(ItemRequest itemToAdd) {
    this.itemToAdd = itemToAdd;
    return this;
  }

  /**
   * Data of item
   * @return itemToAdd
   **/
  @JsonProperty("item_to_add")
  @ApiModelProperty(required = true, value = "Data of item")
  @NotNull
  public ItemRequest getItemToAdd() {
    return itemToAdd;
  }

  public void setItemToAdd(ItemRequest itemToAdd) {
    this.itemToAdd = itemToAdd;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Suggestion suggestion = (Suggestion) o;
    return Objects.equals(this.id, suggestion.id) &&
        Objects.equals(this.itemToAdd, suggestion.itemToAdd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, itemToAdd);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Suggestion {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    itemToAdd: ").append(toIndentedString(itemToAdd)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


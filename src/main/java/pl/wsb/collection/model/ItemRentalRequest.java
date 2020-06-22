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
 * ItemRentalRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class ItemRentalRequest   {
  @JsonProperty("item_id")
  private Integer itemId = null;

  @JsonProperty("duration")
  private Integer duration = null;

  public ItemRentalRequest itemId(Integer itemId) {
    this.itemId = itemId;
    return this;
  }

  /**
   * Item to rent id
   * @return itemId
   **/
  @JsonProperty("item_id")
  @ApiModelProperty(required = true, value = "Item to rent id")
  @NotNull
  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  public ItemRentalRequest duration(Integer duration) {
    this.duration = duration;
    return this;
  }

  /**
   * Number of days for which item is rented
   * @return duration
   **/
  @JsonProperty("duration")
  @ApiModelProperty(required = true, value = "Number of days for which item is rented")
  @NotNull
  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemRentalRequest itemRentalRequest = (ItemRentalRequest) o;
    return Objects.equals(this.itemId, itemRentalRequest.itemId) &&
        Objects.equals(this.duration, itemRentalRequest.duration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId, duration);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemRentalRequest {\n");
    
    sb.append("    itemId: ").append(toIndentedString(itemId)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
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


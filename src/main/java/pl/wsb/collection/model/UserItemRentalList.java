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

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * UserItemRentalList
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class UserItemRentalList   {
  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("data")
  private List<UserItemRental> data = new ArrayList<UserItemRental>();

  public UserItemRentalList total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * Size of the data
   * @return total
   **/
  @JsonProperty("total")
  @ApiModelProperty(required = true, value = "Size of the data")
  @NotNull
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public UserItemRentalList data(List<UserItemRental> data) {
    this.data = data;
    return this;
  }

  public UserItemRentalList addDataItem(UserItemRental dataItem) {
    this.data.add(dataItem);
    return this;
  }

  /**
   * One pagination page data
   * @return data
   **/
  @JsonProperty("data")
  @ApiModelProperty(required = true, value = "One pagination page data")
  @NotNull
  public List<UserItemRental> getData() {
    return data;
  }

  public void setData(List<UserItemRental> data) {
    this.data = data;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserItemRentalList userItemRentalList = (UserItemRentalList) o;
    return Objects.equals(this.total, userItemRentalList.total) &&
        Objects.equals(this.data, userItemRentalList.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, data);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserItemRentalList {\n");
    
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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


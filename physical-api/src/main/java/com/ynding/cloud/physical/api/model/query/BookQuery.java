package com.ynding.cloud.physical.api.model.query;

import com.ynding.cloud.common.model.bo.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p> </p>
 *
 * @author ynding
 * @version 2021/7/21
 **/
@Data
@ApiModel("book查询类")
public class BookQuery extends Query {

    @ApiModelProperty(name = "reader", notes = "读者", dataType = "string")
    private String reader;

    @ApiModelProperty(name = "isbn", notes = "isbn", dataType = "string")
    private String isbn;

    @ApiModelProperty(name = "title", notes = "标题", dataType = "string")
    private String title;

    @ApiModelProperty(name = "author", notes = "作者", dataType = "string")
    private String author;

    @ApiModelProperty(name = "description", notes = "描述", dataType = "string")
    private String description;
}

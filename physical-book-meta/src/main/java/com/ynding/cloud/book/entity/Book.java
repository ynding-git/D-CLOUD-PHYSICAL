package com.ynding.cloud.book.entity;

import org.springframework.data.rest.core.annotation.RestResource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ynding
 * @version 2019/06/14
 *
 */
@Data
@Entity
@ApiModel(value = "Book", description = "书籍")
@RestResource(rel = "books", path = "books")
@Table(name = "t_book")
public class Book implements Serializable {

	private static final long serialVersionUID = -8899520163983685604L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(name = "id", notes = "ID", dataType = "long")
	private Long id;

	@ApiModelProperty(name = "reader", notes = "读者", dataType = "string")
	@Column(name = "reader")
	private String reader;

	@ApiModelProperty(name = "isbn", notes = "isbn", dataType = "string")
    @Column(name = "isbn")
	private String isbn;

	@ApiModelProperty(name = "title", notes = "标题", dataType = "string")
    @Column(name = "title")
	private String title;

	@ApiModelProperty(name = "author", notes = "作者", dataType = "string")
    @Column(name = "author")
	private String author;

	@ApiModelProperty(name = "description", notes = "描述", dataType = "string")
    @Column(name = "description")
	private String description;

}

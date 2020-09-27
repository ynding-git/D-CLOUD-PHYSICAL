package com.ynding.cloud.person.entity;

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
import lombok.NoArgsConstructor;

@Table
@Data
@Entity
@ApiModel(value = "Person", description = "人员")
@NoArgsConstructor
public class Person implements Serializable {
    private static final long serialVersionUID = 6062342895943248978L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "ID", dataType = "long")
    private Long id;

    @ApiModelProperty(name = "name", notes = "姓名", dataType = "string")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(name = "sex", notes = "性别", dataType = "string")
    @Column(name = "sex")
    private String sex;
}

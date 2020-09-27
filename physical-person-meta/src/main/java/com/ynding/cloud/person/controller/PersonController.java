package com.ynding.cloud.person.controller;

import com.ynding.cloud.common.model.bo.Query;
import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.common.model.vo.PersonVO;
import com.ynding.cloud.person.service.PersonService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ynding
 */
@RestController
@RequestMapping("/person")
@Slf4j
@Api(value = "PersonController", tags = "person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加人员", produces = "application/json")
    public ResponseBean save(@RequestBody PersonVO person) {
        personService.save(person);
        return ResponseBean.ok(1);
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询人员", produces = "application/json")
    public ResponseBean getList(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return ResponseBean.ok(personService.findList(query));
    }
}

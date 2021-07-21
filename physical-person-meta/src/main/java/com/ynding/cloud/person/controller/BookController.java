package com.ynding.cloud.person.controller;

import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.common.model.vo.BookVO;
import com.ynding.cloud.physical.api.client.book.BookClient;
import com.ynding.cloud.physical.api.model.query.BookQuery;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author dyn
 * @version 2019/12/12
 */
@RestController
@RequestMapping("person")
@ComponentScan(basePackages = "com.ynding.cloud.physical.api.client.book")
@Api(value = "BookController",tags = "book")
public class BookController {

    private final BookClient bookClient;
    public BookController(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    @GetMapping("/get/book")
    @ApiOperation(value = "查询书本", produces = "application/json")
    public ResponseBean getBooks(BookQuery params) {

        return bookClient.findList(params);
    }

    @PostMapping("/add/book")
    @ApiOperation(value = "添加书本", produces = "application/json")
    @GlobalTransactional(rollbackFor = Exception.class)
    public ResponseBean saveBook(@RequestBody BookVO book) {
        ResponseBean responseBean = bookClient.saveBook(book);

        double d = 1/0;

        return ResponseBean.ok();
    }
}

package com.ynding.cloud.book.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ynding.cloud.book.service.BookService;
import com.ynding.cloud.common.model.bo.Query;
import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.common.model.bo.ResponsePageBean;
import com.ynding.cloud.common.model.vo.BookVO;

import com.ynding.cloud.physical.api.model.query.BookQuery;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ynding
 */
@Slf4j
@RestController
@RequestMapping("/book")
@Api(value = "Book", tags = {"Book-Controller"})
@CrossOrigin(origins = "*")
public class BookController {

    private final BookService bookService;
    private final FastFileStorageClient fastFileStorageClient;
    @Value("${imageUrl.prefix}")
    private String imageUrl;

    public BookController(BookService bookService, FastFileStorageClient fastFileStorageClient) {
        this.bookService = Objects.requireNonNull(bookService);
        this.fastFileStorageClient = fastFileStorageClient;
    }

    @PostMapping("/insert")
    @ApiOperation(value = "添加书本", produces = "application/json")
    public ResponseBean insert(@RequestBody BookVO bookVO) {

        bookService.save(bookVO);
        return ResponseBean.ok(1);
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询列表", produces = "application/json")
    public ResponseBean findList(BookQuery params) {

        Query query = new Query(params);
        List<BookVO> books = bookService.findList(query);

        return ResponseBean.ok(books);
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页大小", required = true, dataType = "Integer")
    })
    public ResponsePageBean findPage(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return bookService.pageList(query);
    }

    @PostMapping("/picture")
    @ApiOperation(value = "图片上传", produces = "application/json")
    public ResponseBean picture(@RequestParam("file") MultipartFile file) {
        try {
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            String filepath = imageUrl + storePath.getFullPath();
            return ResponseBean.ok(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseBean.fail("上传失败", 400);
    }
}

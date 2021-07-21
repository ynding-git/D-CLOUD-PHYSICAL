package com.ynding.cloud.physical.api.client.book;

import com.ynding.cloud.common.model.bo.CloudServiceInfo;
import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.common.model.vo.BookVO;
import com.ynding.cloud.physical.api.client.book.fallback.BookClientImpl;
import com.ynding.cloud.physical.api.model.query.BookQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 调用book服务的接口
 * @author Administrator
 */
@FeignClient(value = CloudServiceInfo.PHYSICAL_BOOK_META,fallback = BookClientImpl.class)
@Component
public interface BookClient {

    /**
     * @param params
     * @return
     */
    @GetMapping("/book/list")
    ResponseBean findList(BookQuery params);

    /**
     * @param book
     * @return
     */
    @PostMapping("/book/insert")
    ResponseBean saveBook(@RequestBody BookVO book);
}

package com.ynding.cloud.physical.api.client.book.fallback;

import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.common.model.vo.BookVO;
import com.ynding.cloud.physical.api.client.book.BookClient;
import com.ynding.cloud.physical.api.model.query.BookQuery;
import org.springframework.stereotype.Component;

import static com.ynding.cloud.common.model.bo.ResponseCode.BOOK_FEIGN_ERROR;

/**
 * 断路器
 * @author dyn
 * @version 2019/12/12
 */
@Component
public class BookClientImpl implements BookClient {

    @Override
    public ResponseBean findList(BookQuery params) {
        return ResponseBean.fail(BOOK_FEIGN_ERROR);
    }

    @Override
    public ResponseBean saveBook(BookVO book) {
        return ResponseBean.fail(BOOK_FEIGN_ERROR);
    }
}

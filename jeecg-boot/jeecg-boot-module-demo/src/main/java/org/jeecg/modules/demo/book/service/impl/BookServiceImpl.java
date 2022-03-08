package org.jeecg.modules.demo.book.service.impl;

import org.jeecg.modules.demo.book.entity.Book;
import org.jeecg.modules.demo.book.mapper.BookMapper;
import org.jeecg.modules.demo.book.service.IBookService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 图书表
 * @Author: 王佳伟
 * @Date:   2022-03-08
 * @Version: V1.0
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}

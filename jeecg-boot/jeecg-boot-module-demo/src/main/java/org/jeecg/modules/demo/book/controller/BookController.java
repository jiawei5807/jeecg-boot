package org.jeecg.modules.demo.book.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.book.entity.Book;
import org.jeecg.modules.demo.book.service.IBookService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 图书表
 * @Author: 王佳伟
 * @Date:   2022-03-08
 * @Version: V1.0
 */
@Api(tags="图书表")
@RestController
@RequestMapping("/book/book")
@Slf4j
public class BookController extends JeecgController<Book, IBookService> {

	private final IBookService bookService;
	 @Autowired
	public BookController(IBookService bookService){
		this.bookService = bookService;
	}
	
	/**
	 * 分页列表查询
	 *
	 * @param book
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@ApiOperation(value="图书表-分页列表查询", notes="图书表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Book>> queryPageList(Book book,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Book> queryWrapper = QueryGenerator.initQueryWrapper(book, req.getParameterMap());
		Page<Book> page = new Page<>(pageNo, pageSize);
		IPage<Book> pageList = bookService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param book
	 * @return
	 */
	@AutoLog(value = "图书表-添加")
	@ApiOperation(value="图书表-添加", notes="图书表-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Book book) {
		bookService.save(book);
		return Result.OK("添加成功！",null);
	}
	
	/**
	 *  编辑
	 *
	 * @param book
	 * @return
	 */
	@AutoLog(value = "图书表-编辑")
	@ApiOperation(value="图书表-编辑", notes="图书表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Book book) {
		bookService.updateById(book);
		return Result.OK("编辑成功!",null);
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "图书表-通过id删除")
	@ApiOperation(value="图书表-通过id删除", notes="图书表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id") String id) {
		bookService.removeById(id);
		return Result.OK("删除成功!",null);
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "图书表-批量删除")
	@ApiOperation(value="图书表-批量删除", notes="图书表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids") String ids) {
		this.bookService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!",null);
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="图书表-通过id查询", notes="图书表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Book> queryById(@RequestParam(name="id") String id) {
		Book book = bookService.getById(id);
		if(book==null) {
			return Result.error("未找到对应数据",null);
		}
		return Result.OK(book);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param book
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Book book) {
        return super.exportXls(request, book, Book.class, "图书表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Book.class);
    }

}

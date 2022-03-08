package org.jeecg.modules.demo.book.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 图书表
 * @Author: 王佳伟
 * @Date:   2022-03-08
 * @Version: V1.0
 */
@Data
@TableName("test_book")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="test_book对象", description="图书表")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**图书名称*/
	@Excel(name = "图书名称", width = 15)
    @ApiModelProperty(value = "图书名称")
    private java.lang.String bookName;
	/**图书作者*/
	@Excel(name = "图书作者", width = 15)
    @ApiModelProperty(value = "图书作者")
    private java.lang.String bookAuthor;
	/**出版社*/
	@Excel(name = "出版社", width = 15)
    @ApiModelProperty(value = "出版社")
    private java.lang.String bookPress;
	/**图书定价*/
	@Excel(name = "图书定价", width = 15)
    @ApiModelProperty(value = "图书定价")
    private java.lang.Double bookPrice;
	/**图书库存*/
	@Excel(name = "图书库存", width = 15)
    @ApiModelProperty(value = "图书库存")
    private java.lang.Integer bookStock;
	/**图书信息*/
	@Excel(name = "图书信息", width = 15)
    @ApiModelProperty(value = "图书信息")
    private java.lang.String bookInfo;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
}

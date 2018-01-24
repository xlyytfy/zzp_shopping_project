package com.taotao.search.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Item;
import com.taotao.search.service.SearchService;

@Controller
@RequestMapping("search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@Value("${TAOTAO_SEARCH_ITEM_ROWS}")
	private Integer rows;
	
	@RequestMapping(method = RequestMethod.GET)
	public String searchItems(Model model,@RequestParam(value="q") String query,@RequestParam(value="page",defaultValue="1")Integer page) throws Exception{
		//解决get乱码
		try {
			query = new String(query.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		TaoResult<Item> taoResult = searchService.searchItems(page,rows,query);
		
		// 把数据放到模型中，页面展示
		// 搜索关键词
		model.addAttribute("query", query);

		// 搜索结果集list
		model.addAttribute("itemList", taoResult.getRows());

		// 当前页码数
		model.addAttribute("page", page);

		// 总页数,查询到的数据总数和每页显示条数进行计算
		long total = taoResult.getTotal();
		// long pages = total % 16 == 0 ? total / 16 : (total / 16) + 1;
		long pages = (total + this.rows - 1) / this.rows;
		model.addAttribute("totalPages", pages);
		return "search";
	}
}

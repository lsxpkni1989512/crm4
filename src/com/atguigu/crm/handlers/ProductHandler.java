package com.atguigu.crm.handlers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ProductService;

@RequestMapping("/product")
@Controller
public class ProductHandler {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	public String update(Product pro,@PathVariable(value="id")Long id){
		
		pro.setId(id);
		
		productService.update(pro);
		
		return "redirect:/product/list";
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(@RequestParam(value="id",required=false)Long id,Map<String,Object> map){
		
		Product product = productService.getProductById(id);
		
		map.put("product", product);
		
		return "pages/product/update";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id")Long id){
		
		productService.delete(id);
		
		return "1";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Product produce){
		
		productService.addProduct(produce);
		
		return "redirect:/product/list";
	}
	
	@RequestMapping("/toAddProduct")
	public String toAddProduct(){
		return "pages/product/add";
	}
	
	@RequestMapping("/list")
	public String toList(@RequestParam(value="pageNo",required=false) String pageNoStr,
			Map<String, Object> map,
						HttpSession session,HttpServletRequest request){
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String queryString = parseRequestParams2QueryString(params);
		map.put("queryString", queryString);
		
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		
		
		Page<Product> page = productService.getPage(pageNo, params);
		map.put("page", page);
		
		
		return "pages/product/list";
	}
	private String parseRequestParams2QueryString(Map<String, Object> params) {
		StringBuilder queryString = new StringBuilder();
		
		if(params == null || params.size() == 0){
			return "";
		}
		
		for(Map.Entry<String, Object> entry: params.entrySet()){
			if(entry.getValue() == null || entry.getValue().toString().trim().equals("")){
				continue;
			}
			
			queryString.append("search_")
			           .append(entry.getKey())
			           .append("=")
			           .append(entry.getValue())
			           .append("&");
		}
		
		if(queryString.length() > 0){
			queryString.replace(queryString.length() - 1, queryString.length(), "");
		}
		return queryString.toString();
	}
}

package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.cart.service.CartService;
import com.taotao.common.util.CookieUtils;
import com.taotao.manager.pojo.Cart;
import com.taotao.manager.pojo.User;
import com.taotao.sso.service.UserService;

@Controller
@RequestMapping("cart")
public class CartController {

	@Value("${TT_TICKET}")
	private String TT_TICKET;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value = "{itemId}",method = RequestMethod.GET)
	public String addItemCart(@PathVariable Long itemId,Integer num,HttpServletRequest request) throws Exception{
		//判断当前用户是否登录
		//获取ticket
		String ticket = CookieUtils.getCookieValue(request,TT_TICKET);
		//根据ticket获取用户登录信息
		User user = userService.querUserByTicket(ticket);
		//获取订单
		if(user != null){
			//用户已经登录
			//当前订单加入购物车
			cartService.addItem(itemId,user.getId(),num);
		}else{
			//用户未登录获取cookie中数据
		}
		//订单加入到购物车
		
		return "redirect:/cart/show.html";
	}
	
	@RequestMapping(value = "show",method = RequestMethod.GET)
	public String showItemCart(Model model,HttpServletRequest request) throws Exception{
		
		//判断当前用户是否登录
		//获取ticket
		String ticket = CookieUtils.getCookieValue(request,TT_TICKET);
		//根据ticket获取用户登录信息
		User user = userService.querUserByTicket(ticket);
		List<Cart> cartList = null;
		if(user != null){
			//当前用户已经登录
			cartList = cartService.queryCartByUserId(user.getId());
		}else{
			
		}
		// 保存购物车到模型中
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	
	@RequestMapping(value = "/update/{itemId}/{num}" ,method = RequestMethod.POST)
	@ResponseBody
	public void updateCartByItemId(){
		
	}
}

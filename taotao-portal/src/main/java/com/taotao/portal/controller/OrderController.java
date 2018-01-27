package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.cart.service.CartService;
import com.taotao.manager.pojo.Cart;
import com.taotao.manager.pojo.User;
import com.taotao.portal.utils.CookieUtils;
import com.taotao.sso.service.UserService;

@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@Value("${TT_TICKET}")
	private String TT_TICKET;
	
	/**
	 * 跳转到订单结算页
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(HttpServletRequest request, Model model) throws Exception{
	
		/*String ticket = CookieUtils.getCookieValue(request,this.TT_TICKET, true);
		//根据令牌查询当前用户
		User user = userService.querUserByTicket(ticket);*/
		
		//通过拦截器传递用户信息
		User user = (User)request.getAttribute("user");
		
		//如果用户不为空
		if(user != null){
			List<Cart> carts = cartService.queryCartByUserId(user.getId());
			//根据userId创建订单信息
			model.addAttribute("carts", carts);
		}
		
		//返回订单页面
		return "order-cart";
	}

}

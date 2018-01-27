package com.taotao.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.taotao.portal.service.CartCookieService;
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
	
	@Autowired
	private CartCookieService cartCookieService;

	/**
	 * 添加商品到购物车
	 * 
	 * @param itemId
	 * @param num
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "{itemId}", method = RequestMethod.GET)
	public String addItemCart(@PathVariable Long itemId, Integer num, HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 获取当前登录用户
		User user = queryUserLogin(request);
		// 获取订单
		if (user != null) {
			// 用户已经登录
			// 当前订单加入购物车
			cartService.addCart(itemId, user.getId(), num);
		} else {
			Map<String, Object> param = new HashMap<>();
			param.put("request", request);
			param.put("response", response);
			// 用户未登录
			/*this.cartService.addCookieCart(param,itemId, num);*/
			cartCookieService.addItemByCookie(itemId, num, request, response);
		}
		// 订单加入到购物车

		return "redirect:/cart/show.html";
	}

	/**
	 * 展示购物车
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String showItemCart(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 获取当前登录用户
		User user = queryUserLogin(request);
		List<Cart> cartList = null;
		if (user != null) {
			// 当前用户已经登录
			cartList = cartService.queryCartByUserId(user.getId());
		} else {
			Map<String, Object> param = new HashMap<>();
			param.put("request", request);
			// 用户未登录获取cookie中数据
			/*cartList = cartService.queryCookieCartByUserId(param);*/
			cartList = cartCookieService.queryCartByCookie(request);
		}
		// 保存购物车到模型中
		model.addAttribute("cartList", cartList);
		return "cart";
	}

	/**
	 * 获取商品id更新购物车商品数量
	 * 
	 * @param itemId
	 * @param num
	 * @throws Exception 
	 */
	@RequestMapping(value = "/update/{itemId}/{num}", method = RequestMethod.POST)
	@ResponseBody
	public void updateCartByItemId(HttpServletRequest request,@PathVariable Long itemId, @PathVariable Integer num) throws Exception {
		// 获取当前登录用户
		User user = queryUserLogin(request);
		
		if(user != null){
			// 用户已经登录
			// 修改商品数量
			cartService.updateCart(itemId, user.getId(), num);
		}else{
			//用户还未登录
		}
	}

	/**
	 * 获取当前登录用户,如果未登录反回null
	 * 
	 * @param request
	 * @return
	 */
	public User queryUserLogin(HttpServletRequest request) throws Exception {
		// 获取ticket
		String ticket = CookieUtils.getCookieValue(request, TT_TICKET);
		// 根据ticket获取用户登录信息
		User user = userService.querUserByTicket(ticket);
		return user;
	}
	
	/**
	 * 删除购物车的商品
	 * 
	 * @param itemId
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "delete/{itemId}", method = RequestMethod.GET)
	public String deleteItemByCart(@PathVariable("itemId") Long itemId, HttpServletRequest request) throws Exception {
		// 获取当前登录用户
		User user = queryUserLogin(request);

		// 判断用户是否登录
		if (user != null) {
			// 用户已登录
			this.cartService.deleteItemInCart(user.getId(), itemId);
		} else {
			// 用户未登录

		}

		return "redirect:/cart/show.html";
	}
}

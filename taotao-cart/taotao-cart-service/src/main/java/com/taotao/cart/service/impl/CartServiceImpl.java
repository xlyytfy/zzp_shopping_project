package com.taotao.cart.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.cart.service.CartService;
import com.taotao.cart.service.redis.RedisUtils;
import com.taotao.manager.mapper.ItemMapper;
import com.taotao.manager.pojo.Cart;
import com.taotao.manager.pojo.Item;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ItemMapper itemMapper;

	@Value("${TAOTAO_ITEM_CART}")
	private String TAOTAO_ITEM_CART;

	@Autowired
	private RedisUtils redisUtils;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public void addCart(Long itemId, Long userId, Integer num) throws Exception {
		Item item = itemMapper.selectByPrimaryKey(itemId);
		String cartJson = redisUtils.get(TAOTAO_ITEM_CART + userId);
		List<Cart> list = new ArrayList<>();
		if (StringUtils.isNotBlank(cartJson)) {
			// 如果购物车已经创建，获取购物车
			list = MAPPER.readValue(cartJson, MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
			boolean exist = false;
			// 变量购物车查询当前商品是否存在于购物车
			for (Cart cart : list) {
				// 商品存在当前购物车中
				if (cart.getItemId().longValue() == itemId) {
					// 数量累加
					cart.setNum(cart.getNum() + num);
					exist = true;
					break;
				}
			}
			if (!exist) {
				addCart(list, userId, itemId, item, num);
			}
		} else {
			// 如果购物车还未创建
			// 添加购物车
			addCart(list, userId, itemId, item, num);
		}
		// 添加到redis
		redisUtils.set(TAOTAO_ITEM_CART + userId, MAPPER.writeValueAsString(list));
	}

	public void addCart(List<Cart> list, Long userId, Long itemId, Item item, Integer num) {
		// 如果当前不存在商品或者list为空
		// 创建购物车数据
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cart.setItemTitle(item.getTitle());
		cart.setItemPrice(item.getPrice());
		cart.setItemImage(item.getImages()[0]);
		cart.setNum(num);
		cart.setCreated(new Date());
		cart.setUpdated(cart.getCreated());
		// 添加购物车
		list.add(cart);
	}

	@Override
	public List<Cart> queryCartByUserId(Long userId) throws Exception {
		String cartJson = redisUtils.get(TAOTAO_ITEM_CART + userId);
		List<Cart> list = new ArrayList<>();
		if (StringUtils.isNotBlank(cartJson)) {
			list = MAPPER.readValue(cartJson, MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
		}
		return list;
	}

	@Override
	public void updateCart(Long itemId, Long userId, Integer num) throws Exception {
		String cartJson = redisUtils.get(TAOTAO_ITEM_CART + userId);
		List<Cart> list = new ArrayList<>();
		if (StringUtils.isNotBlank(cartJson)) {
			// 如果购物车已经创建，获取购物车
			list = MAPPER.readValue(cartJson, MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
			// 变量购物车查询当前商品是否存在于购物车
			for (Cart cart : list) {
				// 商品存在当前购物车中
				if (cart.getItemId().longValue() == itemId) {
					// 数量更新
					cart.setNum(num);
					// 更新到redis
					redisUtils.set(TAOTAO_ITEM_CART + userId, MAPPER.writeValueAsString(list));
					break;
				}
			}
		}
	}

	@Override
	public void deleteItemInCart(Long itemId, Long userId) throws Exception {
		String cartJson = redisUtils.get(TAOTAO_ITEM_CART + userId);
		List<Cart> list = new ArrayList<>();
		if (StringUtils.isNotBlank(cartJson)) {
			// 如果购物车已经创建，获取购物车
			list = MAPPER.readValue(cartJson, MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
			// 变量购物车查询当前商品是否存在于购物车
			for (Cart cart : list) {
				// 商品存在当前购物车中
				if (cart.getItemId().longValue() == itemId) {
					//移除商品
					list.remove(cart);
					//重新设置购物车内容
					redisUtils.set(TAOTAO_ITEM_CART + userId, MAPPER.writeValueAsString(list));
					break;
				}
			}
		}
	}

}

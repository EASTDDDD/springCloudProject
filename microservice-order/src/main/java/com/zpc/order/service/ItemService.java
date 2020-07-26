package com.zpc.order.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.zpc.order.entity.Item;
import com.zpc.order.feign.ItemFeignClient;
import com.zpc.order.properties.OrderProperties;



@Service
public class ItemService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 配置文件注入方式一
	 */
	/*@Value("${myspcloud.item.url}")
	private String itemUrl;*/
	
	@Autowired
	private OrderProperties orderProperties;
	
	
	@Autowired
	private ItemFeignClient itemFeignClient;
	
//	public Item queryItemById(Long id) {
//		return restTemplate.getForObject(orderProperties.getItem().getUrl() + id, Item.class);
//	}
	
	
	/**
	 * 进行容错处理
	 * fallbackMethod的方法参数个数类型要和原方法一致
	 *
	 * @param id
	 * @return
	 */
	public Item queryItemById(Long id) {
		// 该方法走eureka注册中心调用(去注册中心根据app-item查找服务，这种方式必须先开启负载均衡@LoadBalanced)
		String itemUrl = "http://app-item/item/{id}";
		Item result = restTemplate.getForObject(itemUrl, Item.class, id);
		System.out.println("订单系统调用商品服务,result:"+result);
		return result;
		
	}
	
	@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
	public Item queryItemById3(Long id) {
		// 该方法走eureka注册中心调用(去注册中心根据app-item查找服务，这种方式必须先开启负载均衡@LoadBalanced)
		String itemUrl = "http://app-item/item/{id}";
		Item result = restTemplate.getForObject(itemUrl, Item.class, id);
		System.out.println("===========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
	    return result;
	}
	
	@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
	public Item queryItemById4(Long id) {
		// 该方法走eureka注册中心调用(去注册中心根据app-item查找服务，这种方式必须先开启负载均衡@LoadBalanced)
		Item result = itemFeignClient.queryItemById(id);
		System.out.println("===========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
	    return result;
	}
	
	public Item queryItemById5(Long id) {
		// 该方法走eureka注册中心调用(去注册中心根据app-item查找服务，这种方式必须先开启负载均衡@LoadBalanced)
		Item result = itemFeignClient.queryItemById(id);
		System.out.println("===========HystrixCommand queryItemById-线程池名称：" + Thread.currentThread().getName() + "订单系统调用商品服务,result:" + result);
	    return result;
	}
	/**
	 * 请求失败执行的方法
	 * fallbackMethod的方法参数个数类型要和原方法一致
	 *
	 * @param id
	 * @return
	 */
	public Item queryItemByIdFallbackMethod(Long id) {
		return new Item(id, "查询商品信息出错!", null, null, null);	
	}
}

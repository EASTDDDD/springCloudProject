package com.zpc.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zpc.item.config.JdbcConfigBean;
import com.zpc.item.entity.Item;
import com.zpc.item.service.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	private ItemService ieItemService;
	
	@Autowired
	private JdbcConfigBean jdbcConfigBean;
	
	@GetMapping(value = "item/{id}")
	public Item queryItemById(@PathVariable("id") Long id) {
		return ieItemService.queryItemById(id);
	}

	@GetMapping("getconfig")
	public String testconfig() {
		return this.jdbcConfigBean.toString();
	}

}

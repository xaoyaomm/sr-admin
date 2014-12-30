package com.store.api.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@Scope("prototype")
@RequestMapping("/product")
public class OrderController extends BaseAction {

}

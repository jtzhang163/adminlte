package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    //未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() {
//        ModelAndView mv = new ModelAndView();
//
//        List<Orders> ordersList = ordersService.findAll();
//        mv.addObject("ordersList", ordersList);
//        mv.setViewName("orders-list");
//        return mv;
//    }

    //分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true,defaultValue = "1") int page,
                                @RequestParam(name = "pageSize", required = true,defaultValue = "5") int pageSize) {
        ModelAndView mv = new ModelAndView();

        List<Orders> ordersList = ordersService.findAll(page, pageSize);
        PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);

        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }


    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) {
        ModelAndView mv = new ModelAndView();

        Orders orders = ordersService.findById(id);

        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
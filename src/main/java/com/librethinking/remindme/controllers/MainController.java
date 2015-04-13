/*
 * Copyright 2015 Christian Vielma <cvielma@librethinking.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.librethinking.remindme.controllers;

import com.librethinking.remindme.beans.Response;
import com.librethinking.remindme.beans.ToDoItem;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * In charge of the main interactions
 * @author Christian Vielma <cvielma@librethinking.com>
 */
@Controller
public class MainController {
    private static final Logger logger = Logger.getLogger(MainController.class);
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "My controller is working";
    }
    
    @RequestMapping(value = "/still", method = RequestMethod.GET)
    public String still() {
        
        return "main";
    }
    
    /**
     *
     * @param todoItem
     * @return
     */
    @RequestMapping(value = "/list/create")
    public @ResponseBody Response create(@RequestBody ToDoItem todoItem) {
        Response res = new Response();
        res.setStatus("ERROR");
        if (todoItem != null && !StringUtils.isBlank(todoItem.getName())) {
            res.setStatus("OK");
        }
        return res;
    }
    
    @RequestMapping(value = "/list/refresh", method = RequestMethod.POST)
    public @ResponseBody List<ToDoItem> refresh() {
        List<ToDoItem> list = dummyList();
        return list;
    }
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() throws IOException {
        ModelAndView mav = new ModelAndView();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        List<ToDoItem> list = dummyList();
        mav.addObject("todolist", list);
        mav.addObject("todolistjson", ow.writeValueAsString(list));
        mav.setViewName("list");
        logger.info("test");
        return mav;
    }
    
    private List<ToDoItem> dummyList() {
        List<ToDoItem> list = new LinkedList<>();
        Date today = new Date();
        list.add(new ToDoItem(1, "Item 1", "Do item 1", "Me", today, DateUtils.addDays(today, 1)));
        list.add(new ToDoItem(2, "Item 2", "Do item 2", "Me", today, DateUtils.addDays(today, -2)));
        list.add(new ToDoItem(3, "Item 3", "Do item 3", "Me", today, DateUtils.addDays(today, 2)));
        list.add(new ToDoItem(4, "Item 4", "Do item 4", "Me", today, DateUtils.addDays(today, 3)));
        list.add(new ToDoItem(5, "Item 5", "Do item 5", "Me", today, DateUtils.addDays(today, 4)));
        return list;
    }
}

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

import com.librethinking.remindme.beans.interfaces.ItemPersistenceClient;
import com.librethinking.remindme.pojos.Response;
import com.librethinking.remindme.pojos.ToDoItem;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
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
    @Resource
    private ItemPersistenceClient itemService;
    
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
            itemService.createItem(todoItem);
            res.setStatus("OK");
        }
        return res;
    }
    
    @RequestMapping(value = "/list/refresh", method = RequestMethod.POST)
    public @ResponseBody List<ToDoItem> refresh() {
        List<ToDoItem> list = itemService.listItems();
        return list;
    }
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() throws IOException {
        ModelAndView mav = new ModelAndView();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        List<ToDoItem> list = itemService.listItems();
        mav.addObject("todolist", list);
        mav.addObject("todolistjson", ow.writeValueAsString(list));
        mav.setViewName("list");
        logger.info("******************* Test *********************");
        return mav;
    }
}

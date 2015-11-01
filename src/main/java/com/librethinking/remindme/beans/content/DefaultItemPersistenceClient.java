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
package com.librethinking.remindme.beans.content;

import com.librethinking.remindme.beans.interfaces.ItemPersistenceClient;
import com.librethinking.remindme.pojos.ToDoItem;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;

/**
 * This is just a stub class for testing purposes.
 * @author Christian Vielma <cvielma@librethinking.com>
 */
public class DefaultItemPersistenceClient implements ItemPersistenceClient{
    private static final Logger logger = Logger.getLogger(DefaultItemPersistenceClient.class);
    
    @Override
    public List<ToDoItem> listItems() {
        logger.info("************* LISTING ITEMS *********************");
        return dummyList();
    }

    @Override
    public void deleteItem(ToDoItem item) {
        logger.info("Deleting item: " + item);
    }

    @Override
    public ToDoItem createItem(ToDoItem item) {
        logger.info("Creating item: " + item);
        return null;
    }

    @Override
    public ToDoItem updateItem(ToDoItem item) {
        logger.info("Updating item: " + item);
        return null;
    }

    @Override
    public ToDoItem getItem(ToDoItem item) {
        logger.info("Getting item: " + item);
        return null;
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

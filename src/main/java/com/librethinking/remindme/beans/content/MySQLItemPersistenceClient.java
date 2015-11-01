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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Christian Vielma <cvielma@librethinking.com>
 */
public class MySQLItemPersistenceClient implements ItemPersistenceClient {
    @Autowired
    private JdbcTemplate jdbc;
    
    @Autowired
    private ConversionService conversionService;
    
    private static final Logger logger = Logger.getLogger(MySQLItemPersistenceClient.class);
    
    private static final String insertSQL = "INSERT INTO Tasks "
            + "(name, description, owner, creationDate, dueDate) VALUES (?, ?, ?, ?, ?)";
    
    @Override
    public List<ToDoItem> listItems() {
        List<Map<String, Object>> dbResults = jdbc.queryForList("SELECT * FROM Tasks");
        List<ToDoItem> result = new ArrayList<>();
        for (Map<String, Object> record : dbResults) {
            result.add(this.conversionService.convert(record, ToDoItem.class));
        }
        return result;
    }

    @Override
    public void deleteItem(ToDoItem item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ToDoItem createItem(ToDoItem item) {
        
        int result = jdbc.update(insertSQL, item.getName(), item.getDescription(), 
                item.getOwner(), item.getCreationDate(), item.getDueDate());
        return result > 0 ? item : null;
    }

    @Override
    public ToDoItem updateItem(ToDoItem item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ToDoItem getItem(ToDoItem item) {
        Map<String, Object> result = jdbc.queryForMap("SELECT * FROM Tasks WHERE id = ? LIMIT 1", item.getId());
        return this.conversionService.convert(result, ToDoItem.class);
    }
    
}

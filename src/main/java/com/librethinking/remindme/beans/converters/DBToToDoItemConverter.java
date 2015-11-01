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
package com.librethinking.remindme.beans.converters;

import com.librethinking.remindme.pojos.ToDoItem;
import java.util.Date;
import java.util.Map;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Christian Vielma <cvielma@librethinking.com>
 */
public class DBToToDoItemConverter implements Converter<Map<String,Object>, ToDoItem> {

    @Override
    public ToDoItem convert(Map<String, Object> source) {
        ToDoItem result = new ToDoItem();
        result.setId((Integer) source.get("id"));
        result.setName((String) source.get("name"));
        result.setDescription((String) source.get("description"));
        result.setCreationDate((Date) source.get("creationDate"));
        result.setDueDate((Date) source.get("dueDate"));
        
        return result;
    }
    
}

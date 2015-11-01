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
package com.librethinking.remindme.beans.interfaces;

import com.librethinking.remindme.pojos.ToDoItem;
import java.util.List;

/**
 *
 * @author Christian Vielma <cvielma@librethinking.com>
 */
public interface ItemPersistenceClient {
    
    public List<ToDoItem> listItems();
    
    public void deleteItem(final ToDoItem item);
    
    public ToDoItem createItem(final ToDoItem item);
    
    public ToDoItem updateItem(final ToDoItem item);
    
    public ToDoItem getItem(final ToDoItem item);
    
}

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

var ToDoList = new function invocation() {
    var list = [];
    var listSection = '#list';
    var showListButton = '#showList';
    var createTaskButton = '#createTaskButton';
    var createTaskForm = '#createTaskForm';
    var createTaskInput = '#createTaskName';

    this.init = function (newList) {
        list = newList || list;
        var self = this;
        $(document).ready(function () {
            refreshList();
            $(showListButton).click(refreshList);
            $(createTaskButton).click(createTask);
            $(createTaskForm).keypress(function (e) {
                if (e.which === 13) {
                    $(createTaskButton).click();
                    this.reset();
                }
            });
        });
    };

    var refreshList = function () {
        $(listSection).empty();
        $.ajax({
            type: 'POST',
            url: baseUrl + '/list/refresh',
            dataType: 'json',
            contentType: 'application/json',
            processData:false,
            async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
            cache: false,    //This will force requested pages not to be cached by the browser  
            success: function (data) {
                if (data) {
                    list = data;
                    loadList();
                } else {
                    alert('No data');
                }
            },
            error: function (jqXHR, status, error) {
                alert('Error: ' + error);
            }
        });
        
    };

    var loadList = function () {
        for (var i = 0; i < list.length; i++) {
            showTask(list[i], i);
        }
    };

    var showTask = function (todoItem, delayInc) {
        var id = '' || todoItem.id;
        var name = '' || todoItem.name;
        var inc = 1 || delayInc;
        if (name) {
            var todoItemHtml = ' <div id="todo' + id + '" class="todo">\n\
                                            <div class="itemCheck">\n\
                                                <input type="checkbox"/>\n\
                                            </div>\n\
                                            <div class="itemName">'
                    + todoItem.name +
                    '</div>\n\
                                        </div>';
            $(todoItemHtml).hide().appendTo(listSection).delay(300 * inc).fadeIn('slow');
        }
    };

    var createTask = function () {
        var todoItem = {
            name: $(createTaskInput).val(),
        };

        $.ajax({
            type: 'POST',
            url: baseUrl + '/list/create',
            data: JSON.stringify(todoItem),
            dataType: 'json',
            contentType: 'application/json',
            processData:false,
            async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
            cache: false,    //This will force requested pages not to be cached by the browser  
            success: function (data) {
                if (data.status === 'OK') {
                    list.push(todoItem);
                    showTask(todoItem);
                } else {
                    alert('Creation Failed: ' + data);
                }
            },
            error: function (jqXHR, status, error) {
                alert('Error: ' + error);
            }
        });
    };
};

ToDoList.init(newList);

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
package com.librethinking.remindme.config;

import com.librethinking.remindme.beans.content.MySQLItemPersistenceClient;
import com.librethinking.remindme.beans.interfaces.ItemPersistenceClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration class
 * 
 * application.properties is not getting into one of the expected paths: 
 *      http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html
 * @author Christian Vielma <cvielma@librethinking.com>
 */
@SpringBootApplication
@PropertySource("/WEB-INF/classes/application.properties")
public class AppConfig {
    
    @Bean
    public ItemPersistenceClient itemPersistenceService() {
        return new MySQLItemPersistenceClient();
//        return new DefaultItemPersistenceClient();
    }
}

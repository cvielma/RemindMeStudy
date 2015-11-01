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

import com.librethinking.remindme.beans.converters.DBToToDoItemConverter;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Christian Vielma <cvielma@librethinking.com>
 */
@Configuration
public class ConversionServiceProvider
{
    // Need to add conversionService name: http://stackoverflow.com/questions/30039619/how-to-autowired-in-conversionservice-in-springboot
    @Bean(name = "conversionService")
    public ConversionService getConversionService()
    {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters( getConverters() );
        bean.afterPropertiesSet();
        ConversionService object = bean.getObject();
        return object;
    }

    private Set<Converter<?, ?>> getConverters()
    {
        Set<Converter<?, ?>> converters = new HashSet<>();

        converters.add( new DBToToDoItemConverter() );
        // add here more custom converters, either as spring bean references or directly instantiated

        return converters;
    }
}

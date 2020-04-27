/*
 * Copyright (c) 2017. <a href="cf">cf</a> All rights reserved.
 */

package com.bdfint.backend.framework.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * FreeMarkers工具类
 *
 * @author lucf
 * @version 2013-01-15
 */
public class FreeMarkers {

    public static String renderString(String templateString, Map<String, ?> model) {
        try {
            StringWriter result = new StringWriter();
            Template t = new Template("name", new StringReader(templateString), new Configuration(Configuration.VERSION_2_3_0));
            t.process(model, result);
            return result.toString();
        } catch (Exception e) {
            throw Exceptions.unchecked(e);
        }
    }

    public static String renderTemplate(Template template, Object model) {
        try {
            StringWriter result = new StringWriter();
            template.process(model, result);
            return result.toString();
        } catch (Exception e) {
            throw Exceptions.unchecked(e);
        }
    }

    public static Configuration buildConfiguration(String directory) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
        Resource path = new DefaultResourceLoader().getResource(directory);
        cfg.setDirectoryForTemplateLoading(path.getFile());
        return cfg;
    }

}

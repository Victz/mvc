/*
 * The MIT License
 *
 * Copyright 2016 Victz.com.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.victz.mvc;

import com.victz.model.PostsModel;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Victz.com
 */
@Path("posts")
@Controller
@RequestScoped
public class PostsController {

    @Inject
    private PostsModel postsModel;

    @GET
    @View("posts.jsp")
    public void posts() {
    }

    @GET
    @Path("{path:.*}")
    @View("posts.jsp")
    public void get(@PathParam("path") String path) {
        Map<String, String> params = parsePath(path);
        String category = params.get("category");
        if (category != null && !category.isEmpty()) {
            postsModel.setCategoryId(category);
        }

        String tag = params.get("tag");
        if (tag != null && !tag.isEmpty()) {
            postsModel.setTagId(tag);
        }
        String page = params.get("page");
        if (StringUtils.isNumeric(page)) {
            int pageNo = Integer.parseInt(page);
            if (pageNo > 0) {
                postsModel.setPageNo(pageNo);
            }
        }
    }

    @GET
    @Path("loadPost{p:/?}{path:.*}")
    @View("posts.jsp")
    public void loadPost(@PathParam("path") String path) {
        Map<String, String> params = parsePath(path);
        String category = params.get("category");
        if (category != null && !category.isEmpty()) {
            postsModel.setCategoryId(category);
        }

        String tag = params.get("tag");
        if (tag != null && !tag.isEmpty()) {
            postsModel.setTagId(tag);
        }
        String page = params.get("page");
        if (StringUtils.isNumeric(page)) {
            int pageNo = Integer.parseInt(page);
            if (pageNo > 0) {
                postsModel.setPageNo(pageNo);
            }
        }
    }

    private Map<String, String> parsePath(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        String[] pathArray = path.split("/");
        Map<String, String> pathMap = new HashMap<>();
        for (int i = 0; i < pathArray.length / 2; i++) {
            String key = pathArray[2 * i];
            String value = pathArray[2 * i + 1];
            pathMap.put(key, value);
        }
        return pathMap;
    }
}

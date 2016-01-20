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

import java.net.URI;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.MvcContext;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 *
 * @author Victz.com
 */
@Path("")
@Controller
@RequestScoped
public class IndexController {

    @Inject
    MvcContext mvcContext;
    @Inject
    Models models;

    @GET
    @Path("")
    @View("index.jsp")
    public void index() {
        models.put("msg", "Message from index");
    }

    @GET
    @Path("signin")
    @View("signin.jsp")
    public void signin() {
        models.put("msg", "Message from login");
    }

    @POST
    @Path("signin")
    public Response signin(@Context HttpServletRequest request) {
        return Response.seeOther(URI.create(mvcContext.getContextPath() + "/posts")).cookie(new NewCookie("user", "auth token value sample",
                (mvcContext.getContextPath() == null || mvcContext.getContextPath().isEmpty()) ? "/" : mvcContext.getContextPath(),
                "", "The is a test cookie, expire after 30 days", 60 * 60 * 24 * 30, false, true)).build();
    }

    @GET
    @Path("signout")
    public Response signout(@Context HttpServletRequest request) {
        request.getSession().invalidate();
        return Response.seeOther(URI.create(mvcContext.getContextPath() + "/signin")).cookie(new NewCookie("user", "",
                (mvcContext.getContextPath() == null || mvcContext.getContextPath().isEmpty()) ? "/" : mvcContext.getContextPath(),
                "", "", 0, false, true)).build();
    }
}

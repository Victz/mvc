/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.victz.mvc;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Victz.com
 */
@Path("")
@Controller
@RequestScoped
public class IndexController {

    @Inject
    Models models;

    @GET
    @Path("")
    @View("index.jsp")
    public void index() {
        models.put("msg", "Message from index");
    }

    @GET
    @Path("content")
    @View("content.jsp")
    public void content() {
        models.put("msg", "Message from Content");
    }
}

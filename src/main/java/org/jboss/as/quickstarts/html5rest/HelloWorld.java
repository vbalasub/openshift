/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.html5rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

/**
 * A simple REST service which is able to say hello to someone using HelloService Please take a look at the web.xml where JAX-RS
 * is enabled And notice the @PathParam which expects the URL to contain /json/David or /xml/Mary
 *
 * @author bsutter@redhat.com
 */

@Path("/")
public class HelloWorld {
    @Inject
    HelloService helloService;

    @GET
    @Path("/headers")
    @Produces("application/json")
    public String getHelloWorldJSON(@Context HttpHeaders headers) {
    	MultivaluedMap<String,String> headerMap = headers.getRequestHeaders();
    	for(String key : headerMap.keySet()){
    		System.out.println(key);
    	}
        return "Headers End";
//        return "{\"result\":\"" + helloService.createHelloMessage(name) + "\"}";
    }

    @POST
    @Path("/json/{name}")
    @Produces("application/json")
    public String getHelloWorldJSON(@PathParam("name") String name,@Context HttpHeaders headers) {
        System.out.println("name: " + name);
        String user = headers.getRequestHeader("X-Forwarded-User").get(0);;
        return "{\"result\":\"" + helloService.createHelloMessage(name +"USER:"  user) + "\"}";
    }

    @POST
    @Path("/xml/{name}")
    @Produces("application/xml")
    public String getHelloWorldXML(@PathParam("name") String name) {
        System.out.println("name: " + name);
        return "<xml><result>" + helloService.createHelloMessage(name) + "</result></xml>";
    }

}

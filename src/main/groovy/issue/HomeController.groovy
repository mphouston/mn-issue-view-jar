package issue

import io.micronaut.core.util.CollectionUtils
import io.micronaut.core.io.Writable
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.views.ModelAndView
import io.micronaut.views.View
import io.micronaut.views.handlebars.HandlebarsViewsRenderer

import javax.inject.Inject
import io.micronaut.http.annotation.Controller


/*
 * Copyright Michael Houston 2017. All rights reserved.
 * Original Author: mph
 *
*/

/**
 *
 */
@Controller("/")
class HomeController {
  @Inject HandlebarsViewsRenderer renderer


  @Get("/")
  ModelAndView index(HttpRequest request) {
    println "index() request = $request"
    def res = new ModelAndView("index", [loggedIn: true, someText: '<b>ABC</b>', username: 'joe'])
    return res
  }

  @View("index")
  @Get("/pogo")
  HttpResponse pogo() {
    return HttpResponse.ok(CollectionUtils.mapOf("loggedIn", true, "username", "sdelamo",'someText', '<b>ABC</b>'))
  }

  @Produces(MediaType.TEXT_HTML)
  @Get("/w")
  Writable w(HttpRequest request) {
    println "w() request = $request, $renderer"
    return renderer.render('index',[loggedIn: true, someText: '<b>ABC</b>', username: 'joe'])
  }




}

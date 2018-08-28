package me.rowkey.training.vertx

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.DeploymentOptions
import io.vertx.core.json.JsonObject


/**
 * Author: Bryant.Hang
 * Date: 2018/3/26
 * Email: superhj1987@126.com
 */
class MainVerticle : AbstractVerticle() {

    @Throws(Exception::class)
    override fun start() {
        vertx.createHttpServer().requestHandler { req ->
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!")
        }.listen(8080)
        println("HTTP server started on port 8080")
    }
}

fun main(args: Array<String>) {
    var str1 : String = "a"
    val str2 : String
    str2 = "b"
    val vertx = Vertx.vertx()
    val options = DeploymentOptions().setConfig(JsonObject().put("http.port", 8080))
    vertx.deployVerticle("me.rowkey.training.vertx.MainVerticle",options)
}
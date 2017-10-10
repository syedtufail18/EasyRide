package com.kempo.easyride;
import static spark.Spark.*;

public class Server {
    public static void main(String[] args)
    {
        getHerokuAssignedPort();
        staticFileLocation("/public");
        get("/ping", (req, res) -> "pong");
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}

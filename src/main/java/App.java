import com.google.gson.Gson;
import dao.CardTextDao;
import dao.Sql2oCardTextDao;
import exceptions.ApiException;
import models.CardText;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


/**
 * Created by Guest on 8/25/17.
 */
public class App {
    public static void main(String[] args) {
        Sql2oCardTextDao cardTextDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/hearthstone-api.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        cardTextDao = new Sql2oCardTextDao(sql2o);
        conn = sql2o.open();

        //CREATE
        post("/cardtexts/new", "application/json", (req, res) -> {
            res.type("application/json");
            CardText cardText = gson.fromJson(req.body(), CardText.class);
            cardTextDao.add(cardText);
            res.status(201);
            return gson.toJson(cardText);
        });
        //READ
        get("/cardtexts", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(cardTextDao.getAll());//send it back to be displayed
        });

        get("/cardtexts/:id", "application/json", (req, res) -> {
            res.type("application/json");
            int cardTextId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(cardTextDao.findById(cardTextId));
        });
    }
}
    //        FILTERS
//    exception(ApiException.class, (exc, req, res) -> {
//        RuntimeException err = exc;
//        Map<String, Object> jsonMap = new HashMap<>();
//            jsonMap.put("status", err.getStatusCode());
//        jsonMap.put("errorMessage", err.getMessage());
//        res.type("application/json"); //after does not run in case of an exception.
//            res.status(err.getStatusCode()); //set the status
//        res.body(gson.toJson(jsonMap));  //set the output.
//    });
//
//    after((req, res) ->{
//        res.type("application/json");
//    });
//}


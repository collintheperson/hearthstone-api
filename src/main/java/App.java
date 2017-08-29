import com.google.gson.Gson;
import dao.CardTextDao;
import dao.Sql2oCardTextDao;
import dao.Sql2oRarityDao;
import exceptions.ApiException;
import models.CardText;
import models.Rarity;
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
        Sql2oRarityDao rarityDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/hearthstone-api.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        cardTextDao = new Sql2oCardTextDao(sql2o);
        rarityDao = new Sql2oRarityDao(sql2o);
        conn = sql2o.open();

        //CREATE
        post("/cardtexts/new", "application/json", (req, res) -> {
            CardText cardText = gson.fromJson(req.body(), CardText.class);
            cardTextDao.add(cardText);
            res.status(201);
            return gson.toJson(cardText);
        });
        //READ
        get("/cardtexts", "application/json", (req, res) -> {
            return gson.toJson(cardTextDao.getAll());//send it back to be displayed
        });

        get("/cardtexts/:id", "application/json", (req, res) -> {
            int cardTextId = Integer.parseInt(req.params("id"));
            CardText cardToFind = cardTextDao.findById(cardTextId);
            if (cardToFind == null){
                throw new RuntimeException(String.format("No card with the id: %s exists", req.params("id")));
            }
            return gson.toJson(cardTextDao.findById(cardTextId));
        });

        //update
        post("/cardtexts/:id/update", "application/json", (request, response) -> {
            int cardTextId = Integer.parseInt(request.params("id"));
            CardText cardText = gson.fromJson(request.body(), CardText.class);
            cardTextDao.update(cardText.getAttack(),cardText.getHealth(),cardText.getMana(),cardTextId);
            response.status(201);
            return gson.toJson(cardText);

                });

        //delete
        get("/cardtexts/:id/delete", "application/json", (req, res) -> {
            int cardtextId = Integer.parseInt(req.params("id"));
            cardTextDao.deleteById(cardtextId);
            return cardtextId;
        });

        //CREATE
        post("/rarities/new", "application/json", (req, res) -> {
            Rarity rarity = gson.fromJson(req.body(), Rarity.class);
            rarityDao.add(rarity);
            res.status(201);
            return gson.toJson(rarity);
        });
        //READ
        get("/rarities", "application/json", (req, res) -> {
            return gson.toJson(rarityDao.getAll());
        });

        get("/rarities/:id", "application/json", (req, res) -> {
            int rarityId = Integer.parseInt(req.params("id"));
            Rarity cardToFind = rarityDao.findById(rarityId);
            if (cardToFind == null){
                throw new RuntimeException(String.format("No card with the id: %s exists", req.params("id")));
            }
            return gson.toJson(rarityDao.findById(rarityId));
        });

        //update
        post("/rarities/:id/update", "application/json", (request, response) -> {
            int rarityId = Integer.parseInt(request.params("id"));
            Rarity rarity = gson.fromJson(request.body(), Rarity.class);
            rarityDao.update(rarityId,rarity.getMana(),rarity.getCardDetail());
            response.status(201);
            return gson.toJson(rarity);

        });

        //delete
        get("/rarities/:id/delete", "application/json", (req, res) -> {
            int rarityId = Integer.parseInt(req.params("id"));
            rarityDao.deleteById(rarityId);
            return rarityId;
        });

        //filter

        exception(ApiException.class, (exc, req, res) -> {
            RuntimeException err = exc;
            Map<String, Object> jsonMap = new HashMap<>();
//            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
//            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });
        after((req, res) ->{
            res.type("application/json");
        });
    }
}


//

//}


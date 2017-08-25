package dao;

import models.CardText;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;


public class Sql2oCardTextDao implements CardTextDao {
    private final Sql2o  sql2o;
    public Sql2oCardTextDao(Sql2o sql2o)    {
        this.sql2o=sql2o;}

    @Override
    public void add (CardText cardText) {
        String sql = "INSERT INTO cardtext (name, attack, mana, classtype, carddetail, health) VALUES (:name, :attack, :mana, :classType, cardDetail, health :)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("name", cardText.getName())
                    .addParameter("attack", cardText.getAttack())
                    .addParameter("mana", cardText.getMana())
                    .addParameter("classType", cardText.getClassType())
                    .addParameter("cardDetail", cardText.getCardDetail())
                    .addParameter("health", cardText.getHealth())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("ATTACK","attack")
                    .addColumnMapping("MANA","mana")
                    .addColumnMapping("CLASSTYPE", "classtype")
                    .addColumnMapping("CARDDETAIL","cardDetail")
                    .addColumnMapping("HEALTH","health")
                    .executeUpdate()
                    .getKey();
                 cardText.setId(id);
        }   catch (Sql2oException ex)  {
            System.out.println(ex);
        }
    }
//    @Override
//    public void add (CardText CardText) {
//        String sql = "INSERT INTO cardtext (name, mana, classtype, detail) VALUES (:name, :mana, :classType, cardDetail)";
//        try (Connection con = sql2o.open()) {
//            int id = (int) con.createQuery(sql)
//                    .addParameter("name", CardText.getName())
//                    .addParameter("mana", CardText.getMana())
//                    .addParameter("classType", CardText.getClassType())
//                    .addParameter("cardDetail",CardText.getCardDetail())
//                    .addColumnMapping("NAME", "name")
//                    .addColumnMapping("MANA","mana")
//                    .addColumnMapping("CLASSTYPE", "classtype")
//                    .addColumnMapping("CARDDETAIL","cardDetail")
//                    .executeUpdate()
//                    .getKey();
//            CardText.setId(id);
//        }   catch (Sql2oException ex)  {
//            System.out.println(ex);
//        }
//    }
    @Override
    public List<CardText> getAll() {
        try ( Connection con = sql2o.open())    {
            return con.createQuery("SELECT * FROM text")
                    .executeAndFetch(CardText.class);
        }
    }
}

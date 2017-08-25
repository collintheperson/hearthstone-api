package dao;

import models.Text;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


public class Sql2OCardTextDao implements CardTextDao {
    private final Sql2o  sql2o;
    public Sql2OCardTextDao(Sql2o sql2o)    {
        this.sql2o=sql2o;}

    @Override
    public void add (Text cardText) {
        String sql = "INSERT INTO cardText (name, attack, mana, classtype, carddetail, health) VALUES (:name, :attack, :mana, :classType, cardDetail, health :)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("name", cardText.getName())
                    .addParameter("attack", cardText.getAttack())
                    .addParameter("mana",cardText.getMana())
                    .addParameter("classType",cardText.getClassType())
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
    @Override
    public void List<CardText> Sql2OCardTextDao() {
        try ( Connection con = sql2o.open())    {
            return con.createQuery("SELECT * FROM text")
                    .executeAndFetch(CardText.class);
        }
    }
}

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
        String sql = "INSERT INTO cardtext ( attack, health, mana, classtype,name,carddetail) VALUES (:attack, :health, :mana, :classType, :name, :cardDetail)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("attack", cardText.getAttack())
                    .addParameter("health", cardText.getHealth())
                    .addParameter("mana", cardText.getMana())
                    .addParameter("classType", cardText.getClassType())
                    .addParameter("name", cardText.getName())
                    .addParameter("cardDetail", cardText.getCardDetail())
                    .addColumnMapping("ATTACK","attack")
                    .addColumnMapping("HEALTH","health")
                    .addColumnMapping("MANA","mana")
                    .addColumnMapping("CLASSTYPE", "classtype")
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("CARDDETAIL","cardDetail")
                    .executeUpdate()
                    .getKey();
                 cardText.setId(id);
        }   catch (Sql2oException ex)  {
            System.out.println(ex);
        }
    }

    @Override
    public List<CardText> getAll() {
        try ( Connection con = sql2o.open())    {
            return con.createQuery("SELECT * FROM cardtext")
                    .executeAndFetch(CardText.class);
        }
    }
    @Override
    public CardText findById(int id) {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM cardtext WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(CardText.class);
        }
    }
    @Override
    public void update (int attack, int health, int mana, int id) {
        String sql = "UPDATE cardtext SET( attack, health, mana) = (:attack, :health, :mana) WHERE id=:id";
        try (Connection con = sql2o.open()) {
             con.createQuery(sql)
                    .addParameter("attack", attack)
                    .addParameter("health", health)
                    .addParameter("mana", mana)
                    .addParameter("id",id)
                    .executeUpdate();
        }   catch (Sql2oException ex)  {
            System.out.println(ex);
        }
    }
    @Override
    public void deleteById(int id)  {
        try ( Connection con = sql2o.open())    {
            con.createQuery("DELETE FROM cardtext WHERE id = :id")
                    .addParameter("id",id)
                    .executeUpdate();
        }   catch (Sql2oException ex)   {
            System.out.println(ex);
        }
    }
}

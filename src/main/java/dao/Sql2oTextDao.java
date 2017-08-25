package dao;

import models.Text;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


public class Sql2oTextDao implements TextDao {
    private final Sql2o  sql2o;
    public Sql2oTextDao(Sql2o sql2o)    {
        this.sql2o=sql2o;}

    @Override
    public void add (Text text) {
        String sql = "INSERT INTO text (name, attack, mana, classtype, carddetail, health) VALUES (:name, :attack, :mana, :classType, cardDetail, health :)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("name", text.getName())
                    .addParameter("attack", text.getAttack())
                    .addParameter("mana",text.getMana())
                    .addParameter("classType",text.getClassType())
                    .addParameter("cardDetail", text.getCardDetail())
                    .addParameter("health", text.getHealth())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("ATTACK","attack")
                    .addColumnMapping("MANA","mana")
                    .addColumnMapping("CLASSTYPE", "classtype")
                    .addColumnMapping("CARDDETAIL","carddetail")
                    .addColumnMapping("HEALTH","health")
                    .executeUpdate()
                    .getKey();
                 text.setId(id);
        }   catch (Sql2oException ex)  {
            System.out.println(ex);
        }

    }
}

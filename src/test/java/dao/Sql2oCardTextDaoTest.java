package dao;

import models.CardText;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/25/17.
 */
public class Sql2oCardTextDaoTest {

    private Sql2oCardTextDao textDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        textDao = new Sql2oCardTextDao(sql2o);
        conn = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_InstantiatesANewCard()  throws Exception    {
        CardText card = setUpNewCard();
        assertTrue(card instanceof CardText);
    }
    @Test
    public void add_InstantiatesANewSpell() throws Exception    {
        CardText spell = setUpNewSpell();
        assertTrue(spell instanceof CardText);
    }
    @Test
    public void getAll_RetrieveAllCards()   throws Exception    {
        CardText card = setUpNewCard();
        CardText spell = setUpNewSpell();
        textDao.add(card);
        textDao.add(spell);
        assertEquals(2,textDao.getAll().size());
    }

    //helper methods
    public static CardText setUpNewCard ()  {
        return new CardText(2,5,1,"Priest ","Give +1 attack to minions with more than 4 attack","Direlurk Monkey");
    }
    public static CardText setUpNewSpell()  {
        return new CardText(8,"Mage","Destroy all minions","Ragnarok");
    }

}
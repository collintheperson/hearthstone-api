package dao;

import models.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/25/17.
 */
public class Sql2OCardCardTextDaoTest {

    private Sql2OCardTextDao textDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        textDao = new Sql2OCardTextDao(sql2o);
        conn = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add_InstantiatesANewCard()  throws Exception    {
        Text card = setUpNewCard();
        assertTrue(card instanceof  Text);
    }
    @Test
    public void add_InstantiatesANewSpell() throws Exception    {
        Text spell = setUpNewSpell();
        assertTrue(spell instanceof Text);
    }
    @Test
    public void getAll_RetrieveAllCards()   throws Exception    {
        Text card = setUpNewCard();
        Text spell = setUpNewSpell();
        textDao.add(card);
        textDao.add(spell);
        assertEquals(2.textDao.getAll().size());
    }

    //helper methods
    public static Text setUpNewCard ()  {
        return new Text(2,5,1,"Priest ","Give +1 attack to minions with more than 4 attack","Direlurk Monkey");
    }
    public static Text setUpNewSpell()  {
        return new Text (8,"Mage","Destroy all minions","Ragnarok");
    }

}
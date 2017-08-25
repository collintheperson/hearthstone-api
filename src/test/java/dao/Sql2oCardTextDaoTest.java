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

    private Sql2oCardTextDao cardTextDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        cardTextDao = new Sql2oCardTextDao(sql2o);
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
//    @Test
//    public void add_InstantiatesANewSpell() throws Exception    {
//        CardText spell = setUpNewSpell();
//        assertTrue(spell instanceof CardText);
//    }
    @Test
    public void getAll_RetrieveAllCards()   throws Exception    {
        CardText card = setUpNewCard();
        CardText spell = new CardText(6,"b","c","d",1,5);
        cardTextDao.add(card);
        cardTextDao.add(spell);
        assertEquals(2,cardTextDao.getAll().size());
    }
    @Test
    public void findById_FindASpecificCardText_FishWitch() throws Exception {
        CardText test1 = setUpNewCard();
        CardText test2 = new CardText (3,"Warlock","Destroy your other minions and gain their stats","Corruptor the Undoer",8,2);
        cardTextDao.add(test1);
        cardTextDao.add(test2);
        CardText foundCardText = cardTextDao.findById(2);
        assertEquals("Corruptor the Undoer",foundCardText.getName());
    }
    @Test
    public void update_EvaluateIfCardTextChanged() throws Exception {
        CardText test1 = setUpNewCard();
        cardTextDao.add(test1);
        cardTextDao.update(1,2,4,1);
        CardText foundCardText = cardTextDao.findById(1);
        assertEquals(1, foundCardText.getAttack());
    }
    @Test
    public void delete_SeeIfACardIsDeleted()    throws Exception    {
        CardText test1 = setUpNewCard();
        cardTextDao.add(test1);
        int idToDelete = test1.getId();
        cardTextDao.deleteById(idToDelete);
        assertEquals(0,cardTextDao.getAll().size());
    }


    //helper methods
    public static CardText setUpNewCard ()  {
        return new CardText(2,"a","b","Labrapriest",2,5);
    }
//    public static CardText setUpNewSpell()  {
//        return new CardText(8,"Mage","Destroy all minions","Ragnarok");
//    }

}
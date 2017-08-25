import com.google.gson.Gson;
import dao.Sql2oTextDao;
import org.sql2o.Sql2o;
import org.sql2o.Connection;


/**
 * Created by Guest on 8/25/17.
 */
public class App {
    public static void main(String[] args) {
        Sql2oTextDao textDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/salesify.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        textDao = new Sql2oTextDao(sql2o);
        conn = sql2o.open();
    }
    }

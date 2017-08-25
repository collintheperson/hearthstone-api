package dao;

import models.Text;

import java.util.List;

/**
 * Created by Guest on 8/25/17.
 */
public interface CardTextDao {

    void add(Text text);

    List<Text> getAll();
//
//    Text findByName(String name);
//
//    Text findByMana(int mana);
}

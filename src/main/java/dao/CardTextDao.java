package dao;


import models.CardText;

import java.util.List;

public interface CardTextDao {
    //create
    void  add(CardText cardtext);
    //   void addCardTextToFoodType(CardText cardtext, Foodtype foodtype);

    //get all
    List<CardText> getAll();


    //find
    CardText findById(int id);
//    List<CardText> findAllCardsByCardText(int cardtextId);
    //update
   void update(int id, int mana, int attack, int health);

    //delete
    void deleteById(int id);
}

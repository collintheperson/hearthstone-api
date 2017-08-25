package dao;


import models.CardText;

import java.util.List;

public interface CardTextDao {
    //create
    void add (CardText cardtext);
    //   void addCardTextToFoodType(CardText cardtext, Foodtype foodtype);

    //get all
    List<CardText> getAll();


    //find
//    CardText findById(int id);
//    List<CardText> findAllCardsByCardText(int cardtextId);
    //update
//    void update(int id, String name, String address, String zipcode, String phone, String website, String email, String image);

    //delete
  //  void deleteById(int id);
}

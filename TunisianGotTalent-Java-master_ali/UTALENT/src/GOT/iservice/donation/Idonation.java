/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.iservice.donation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Ali
 */
public interface Idonation<T> {
    void ajouter(T t) throws SQLException;
      ObservableList<T> affciher() throws SQLException;
      void delete(int id) throws SQLException;
       void update(T t, int id) throws SQLException;
        public T findById(int id) ;
          List<T> afficherdon()throws SQLException; ;
         public  ArrayList<T> getdonationbyCat(String dbc); 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.iservice.facture;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ali
 */
public interface Ifacture<T> {
    void ajouter(T t) throws SQLException;
      List<T> affciher() throws SQLException;
      void delete(int id) throws SQLException;
       void update(T t, int id) throws SQLException;
}

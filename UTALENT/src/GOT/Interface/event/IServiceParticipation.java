/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.Interface.event;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author maysa1998
 */
public interface IServiceParticipation<T> {
    
    
    void ajouter(T t) throws SQLException;
    boolean delete(T t) throws SQLException;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.Interface.reclamation;

import GOT.entites.reclamation.Avis;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author skand
 */
public interface AvisInterface {
    public Boolean ExistAvis(int id) throws SQLException;
    public Boolean ajouterAvis(Avis a) throws SQLException;
    public List<Avis> AfficherAvis() throws SQLException;
    public Avis getAvisById(int id) throws SQLException;
    public Boolean deleteAvis(int id) throws SQLException;
    //public Boolean updateAvis(Avis a) throws SQLException;
}

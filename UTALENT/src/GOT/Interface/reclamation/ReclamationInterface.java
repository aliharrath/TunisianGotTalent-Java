/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.Interface.reclamation;

import GOT.entites.reclamation.Avis;
import GOT.entites.reclamation.Reclamation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author skand
 */
public interface ReclamationInterface {
    public Boolean ExistReclamation(int id) throws SQLException;
    public Boolean ajouterReclamation(Reclamation r) throws SQLException;
    public List<Reclamation> AfficherReclamation() throws SQLException;
    public List<Reclamation> AfficherReclamation(int id_user) throws SQLException;
    public Reclamation getReclamationById(int id) throws SQLException;
    public Boolean deleteReclamation(int id) throws SQLException;
    public Boolean updateReclamation(Reclamation r) throws SQLException;
    public Boolean updateAvis(int id) throws SQLException ;
    public Avis chercheAvis(int id);
}

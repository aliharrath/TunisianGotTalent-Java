/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOT.Interface.groupe;

import GOT.entites.groupe.Groupe;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IGroupeServices {
     public void create(Groupe e);
    public List<Groupe> affichergp();
    public void update(Groupe g);
    public void delete(int id);
  
}

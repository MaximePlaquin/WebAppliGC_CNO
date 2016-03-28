package userclasses.modele;

import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.table.TableModel;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author rsmon
 */
public class ModeleDeTableau implements TableModel

{
     
   private ArrayList<ArrayList> donneesDuModeleDuTableau;

   public ModeleDeTableau(ArrayList<ArrayList> donneesDuModeleDuTableau) {
     this.donneesDuModeleDuTableau = donneesDuModeleDuTableau;
   }
     
   public int     getRowCount(){ return donneesDuModeleDuTableau.size()-1; }
   public int     getColumnCount(){ return donneesDuModeleDuTableau.get(0).size(); }
   public String  getColumnName(int i){ return (String) donneesDuModeleDuTableau.get(0).get(i); }
   public boolean isCellEditable(int row, int column) { return false;}
   public Object  getValueAt(int row, int column) { return donneesDuModeleDuTableau.get(row+1).get(column); }
   public void    setValueAt(int row, int column, Object o) {donneesDuModeleDuTableau.get(row+1).set(column, o); }
   public void    addDataChangeListener(DataChangedListener d) {}
   public void    removeDataChangeListener(DataChangedListener d) {}
    
   public void remplirEntete(String ... titres) {
       
     donneesDuModeleDuTableau.clear();   
     ArrayList entetes= new ArrayList(Arrays.asList(titres));
     donneesDuModeleDuTableau.add(entetes);
   }         

   public void ajouterRangee(Object ... valeurs){
      
      ArrayList   ligSal= new ArrayList();    
      ligSal.addAll(Arrays.asList(valeurs));
      donneesDuModeleDuTableau.add(ligSal); 
   }
}


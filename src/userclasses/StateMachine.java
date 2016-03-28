/**
 * Your application code goes here
 */

package userclasses;

import generated.StateMachineBase;
import com.codename1.ui.*; 
import com.codename1.ui.events.*;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.util.Resources;
import com.codename1.xml.Element;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import userclasses.modele.ModeleDeTableau;
import userclasses.parser.RequetePourArbreXML;
import static userclasses.parser.AideParserArbreXML.getValeur;

/**
 *
 * @author Maxime
 */
public class StateMachine extends StateMachineBase {
    public StateMachine(String resFile) {  super(resFile); }

    RequetePourArbreXML requeteRestRegion;
    RequetePourArbreXML requeteResumeRegion;
    ComboBox comboRegion;
    ListModel modeleDuComboBox;
    Map<String, String> itemsCombo;
    Element racine;
    Element racineResumeRegion;
    Vector<Element> lesRegions;
    Table tableau;
    ModeleDeTableau modeleDuTableau;
    ArrayList<ArrayList> donneesDuModeleTableau;
    
    String libRegionSelect;
    
    
    @Override
    protected void postMain(Form f) {
        super.postMain(f);

        requeteRestRegion = new RequetePourArbreXML();
        requeteResumeRegion = new RequetePourArbreXML();
    }
    
   
    
    // Methode pour le bouto OK ( clique droit > Override methode )
    @Override
    protected void onMain_ConnecterAction(Component c, ActionEvent event) {
        super.onMain_ConnecterAction(c, event);
        
        //récuperer login et mdp
        String login = this.findLogin().getText();
        String mdp = this.findMdp().getText();
        
        if (login.equals("Max") && mdp.equals("salut")){
            this.showForm("CaRegion", null);
            
            
            //initialisation combo
            
            requeteRestRegion.executer("/region/toutes");
            comboRegion = this.findComboRegion();
            modeleDuComboBox = comboRegion.getModel();
            itemsCombo = new HashMap();
            racine = requeteRestRegion.getRacine();
            
            
            //Initialisation tableau
            
            tableau = this.findTableRegion();
            tableau.setDrawBorder(true);
            tableau.setScrollableX(true);
            tableau.setScrollableY(true);
            
            donneesDuModeleTableau = new ArrayList<ArrayList>();
            modeleDuTableau = new ModeleDeTableau(donneesDuModeleTableau);
            modeleDuTableau.remplirEntete("Chiffre D'affaire", "Nombre de clients");
            tableau.setVisible(true);
            tableau.setModel(modeleDuTableau);
            
            
            lesRegions = racine.getChildrenByTagName("resumeRegion");
            
            for(Element laRegion: lesRegions ){
                
                itemsCombo.put(getValeur(laRegion,"nomReg"), getValeur(laRegion,"codeReg"));
                modeleDuComboBox.addItem(getValeur(laRegion,"nomReg"));
                
            }
            
            
        }
        else {
            System.out.println("Erreur de login");
        }
        
    }

    //Methode pousser les données vers le tableau 
    @Override
    protected void onCaRegion_ComboRegionAction(Component c, ActionEvent event) {
        super.onCaRegion_ComboRegionAction(c, event); 
        
        
        libRegionSelect = modeleDuComboBox.getItemAt(modeleDuComboBox.getSelectedIndex()).toString();
        
        String id = itemsCombo.get(libRegionSelect);
        
        requeteResumeRegion.executer("/region/numero/"+id);
        racineResumeRegion = requeteResumeRegion.getRacine();
        
        modeleDuTableau.remplirEntete("Chiffre D'affaire", "Nombre de clients");
        
        modeleDuTableau.ajouterRangee(getValeur(racineResumeRegion, "caAnnuel"),(getValeur(racineResumeRegion,"nbclient")));
        tableau.setModel(modeleDuTableau);
        
        
        
    }
    

}

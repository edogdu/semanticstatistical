/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.items;

/**
 *
 * @author cem.ozkan
 */
public class Property{
    
    private String name;
    private int id;
    private String enLabel;
    private String trLabel;

    public Property(String name, int id, String enLabel, String trLabel) {
        this.name = name;
        this.id = id;
        this.enLabel = enLabel;
        this.trLabel = trLabel;
    }

    
    
    public String getEnLabel() {
        return enLabel;
    }

    
    public void setEnLabel(String enLabel) {
        this.enLabel = enLabel;
    }

    
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getTrLabel() {
        return trLabel;
    }

    
    public void setTrLabel(String trLabel) {
        this.trLabel = trLabel;
    }
}

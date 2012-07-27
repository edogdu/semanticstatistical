/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.items;

/**
 *
 * @author celebron
 */
public class Sector {
    private String name;
    private String enLabel;

    public Sector(String name, String enLabel) {
        this.name = name;
        this.enLabel = enLabel;
    }

    
    public String getEnLabel() {
        return enLabel;
    }

    public void setEnLabel(String enLabel) {
        this.enLabel = enLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}

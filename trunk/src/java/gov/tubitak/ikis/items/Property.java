/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.items;

/**
 *
 * @author cem.ozkan
 */
public class Property implements Item{
    
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

    
    @Override
    public String getEnLabel() {
        return enLabel;
    }

    @Override
    public void setEnLabel(String enLabel) {
        this.enLabel = enLabel;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTrLabel() {
        return trLabel;
    }

    @Override
    public void setTrLabel(String trLabel) {
        this.trLabel = trLabel;
    }
}

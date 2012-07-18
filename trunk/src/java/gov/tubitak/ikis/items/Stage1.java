/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.items;

/**
 *
 * @author cem.ozkan
 */
public class Stage1 implements Item{

    private String name;
    private String id;
    private String enLabel;
    private String trLabel;

    @Override
    public String getEnLabel() {
        return enLabel;
    }

    @Override
    public void setEnLabel(String enLabel) {
        this.enLabel = enLabel;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
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

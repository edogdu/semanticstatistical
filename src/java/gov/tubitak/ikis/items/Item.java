/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.items;

/**
 *
 * @author cem.ozkan
 */
public interface Item {
    
    public String getName();
    public int getId();
    public String getEnLabel();
    public String getTrLabel();
    public void setName(String name);
    public void setId(int id);
    public void setEnLabel(String enLabel);
    public void setTrLabel(String trLabel);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.items;

/**
 *
 * @author cem.ozkan
 */
public class Data {
    
    private String value;
    private Item belongsTo;
    private Property property;
    private String sector;

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Data(String value, Item belongsTo, Property property, String sector) {
        this.value = value;
        this.belongsTo = belongsTo;
        this.property = property;
        this.sector = sector;
    }
    
    

    public Item getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(Item belongsTo) {
        this.belongsTo = belongsTo;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}

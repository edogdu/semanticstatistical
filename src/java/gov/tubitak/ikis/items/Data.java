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
    private String belongsTo;
    private Property property;
    private String sector;

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Data(String value, String belongsTo, Property property, String sector) {
        this.value = value;
        this.belongsTo = belongsTo;
        this.property = property;
        this.sector = sector;
    }
    
    

    public String getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(String belongsTo) {
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

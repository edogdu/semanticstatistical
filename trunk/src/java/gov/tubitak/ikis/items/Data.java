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
    private String header;
    private String year;
    private String period;
    private String resource;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
    
    

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Data(String value, String belongsTo, Property property, String sector,String header,String year, String period,String resource) {
        this.value = value;
        this.belongsTo = belongsTo;
        this.property = property;
        this.sector = sector;
        this.header = header;
        this.year = year;
        this.period = period;
        this.resource = resource;
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

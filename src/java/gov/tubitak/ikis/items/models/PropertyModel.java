/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.items.models;

import gov.tubitak.ikis.items.Property;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author cem.ozkan
 */
public class PropertyModel implements SelectableDataModel<Property> {

    private Property[] array;

    public PropertyModel() {
    }

    public PropertyModel(Property[] array) {
        this.array = array;
    }
    
    @Override
    public Object getRowKey(Property t) {
        int i=0;
        for (Property property : array) {
            i++;
            if(property.equals(t))
                return i;
        }
        return null;
    }

    @Override
    public Property getRowData(String string) {
        int i=0;
        int j=Integer.parseInt(string);
        for (Property property : array) {
            i++;
            if(i==j)
                return property;
        }
        return null;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.components;

import gov.tubitak.components.models.GraphModel;
import javax.faces.component.UIComponentBase;

/**
 *
 * @author cem.ozkan
 */
public class IkisGraph extends UIComponentBase{
    private GraphModel submittedValue;
    
    @Override
    public String getFamily() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public GraphModel getSubmittedValue() {
        return submittedValue;
    }

    public void setSubmittedValue(GraphModel submittedValue) {
        this.submittedValue = submittedValue;
    }
    
}

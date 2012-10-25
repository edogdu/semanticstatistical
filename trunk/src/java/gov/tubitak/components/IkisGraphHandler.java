/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.components;

import gov.tubitak.components.models.GraphModel;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.MetaRuleset;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.facelets.MethodRule;

/**
 *
 * @author celebron
 */
public class IkisGraphHandler extends ComponentHandler{

    public IkisGraphHandler(ComponentConfig config) {
        super(config);
    }

    @Override
    protected MetaRuleset createMetaRuleset(Class type) {
        MetaRuleset metaRuleset = super.createMetaRuleset(type);  
        Class[] model = new Class[]{GraphModel.class};

        metaRuleset.addRule(new MethodRule("model", null, model));

        return metaRuleset; 
    }
    
}

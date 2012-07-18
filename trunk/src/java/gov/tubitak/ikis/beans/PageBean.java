/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.beans;

import gov.tubitak.ikis.items.Item;
import gov.tubitak.ikis.service.Properties;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author cem.ozkan
 */
@ManagedBean(name="PageBean")
@ViewScoped
public class PageBean {
    
    private List<Item> selectedHeaders;

    public void setSelectedHeaders(List<Item> selectedHeaders) {
        this.selectedHeaders = selectedHeaders;
    }

    public List<Item> getSelectedHeaders() {
        return selectedHeaders;
    }
    
    public Item[] getHeaders(){
        return Properties.getHeaders();
    }
}

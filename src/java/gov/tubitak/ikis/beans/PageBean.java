/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.beans;

import gov.tubitak.ikis.items.Data;
import gov.tubitak.ikis.items.Item;
import gov.tubitak.ikis.items.Stage1;
import gov.tubitak.ikis.items.Stage2;
import gov.tubitak.ikis.service.DataFinder;
import gov.tubitak.ikis.service.GetItems;
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
    
    private Item[] selectedHeaders;
    private Item[] selectedMetadatas;
    private Stage1[] selectedStage1;
    private Stage2[] selectedStage2;
    private Item[] selectedProvince;

    public Item[] getSelectedMetadatas() {
        return selectedMetadatas;
    }

    public void setSelectedMetadatas(Item[] selectedMetadatas) {
        this.selectedMetadatas = selectedMetadatas;
    }

    public Item[] getSelectedProvince() {
        return selectedProvince;
    }

    public void setSelectedProvince(Item[] selectedProvince) {
        this.selectedProvince = selectedProvince;
    }

    public Item[] getSelectedStage1() {
        return selectedStage1;
    }

    public void setSelectedStage1(Stage1[] selectedStage1) {
        this.selectedStage1 = selectedStage1;
    }

    public Item[] getSelectedStage2() {
        return selectedStage2;
    }

    public void setSelectedStage2(Stage2[] selectedStage2) {
        this.selectedStage2 = selectedStage2;
    }

    public void setSelectedHeaders(Item[] selectedHeaders) {
        this.selectedHeaders = selectedHeaders;
    }

    public Item[] getSelectedHeaders() {
        return selectedHeaders;
    }
    
    public Item[] getHeaders(){
        return Properties.getHeaders();
    }
    
    public Item[] getStage1(){
        return GetItems.getStage1();
    }
    
    public Item[] getStage2(){
        return GetItems.getStage2(selectedStage1[0]);
    }
    
    public Item[] getProvince(){
        return GetItems.getProvince(selectedStage2[0]);
    }
    
    public Item[] getMetadatas(){
        if(selectedHeaders.length>0)
            return Properties.getMetadata(selectedHeaders[0].getName());
        return null;
    }
    
    public Data[] getDatas(){
        return DataFinder.getdata(selectedHeaders); //TODO Düzgün parametre ver
    }
}

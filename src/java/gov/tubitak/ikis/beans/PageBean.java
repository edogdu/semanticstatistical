/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.beans;

import gov.tubitak.ikis.items.*;
import gov.tubitak.ikis.service.DataFinder;
import gov.tubitak.ikis.service.GetItems;
import gov.tubitak.ikis.service.Properties;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author cem.ozkan
 */
@ManagedBean(name="PageBean")
@ViewScoped
public class PageBean implements Serializable{
    
    private Property[] selectedHeaders;
    private Property[] selectedMetadatas;
    private Stage1[] selectedStage1;
    private Stage2[] selectedStage2;
    private Province[] selectedProvince;

    public PageBean() {
        selectedHeaders =new Property[1];
        selectedMetadatas =new Property[1];
        selectedProvince =new Province[1];
        selectedStage1=new Stage1[1];
        selectedStage2=new Stage2[1];
    }

    
    public Item[] getSelectedMetadatas() {
        return selectedMetadatas;
    }

    public void setSelectedMetadatas(Property[] selectedMetadatas) {
        this.selectedMetadatas = selectedMetadatas;
    }

    public Province[] getSelectedProvince() {
        return selectedProvince;
    }

    public void setSelectedProvince(Province[] selectedProvince) {
        this.selectedProvince = selectedProvince;
    }

    public Stage1[] getSelectedStage1() {
        return selectedStage1;
    }

    public void setSelectedStage1(Stage1[] selectedStage1) {
        this.selectedStage1 = selectedStage1;
    }

    public Stage2[] getSelectedStage2() {
        return selectedStage2;
    }

    public void setSelectedStage2(Stage2[] selectedStage2) {
        this.selectedStage2 = selectedStage2;
    }

    public void setSelectedHeaders(Property[] selectedHeaders) {
        this.selectedHeaders = selectedHeaders;
    }

    public Property[] getSelectedHeaders() {
        return selectedHeaders;
    }
    
    public Property[] getHeaders(){
        return Properties.getHeaders();
    }
    
    public Stage1[] getStage1(){
        return GetItems.getStage1();
    }
    
    public Stage2[] getStage2(){
        if(selectedStage1[0]!=null)
            return GetItems.getStage2(selectedStage1[0]);
        return  new Stage2[1];
    }
    
    public Province[] getProvince(){
        if(selectedStage2[0]!=null)
            return GetItems.getProvince(selectedStage2[0]);
        return  new Province[1];
    }
    
    public Item[] getMetadatas(){
        if(selectedHeaders[0]!=null)
            return Properties.getMetadata(selectedHeaders[0].getName());
        return  new Item[1];
    }
    
    public Data[] getDatas(){
        return DataFinder.getdata(selectedHeaders); //TODO Düzgün parametre ver
    }
}

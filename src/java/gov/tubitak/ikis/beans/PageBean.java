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
    
    private String[] selectedHeaders;
    private String[] selectedMetadatas;
    private String[] selectedStage1;
    private String[] selectedStage2;
    private String[] selectedProvince;

    public PageBean() {
        selectedHeaders =new String[1];
        selectedMetadatas =new String[1];
        selectedProvince =new String[1];
        selectedStage1=new String[1];
        selectedStage2=new String[1];
    }

    
    public String[] getSelectedMetadatas() {
        return selectedMetadatas;
    }

    public void setSelectedMetadatas(String[] selectedMetadatas) {
        this.selectedMetadatas = selectedMetadatas;
    }

    public String[] getSelectedProvince() {
        return selectedProvince;
    }

    public void setSelectedProvince(String[] selectedProvince) {
        this.selectedProvince = selectedProvince;
    }

    public String[] getSelectedStage1() {
        return selectedStage1;
    }

    public void setSelectedStage1(String[] selectedStage1) {
        this.selectedStage1 = selectedStage1;
    }

    public String[] getSelectedStage2() {
        return selectedStage2;
    }

    public void setSelectedStage2(String[] selectedStage2) {
        this.selectedStage2 = selectedStage2;
    }

    public void setSelectedHeaders(String[] selectedHeaders) {
        this.selectedHeaders = selectedHeaders;
    }

    public String[] getSelectedHeaders() {
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
    
    public Property[] getMetadatas(){
        if(selectedHeaders[0]!=null)
            return Properties.getMetadata(selectedHeaders[0]);
        return  new Property[1];
    }
    
    public Data[] getDatas(){
        return DataFinder.getdata(selectedHeaders, selectedMetadatas, selectedStage1, selectedStage2, selectedProvince);
    }
}

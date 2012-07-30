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
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author cem.ozkan
 */
@ManagedBean(name="PageBean")
@SessionScoped
public class PageBean implements Serializable{
    
    private String[] selectedHeaders;
    private String[] selectedMetadatas;
    private String[] selectedStage1;
    private String[] selectedStage2;
    private String[] selectedProvince;
    private String[] selectedSector;

    public PageBean() {
        selectedHeaders =null;
        selectedMetadatas =null;
        selectedProvince =null;
        selectedStage1=null;
        selectedStage2=null;
        selectedSector=null;
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
        try{
        if(selectedProvince[0]==null)
            this.selectedProvince = null;
        else
            this.selectedProvince = selectedProvince;
        }catch(Exception ex){
            this.selectedProvince = null;
        }
    }

    public String[] getSelectedStage1() {
        return selectedStage1;
    }

    public void setSelectedStage1(String[] selectedStage1) {
        try{
        if(selectedStage1[0]==null)
            this.selectedStage1 = null;
        else
            this.selectedStage1 = selectedStage1;
        }catch(Exception ex){
            this.selectedStage1 = null;
        }
    }

    public String[] getSelectedStage2() {
        return selectedStage2;
    }

    public void setSelectedStage2(String[] selectedStage2) {
        try{
        if(selectedStage2[0]==null)
            this.selectedStage2 = null;
        else
            this.selectedStage2 = selectedStage2;
        }catch(Exception ex){
            this.selectedStage2 = null;
        }
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

    public String[] getSelectedSector() {
        return selectedSector;
    }

    public void setSelectedSector(String[] selectedSector) {
        try{
        if(selectedSector[0]==null)
            this.selectedSector = null;
        else
            this.selectedSector = selectedSector;
        }catch(Exception ex){
            this.selectedSector = null;
        }
    }
    
    public Stage1[] getStage1(){
        return GetItems.getStage1();
    }
    
    public Sector[] getSectors(){
        return Properties.getSector();
    }
    
    public Stage2[] getStage2(){
        try{
        if(selectedStage1!=null)
            return GetItems.getStage2(selectedStage1);
        }catch(Exception ex){
            return  new Stage2[1];
        }
        return  new Stage2[1];
    }
    
    public Province[] getProvince(){
        try{
        if(selectedStage2!=null)
            return GetItems.getProvince(selectedStage2);
        }catch(Exception ex){
            return  new Province[1];
        }
        return  new Province[1];
    }
    
    public Property[] getMetadatas(){
        try{
        if(selectedMetadatas==null||selectedMetadatas.length!=0||selectedHeaders[0]!=null)
            return Properties.getMetadata(selectedHeaders);
        }
        catch(Exception ex){
            return new Property[1];
        }
        return  new Property[1];
    }
    
    public Data[] getDatas(){
        return DataFinder.getdata(selectedHeaders, selectedMetadatas, selectedStage1, selectedStage2, selectedProvince,selectedSector);
    }
}

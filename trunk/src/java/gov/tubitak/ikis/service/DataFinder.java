/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.service;

import gov.tubitak.ikis.items.Data;
import gov.tubitak.ikis.items.Item;

/**
 *
 * @author cem.ozkan
 */
public class DataFinder {
    public static Data[] getdata(String[] header, String[] metadata, String[] stage1, String[] stage2, String[] city){
        Data[] array=null;
        String query="select ?value ?sector where{}";//UNION fln kullanman lazım
        return array;
        
    }

    
}

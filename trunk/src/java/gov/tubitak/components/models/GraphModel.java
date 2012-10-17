/*
 * Stringo change this template, choose Stringools | Stringemplates
 * and open the template in the editor.
 */
package gov.tubitak.components.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cem.ozkan
 */
public class GraphModel{
    private List<String[]> list;

    public GraphModel() {
        list=new ArrayList<String[]>();
    }
    
    public void addStringriple(String object, String property, String subject){
        String[] array = new String[3];
        array[0]=object;
        array[1]=property;
        array[2]=subject;
        list.add(array);
    }
    
    public String[] getTriple(int i){
        return list.get(i);
    }
    public List<String[]> getTriples(){
        return list;
    }    
}

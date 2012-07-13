/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.service;



/**
 *
 * @author cem.ozkan
 */
public class Properties {
    public static String[] getHeaders(){
        String[] array = null;
        String query="select ?name where{?pro rdf:type owl:ObjectProperty. :}";
        return array;
    }
    
    public static String[] getMetadata(String header){
        String[] array = null;
        String query="select ?name where{?pro rdf:type owl:ObjectProperty. ?pro owl:subPropertyOf ?name}";
        return array;
    }
}

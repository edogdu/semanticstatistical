/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.service;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import gov.tubitak.ikis.database.Sparql;
import java.util.Iterator;
import java.util.List;
import java.lang.String;



/**
 *
 * @author cem.ozkan
 */
public class Properties {
    public static String[] getHeaders(){
        String[] array = null;
        String query="select ?pro where{?pro rdf:type owl:ObjectProperty. ?pro :propertyType "+"\"header\""+"}";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new String[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            array[i]=next.get("pro").toString();
        }
        return array;
    }
    
    public static String[] getMetadata(String header){
        String[] array = null;
        String query="select ?pro where{?pro rdf:type owl:ObjectProperty. ?pro rdfs:subPropertyOf "+header+".}";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new String[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            array[i]=next.get("pro").toString();
        }
        return array;
    }
}

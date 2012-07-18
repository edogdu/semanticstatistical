/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.service;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import gov.tubitak.ikis.database.Sparql;
import gov.tubitak.ikis.items.Property;
import java.util.Iterator;
import java.util.List;
import java.lang.String;



/**
 *
 * @author cem.ozkan
 */
public class Properties {
    public static Property[] getHeaders(){
        Property[] array;
        String query="select ?pro where{?pro rdf:type owl:ObjectProperty. ?pro :propertyType "+"\"header\""+"}";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new Property[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            array[i]=new Property(next.get("pro").toString(), next.get("pro").toString(), next.get("pro").toString(),next.get("pro").toString());
            i++;
        }
        return array;
    }
    
    public static Property[] getMetadata(String header){
        Property[] array;
        String query="select ?pro ?label where{?pro rdf:type owl:ObjectProperty. ?pro rdfs:subPropertyOf "+header+". ?pro rdfs:label ?label}";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new Property[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            array[i]=new Property(next.get("pro").toString(), next.get("pro").toString(), next.get("label").toString(),next.get("label").toString());
            i++;
        }
        return array;
    }
}

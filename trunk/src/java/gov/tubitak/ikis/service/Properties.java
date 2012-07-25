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
        String query="select ?pro ?label ?id where{?pro rdf:type owl:ObjectProperty. ?pro :propertyType "+"\"header\""+". ?pro rdfs:label ?label. ?pro :id ?id. }";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new Property[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            String test=next.get("id").toString();
            if(test.contains("^^"))
                test=test.replaceAll("\"", "").substring(0, test.indexOf("^^"));
            array[i]=new Property(next.get("pro").toString(), Integer.parseInt(test), next.get("label").toString(),next.get("label").toString());
            i++;
        }
        return array;
    }
    
    public static Property[] getMetadata(String[] header){
        Property[] array;
        String query="select ?pro ?label ?id where{?pro rdf:type owl:ObjectProperty.";
        for (int i = 0; i < header.length; i++) {
            if(i!=header.length-1)
                query+=" {?pro rdfs:subPropertyOf <"+header[i]+">} UNION ";
            if(i==header.length-1)
                query+=" {?pro rdfs:subPropertyOf <"+header[i]+">}. ";
                
        }
        
         query+="?pro rdfs:label ?label. ?pro :id ?id.}";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new Property[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            String test=next.get("id").toString();
            if(test.contains("^^"))
                test=test.replaceAll("\"", "").substring(0, test.indexOf("^^"));
            array[i]=new Property(next.get("pro").toString(), Integer.parseInt(test), next.get("label").toString(),next.get("label").toString());
            i++;
        }
        return array;
    }
    
    public static Property getPropertyByName(String name){
        name=name.substring(name.indexOf("#"));
        Property pro = null;
        String query="select ?pro ?label ?id where{<"+name+"> rdfs:label ?label. <"+name+"> :id ?id.}";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        QuerySolution get = toList.get(0);
        if(!toList.isEmpty()){
            String test=get.get("id").toString();
            if(test.contains("^^"))
                test=test.replaceAll("\"", "").substring(0, test.indexOf("^^"));
             pro= new Property(name,Integer.parseInt(test), get.get("label").toString(), get.get("label").toString());
        }
        return pro;   
    }
}

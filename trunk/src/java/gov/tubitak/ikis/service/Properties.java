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
import gov.tubitak.ikis.items.Sector;
import java.util.Iterator;
import java.util.List;
import java.lang.String;



/**
 *
 * @author cem.ozkan
 */
public class Properties {
    public static Property[] getHeaders(String lang){
        Property[] array;
        String query="select ?pro ?label ?id where{?pro rdf:type owl:ObjectProperty. ?pro :propertyType "+"\"header\""+". ?pro rdfs:label ?label. ?pro :id ?id.FILTER( lang(?label) = \"TR\" )}ORDER BY ?label";
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
            array[i]=new Property(next.get("pro").toString(), Integer.parseInt(test), DataFinder.labelFinder(next.get("pro").toString(), lang),DataFinder.labelFinder(next.get("pro").toString(), lang));
            i++;
        }
        return array;
    }
    
    public static Property[] getMetadata(String[] header, String lang){
        Property[] array;
        String query="select ?pro ?label ?id where{?pro rdf:type owl:ObjectProperty.";
        for (int i = 0; i < header.length; i++) {
            if(i!=header.length-1)
                query+=" {?pro rdfs:subPropertyOf <"+header[i]+">} UNION ";
            if(i==header.length-1)
                query+=" {?pro rdfs:subPropertyOf <"+header[i]+">}. ";
                
        }
        
         query+="?pro rdfs:label ?label. ?pro :id ?id.FILTER( lang(?label) = \""+lang+"\" )}ORDER BY ?label";
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
            array[i]=new Property(next.get("pro").toString(), Integer.parseInt(test), DataFinder.labelFinder(next.get("pro").toString(), "EN"),DataFinder.labelFinder(next.get("pro").toString(), "TR"));
            i++;
        }
        return array;
    }
    
    public static Property getPropertyByName(String name,String lang){
        Property pro = null;
        String query="select ?label ?id where{<"+name+"> rdfs:label ?label. <"+name+"> :id ?id.}";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        QuerySolution get = toList.get(0);
        if(!toList.isEmpty()){
            String test=get.get("id").toString();
            if(test.contains("^^"))
                test=test.replaceAll("\"", "").substring(0, test.indexOf("^^"));
             pro= new Property(name,Integer.parseInt(test), DataFinder.labelFinder(name, lang),DataFinder.labelFinder(name, lang));
        }
        return pro;   
    }
    
    public static Sector[] getSector(){
        Sector[] array;
        String query="select ?pro ?name ?id where{?pro rdf:type :Sector. ?pro rdfs:label ?name.}ORDER BY ?name";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new Sector[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            array[i]=new Sector(next.get("pro").toString(), next.get("name").toString());
            i++;
        }
        return array;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.service;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import gov.tubitak.ikis.database.Sparql;
import gov.tubitak.ikis.items.Data;
import gov.tubitak.ikis.items.Item;
import gov.tubitak.ikis.items.Property;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author cem.ozkan
 */
public class DataFinder {
    public static Data[] getdata(String[] header, String[] metadata, String[] stage1, String[] stage2, String[] city){
        Data[] array = null;
        String query="select ?v ?sector ?stage where{";//UNION fln kullanman lazım
        if(stage1.length>0 && stage2.length>0&& metadata.length>0){
            query+="<"+stage2[0]+"> <"+metadata[0]+"> ?value.";
        }
        else if(stage1.length>0 && stage2.length==0 && metadata.length>0){
            query+="<"+stage1[0]+"> <"+metadata[0]+"> ?value.";
            
        }
        else if(stage1.length==0 && stage2.length==0 && metadata.length>0){
            query+="{?loc rdf:type :Stage1} UNION {?loc rdf:type :Stage2}. ?loc  <"+metadata[0]+"> ?value.";
            
        }
        if(metadata.length>0)
            
        if(stage1.length>0 && stage2.length>0&& metadata.length>0){
        query+="?value <"+metadata[0]+"> ?st. ?st rdfs:label ?stage. ";    
        query+="?value :hasSector ?sector. ?value :value ?v.}";
        
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new Data[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            
            array[i]=new Data(next.get("v").toString(), next.get("stage").toString(), new Property(metadata[0], i, metadata[0], metadata[0]), next.get("sector").toString().substring(next.get("sector").toString().indexOf("#")+1));
            i++;
        }
        }
        return array;
        
    }
    public static String sectorFinder(String sector){
        String query="select ?pro ?label ?id where{<"+sector+"> rdfs:label ?label. <"+sector+"> :id ?id.}";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        if(!toList.isEmpty()){
            QuerySolution get = toList.get(0);
             return get.get("label").toString();
        }
        return ""; 
    }
    
}

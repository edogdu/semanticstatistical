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
        Data[] array;
        String query="select ?value ?sector ?stage where{";//UNION fln kullanman lazım
        if(stage1.length>0 && stage2.length>0&& metadata.length>0){
            query+="<"+stage2[0]+"> <"+metadata[0]+"> ?value.";
        }
        else if(stage1.length>0 && stage2.length==0 && metadata.length>0){
            query+="<"+stage1[0]+"> <"+metadata[0]+"> ?value.";
            
        }
        else if(stage1.length==0 && stage2.length==0 && metadata.length>0){
            query+="{?loc rdf:type :Stage1} UNION {?loc rdf:type :Stage2}. ?loc  <"+metadata[0]+"> ?value.";
            
        }
        query+="?value :hasSector ?sec. ?sec :name ?sector. ?value :value ?value. ?value <"+metadata[0]+"> ?st. ?st :name ?stage.}";
        
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new Data[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            
            array[i]=new Data(next.get("value").toString(), next.get("sector").toString(), new Property(query, i, query, query), next.get("value").toString());
            i++;
        }
        return array;
        
    }

    
}

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
import gov.tubitak.ikis.items.Province;
import gov.tubitak.ikis.items.Stage1;
import gov.tubitak.ikis.items.Stage2;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author cem.ozkan
 */
public class GetItems {
    public static Stage1[] getStage1(){
        Stage1[] array;
        String query="select ?st ?id ?label where{?st rdf:type :Stage1. ?st :id ?id. ?st rdfs:label ?label}";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new Stage1[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            String test=next.get("id").toString();
            array[i]=new Stage1(next.get("st").toString(), test, next.get("label").toString(),next.get("label").toString());
            i++;
        }
        return array;
    }
    
    public static Stage2[] getStage2(String[] stage1){
        Stage2[] array;
        String query="select ?st ?id ?label where{?st rdf:type :Stage2. ?st :id ?id. ?st rdfs:label ?label. ";
        for (int i = 0; i < stage1.length; i++) {
            if(i!=stage1.length-1)
                query+=" {?st :hasRegion <"+stage1[i]+">} UNION ";
            if(i==stage1.length-1)
                query+=" {?st :hasRegion <"+stage1[i]+">}.}";
                
        }
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new Stage2[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            String test=next.get("id").toString();
            array[i]=new Stage2(next.get("st").toString(), test, next.get("label").toString(),next.get("label").toString());
            i++;
        }
        return array;
    }
    
    public static Province[] getProvince(String[] stage2){
        Province[] array;
        String query="select ?st ?id ?label where{?st rdf:type :City. ?st :id ?id. ?st rdfs:label ?label.";
        for (int i = 0; i < stage2.length; i++) {
            if(i!=stage2.length-1)
                query+=" {?st :hasStage2 <"+stage2[i]+">} UNION ";
            if(i==stage2.length-1)
                query+=" {?st :hasStage2 <"+stage2[i]+">}.}";
                
        }
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new Province[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            String test=next.get("id").toString();
            array[i]=new Province(next.get("st").toString(), test, next.get("label").toString(),next.get("label").toString());
            i++;
        }
        return array;
    }
}

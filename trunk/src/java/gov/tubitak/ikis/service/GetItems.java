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
        String query="select ?st ?id where{?st rdf:type :Stage1. ?st :id ?id.";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new Stage1[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            array[i]=new Stage1(next.get("st").toString(), next.get("id").toString(), next.get("st").toString(),next.get("st").toString());
            i++;
        }
        return array;
    }
    
    public static Stage2[] getStage2(Stage1 stage1){
        Stage2[] array;
        String query="select ?st ?name ?id where{?st rdf:type :Stage2. ?st :name ?name. ?st :id ?id. ?st :hasRegion :"+stage1.getName()+"";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new Stage2[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            array[i]=new Stage2(next.get("st").toString(), next.get("id").toString(), next.get("st").toString(),next.get("st").toString());
            i++;
        }
        return array;
    }
    
    public static Province[] getProvince(Stage2 stage2){
        Province[] array;
        String query="select ?st ?name ?id where{?st rdf:type :Province. ?st :name ?name. ?st :id ?id. ?st :hasStage2 :"+stage2.getName()+"";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        array=new Province[toList.size()];
        Iterator<QuerySolution> iterator = toList.iterator();
        int i=0;
        while (iterator.hasNext()) {
            QuerySolution next = iterator.next();
            array[i]=new Province(next.get("st").toString(), next.get("id").toString(), next.get("st").toString(),next.get("st").toString());
            i++;
        }
        return array;
    }
}

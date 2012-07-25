/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.database;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

/**
 *
 * @author cem.ozkan"http://www.tuik.com/tuik#tr2batimarmara"
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String query="select ?pro where{?pro rdf:type owl:ObjectProperty. ?pro :propertyType "+"\"header\""+"}";
//        String query="select ?st ?id ?label where{?st rdf:type :Stage2. ?st :id ?id. ?st rdfs:label ?label. ?st :hasRegion <"+"http://www.tuik.com/tuik#tr2batimarmara"+">.}";
        String query="select ?st ?id ?label where{?st rdf:type :City. ?st :id ?id. ?st rdfs:label ?label. ?st :hasStage2 <"+"http://www.tuik.com/tuik#tr61antalya"+">.}";
        ResultSet search = Sparql.search(query);
        ResultSetFormatter.out(search);
    }
}

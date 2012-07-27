/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.database;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import gov.tubitak.ikis.service.Properties;

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
        //String query="select ?value ?sector ?stage where{<http://www.tuik.com/tuik#tr41bursa> <http://www.tuik.com/tuik#studentCountInEducation> ?value.?value :hasSector ?sector.  ?value <http://www.tuik.com/tuik#studentCountInEducation> ?st. ?st rdfs:label ?stage.}";
        Properties.getPropertyByName("http://www.tuik.com/tuik#classroomCountInEducation");
//        ResultSet search = Sparql.search(query);
//        ResultSetFormatter.out(search);
    }
}

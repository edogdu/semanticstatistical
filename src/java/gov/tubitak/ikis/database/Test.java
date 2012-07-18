/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.database;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

/**
 *
 * @author cem.ozkan
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String query="select ?pro where{?pro rdf:type owl:ObjectProperty. ?pro :propertyType "+"\"header\""+"}";
        String query="select ?pro where{?pro rdf:type owl:ObjectProperty. ?pro rdfs:subPropertyOf "+"<http://www.tuik.com/tuik#economicIndicators>"+".}";
        ResultSet search = Sparql.search(query);
        ResultSetFormatter.out(search);
    }
}

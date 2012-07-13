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
        
        ResultSet search = Sparql.search("SELECT ?a ?st1 WHERE { ?st1 :studentCountInEducation ?a}");
        ResultSetFormatter.out(search);
    }
}

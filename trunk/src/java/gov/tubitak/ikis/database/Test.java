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
//        String query="select ?v ?sector ?year ?stage ?period ?resource "
//                + "where{ {<http://www.tuik.com/tuik#tr1?stanbul> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION {<http://www.tuik.com/tuik#tr2batimarmara> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION {<http://www.tuik.com/tuik#tr3ege> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION {<http://www.tuik.com/tuik#tr4dogumarmara> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION {<http://www.tuik.com/tuik#tr5batianadolu> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION {<http://www.tuik.com/tuik#tr6akdeniz> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION {<http://www.tuik.com/tuik#tr7ortaanadolu> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION {<http://www.tuik.com/tuik#tr8batikaradeniz> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION {<http://www.tuik.com/tuik#tr9dogukaradeniz> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION {<http://www.tuik.com/tuik#trakuzeydoguanadolu> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION {<http://www.tuik.com/tuik#trbortadoguanadolu> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION {<http://www.tuik.com/tuik#trcguneydoguanadolu> <http://www.tuik.com/tuik#classroomCountInEducation> ?value}."
//                + "{<http://www.tuik.com/tuik#trc1gaziantep> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION{<http://www.tuik.com/tuik#trc2sanliurfa> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION{<http://www.tuik.com/tuik#trc3mardin> <http://www.tuik.com/tuik#classroomCountInEducation> ?value}."
//                + "{<http://www.tuik.com/tuik#batman> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION{<http://www.tuik.com/tuik#mardin> <http://www.tuik.com/tuik#classroomCountInEducation> ?value}."
//                + "?value <http://www.tuik.com/tuik#classroomCountInEducation> ?st. "
//                + "?st rdfs:label ?stage. ?value :hasSector ?sector. ?value :value ?v. ?value :year ?year. "
//                + "?value :hasMeeting ?me.?me :name ?period. ?value :hasResource ?re. ?re :name ?resource}";
//        String query="select ?v ?sector ?year ?stage ?period ?resource "
//                + "where{{<http://www.tuik.com/tuik#tr4dogumarmara> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION{<http://www.tuik.com/tuik#tr41bursa> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION{<http://www.tuik.com/tuik#tr42kocaeli> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} UNION"
//                + "{<http://www.tuik.com/tuik#bolu> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION{<http://www.tuik.com/tuik#duzce> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION{<http://www.tuik.com/tuik#kocael?> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION{<http://www.tuik.com/tuik#sakarya> <http://www.tuik.com/tuik#classroomCountInEducation> ?value} "
//                + "UNION{<http://www.tuik.com/tuik#yalova> <http://www.tuik.com/tuik#classroomCountInEducation> ?value}."
//                + "?value <http://www.tuik.com/tuik#classroomCountInEducation> ?st. ?st rdfs:label ?stage. ?value :hasSector ?sector. "
//                + "?value :value ?v. ?value :year ?year. ?value :hasMeeting ?me.?me :name ?period. ?value :hasResource ?re. ?re :name ?resource}";
        String query="select ?p ?label where{?p rdf:type owl:ObjectProperty. ?p rdfs:label ?label" +  ".FILTER( lang(?label) = \"TR\" )}";
//        Properties.getPropertyByName("http://www.tuik.com/tuik#classroomCountInEducation");
        ResultSet search = Sparql.search(query);
        ResultSetFormatter.out(search);
    }
}

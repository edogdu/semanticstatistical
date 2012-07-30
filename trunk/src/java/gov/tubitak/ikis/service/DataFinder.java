/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.service;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import gov.tubitak.ikis.database.Sparql;
import gov.tubitak.ikis.items.*;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author cem.ozkan
 */
public class DataFinder {
//TODO SEKTOR, ÇOKLU metadata ve coklu header
    public static Data[] getdata(String[] header, String[] metadata, String[] stage1, String[] stage2, String[] city, String[] sector) {
        Data[] array = null;
        String query = "select ?v ?sector ?year ?stage ?period ?resource where{";//UNION fln kullanman lazım
        if(header!=null && stage1==null && stage2==null&& city==null &&metadata==null){
            for (int i = 0; i < header.length; i++) {
                    Property[] metadata1 = Properties.getMetadata(header);
                    for (int j = 0; j < metadata1.length; j++) {
                        if(j!=metadata1.length-1){
                            query+="{ :Stage1<" + metadata1[j].getName() + "> ?value} UNION ";
                            query+="{ :Stage2<" + metadata1[j].getName() + "> ?value} UNION ";
                            query+="{ :City<" + metadata1[j].getName() + "> ?value} UNION ";
                        }
                        else if(j==metadata1.length-1){
                            query+="{ :Stage1<" + metadata1[j].getName() + "> ?value} UNION ";
                            query+="{ :Stage2<" + metadata1[j].getName() + "> ?value} UNION ";
                            query+="{ :City<" + metadata1[j].getName() + "> ?value}  ";
                        }
                    }
                    if(i!=header.length-1)
                        query+=" UNION ";
                    else if(i==header.length-1)
                        query+=".?st rdfs:label ?stage.";
            }
        }
        else if(stage1!=null && stage2!=null&& city!=null &&metadata!=null){//sehir secili ise tüm şehirler
            for (int i = 0; i < city.length; i++) {
                if(i!=city.length-1)
                    query+="{<"+city[i]+"> <"+metadata[0]+"> ?value} UNION ";
                else if(i==city.length-1)
                    query+="{<"+city[i]+"> <"+metadata[0]+"> ?value}.";
            }
            query += "?value <" + metadata[0] + "> ?st. ?st rdfs:label ?stage. ";
        }
        else if (stage1 != null && stage2 != null && city == null && metadata != null) {//sehir secili değil ama duzey2 secili ise duzey2 ve onun altındaki sehirler
            query += "{<" + stage2[0] + "> <" + metadata[0] + "> ?value} UNION";
            Province[] stage21 = GetItems.getProvince(stage2[0]);
            for (int i = 0; i < stage21.length; i++) {
                Province st = stage21[i];
                if (i != stage21.length - 1) {
                    query += "{<" + st.getName() + "> <" + metadata[0] + "> ?value} UNION";
                } else if (i == stage21.length - 1) {
                    query += "{<" + st.getName() + "> <" + metadata[0] + "> ?value}.";
                }
            }
            query += "?value <" + metadata[0] + "> ?st. ?st rdfs:label ?stage. ";
        } else if (stage1 != null && stage2 == null && city == null && metadata != null) {//sadece duzey1 secili ise onun altındaki tum duzey 2 ve iller
            query += "{<" + stage1[0] + "> <" + metadata[0] + "> ?value} UNION";
            Stage2[] stage21 = GetItems.getStage2(stage1);
            for (int i = 0; i < stage21.length; i++) {
                Stage2 st = stage21[i];
                query += "{<" + st.getName() + "> <" + metadata[0] + "> ?value} UNION";
                Province[] province = GetItems.getProvince(st.getName());
                for (int j = 0; j < province.length; j++) {
                    Province province1 = province[j];
                    if (j != province.length - 1) {
                        query += "{<" + province1.getName() + "> <" + metadata[0] + "> ?value} UNION";
                    } else if (j == province.length - 1) {
                        query += "{<" + province1.getName() + "> <" + metadata[0] + "> ?value} ";
                    }
                }
                if(i==stage21.length-1)
                    query+=".";
                else if(i!=stage21.length-1)
                    query+="UNION";
            }
            query += "?value <" + metadata[0] + "> ?st. ?st rdfs:label ?stage. ";
        } else if (stage1 == null && stage2 == null && city == null && metadata != null) { //sadece ustveri secili ise o ust veriyi kullanan tum stageler
            query += "?value <" + metadata[0] + "> ?st. ?st rdfs:label ?stage. ";
        }

        if (header != null ||metadata != null || stage1 != null || stage2 != null || city != null|| sector != null) {
            if(sector!=null)
                query+="?value :hasSector <"+sector[0]+">.";//TODO KONTROL ET COKLU DENE SECTORUN VALUE İLİSKİSİ VARSA ONU KULLANARAK COKLU YAP
            query += "?value :hasSector ?sector. ?value :value ?v. ?value :year ?year. ?value :hasMeeting ?me.?me :name ?period. ?value :hasResource ?re. ?re :name ?resource}";
            
            ResultSet search = Sparql.search(query);
            List<QuerySolution> toList = ResultSetFormatter.toList(search);
            array = new Data[toList.size()];
            Iterator<QuerySolution> iterator = toList.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                QuerySolution next = iterator.next();

                array[i] = new Data(next.get("v").toString(), next.get("stage").toString(), Properties.getPropertyByName(metadata[0]), next.get("sector").toString().substring(next.get("sector").toString().indexOf("#") + 1), Properties.getPropertyByName(header[0]).getTrLabel(), next.get("year").toString(), next.get("period").toString().replace("Toplant?s?", ""), next.get("resource").toString());
                i++;
            }
        }
        return array;

    }

    public static String sectorFinder(String sector) {
        String query = "select ?pro ?label ?id where{<" + sector + "> rdfs:label ?label.}";
        ResultSet search = Sparql.search(query);
        List<QuerySolution> toList = ResultSetFormatter.toList(search);
        if (!toList.isEmpty()) {
            QuerySolution get = toList.get(0);
            return get.get("label").toString();
        }
        return "";
    }
}

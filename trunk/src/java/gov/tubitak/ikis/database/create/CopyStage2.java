/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.database.create;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import gov.tubitak.ikis.database.Sparql;
import gov.tubitak.ikis.database.Sparql;
import gov.tubitak.tuik.exceptions.DatabaseConnectException;
import gov.tubitak.tuik.exceptions.DatabaseWriteOrDeleteException;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import jxl.*;
import jxl.read.biff.BiffException;

/**
 *
 * @author cem.ozkan
 */
public class CopyStage2 {

    private static String RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private static String TUIK = "http://www.tuik.com/tuik#";
    private static String RDFS = "http://www.w3.org/2000/01/rdf-schema#";

    public static void read() throws IOException {
        String VERI_GIR_IL_ID;//0
        String VERI_GIR_IL;//1
        String VERI_GIR_IL_IBBS;//2  id
        String VERI_GIR_DUZ2_ID;//5
        String VERI_GIR_DUZ2;//6 stage2 name
        String VERI_GIR_DUZ2_IBBS;//7 stage2 id
        String DUZEY2_IL_LISTESI;//8 stage2 hasProvince
        String VERI_GIR_DUZ1_ID;//9 stage id
        String VERI_GIR_DUZ1;//10 stage name
        String VERI_GIR_DUZ1_IBBS;//11 id
        String YIL;//12 year
        String TOP_DONEMI_ID;//13 id
        String TOP_DONEMI;//14 period
        String IL_KOOR_TOP_ID; //16 id
        String IL_KOOR_TOP;      //17  meeting name
        String IL_KOOR_TOP_TARIHI;//18 date
        String BASLIK_ID;//19 id
        String BASLIK_ADI;//20 name
        String USTVERI_ID;//21 id
        String USTVERI_ADI;//22 hasValue altPropertysi rdfs:label
        String USTVERI_ILI_ID;//24 id
        String USTVERI_ILI;//25 name
        String USTVERI_ACIKLAMASI;//27 hasValue altPropertysi rdfs:comment
        String USTVERI_DURUMU;//28 status
        String USTVERI_FORM_TIPI;//29 formtype
        String USTVERI_SAGLANMA_SEKLI;//30 gatherType
        String ALABILECEGI_EN_AZ_DEGER;//32  minValue
        String ALABILECEGI_EN_FAZLA_DEGER;//33 maxValue
        String OLCU_BIRIMI_ID;//34 measure id
        String OLCU_BIRIMI;//35 measure
        String SUTUN_ID;//36
        String SUTUN;//37 type
        String SEKTOR_ID;//39
        String SEKTOR;//40 sector
        String SEKTOR_GORUNUM_SIRASI;//41
        String TUM_SEKTOR_LISTESI;//42
        String VERI_GIR_SUT;//44
        String KURUM_ID;//46 enterprise id
        String KURUM;//47 enterprise
        String BAGLI_OL_KURUM_ID;//48 hasSubOrganization
        String BAGLI_OL_KURUM;//49 hasSubOrganization
        String KURUM_VERISI;//50 enterpriseValue
        String IL_VERISI;//52 provinceValue
        String DUZEY2_VERISI;//53 stage2Value
        String DUZEY1_VERISI;//54 stage1Value
        String TURKIYE_VERISI;//55 turkeyValue
        String IL_VERISI_DUZEY2_SIRASI;//56 provinceStage2Rank
        String IL_VERISI_DUZEY1_SIRASI;//57 provinceStage1Rank
        String IL_VERISI_TURKIYE_SIRASI;//58 provinceTurkeyRank
        String DUZ2_VER_DUZ1_SIRASI;//59 stage2Stage1Rank
        String DUZ2_VER_TR_SIRASI;//60 stage2TurkeyRank
        String DUZ1_VER_TR_SIRASI;//61 stage1TurkeyRank
        Double IL_VER_DUZ2_PAYI;//62 provinceStage2Portion
        Double IL_VER_DUZ1_PAYI;//63 provinceStage1Portion
        Double IL_VERISI_TURKIYE_PAYI;//64 provinceTurkeyPortion
        Double DUZ2_VER_DUZ1_PAYI;//65 stage2Stage1Portion
        Double DUZ2_VER_TR_PAYI;//66 stage2TurkeyPortion
        Double DUZ1_VER_TR_PAYI;//67 stage1TurkeyPortion
        String IL_BAZINDA_TURKIYE_MIN;//68 provinceTurkeyMin
        String IL_BAZINDA_TURKIYE_MAKS;//69 provinceTurkeyMax
        String IL_BAZINDA_TURKIYE_ORT;//70 provinceTurkeyAvg
        String ENUM_VAS_TIPI;//74 collectType

        File file = new File("C:\\Users\\cem.ozkan\\Documents\\ikis - Sektor Verileri ve Aciklamalari\\duzey2.xls");
        WorkbookSettings ws = new WorkbookSettings();
        Workbook w;
        try {
            ws.setEncoding("Windows-1254");//ENCODİNG bu olmalı yoksa türkçe karakterlerde hata oluyo,
            ws.setAutoFilterDisabled(true);
            w = Workbook.getWorkbook(file, ws);
            Sheet sheet = w.getSheet(0);
            for (int j = 1; j < sheet.getRows(); j++) {

                VERI_GIR_DUZ2_ID = convert(sheet.getCell(0, j).getContents());//0
                VERI_GIR_DUZ2 = convert(sheet.getCell(1, j).getContents());//1
                VERI_GIR_DUZ2_IBBS = convert(sheet.getCell(2, j).getContents());//2
                DUZEY2_IL_LISTESI = convert(sheet.getCell(3, j).getContents());
                String[] iller = convert(DUZEY2_IL_LISTESI).split(",");
                try {
                    Sparql.insertProperty(TUIK + VERI_GIR_DUZ2.toLowerCase().replaceAll("-", ""), RDF + "type", TUIK + "Stage2");
                    Sparql.insertLiteral(TUIK + VERI_GIR_DUZ2.toLowerCase().replaceAll("-", ""), TUIK + "id", VERI_GIR_DUZ2_IBBS);
                    Sparql.insertLiteral(TUIK + VERI_GIR_DUZ2.toLowerCase().replaceAll("-", ""), RDFS + "label", VERI_GIR_DUZ2);
                    ResultSet search = Sparql.search("select ?name where{?stage rdf:type :Stage1. ?stage rdfs:label ?name. ?stage :id \"" + VERI_GIR_DUZ2_IBBS.substring(0, 3) + "\".}");
                    List<QuerySolution> toList = ResultSetFormatter.toList(search);
                    if (!toList.isEmpty()) {
                        String name = toList.get(0).get("name").toString();
                        Sparql.insertProperty(TUIK + name.toLowerCase().replaceAll("-", ""), TUIK + "hasStage2", TUIK+VERI_GIR_DUZ2.toLowerCase().replaceAll("-", ""));
                        Sparql.insertProperty(TUIK + VERI_GIR_DUZ2.toLowerCase().replaceAll("-", ""), TUIK + "hasRegion", TUIK + name.toLowerCase().replaceAll("-", ""));
                    }

                    for (int i = 0; i < iller.length; i++) {
                        String string = iller[i];
                        String toLowerCase = string.toLowerCase();
                        search = Sparql.search("select ?id where{?city rdf:type :City. ?city rdfs:label \"" + string+ "\". ?city :id ?id}");
                        toList = ResultSetFormatter.toList(search);
                        if (!toList.isEmpty()) {
                            Sparql.insertProperty(TUIK + VERI_GIR_DUZ2.toLowerCase().replaceAll("-", ""), TUIK + "hasCity", TUIK + string.toLowerCase());
                            Sparql.insertProperty(TUIK + string.toLowerCase(), TUIK + "hasStage2", TUIK + VERI_GIR_DUZ2.toLowerCase().replaceAll("-", ""));
                        }
                    }
                } catch (DatabaseConnectException ex) {
                    Logger.getLogger(CopyProvince.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DatabaseWriteOrDeleteException ex) {
                    Logger.getLogger(CopyProvince.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println(VERI_GIR_DUZ2_ID + " " + VERI_GIR_DUZ2 + " " + VERI_GIR_DUZ2_IBBS);
            }              
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
    private static String convert(String str) {
        str=str.replaceAll("İ", "I");
        str=str.replaceAll(" ", "");
        str=str.replaceAll("Ç", "C");
        str=str.replaceAll("Ü", "U");
        str=str.replaceAll("Ğ", "G");
        str=str.replaceAll("Ş", "S");
        str=str.replaceAll("Ö", "O");
        str=str.replaceAll("ç", "c");
        str=str.replaceAll("ü", "u");
        str=str.replaceAll("ğ", "g");
        str=str.replaceAll("ş", "s");
        str=str.replaceAll("ö", "o");
        str=str.replaceAll("ı", "i");
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();
        String s = null ;
        try {
            // Convert a string to ISO-LATIN-1 bytes in a ByteBuffer
            // The new ByteBuffer is ready to be read.
            ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(str));
            CharBuffer cbuf = decoder.decode(bbuf);
            s = cbuf.toString();

        } catch (CharacterCodingException ex) {
            Logger.getLogger(CopyProvince.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            read();
        } catch (IOException ex) {
            Logger.getLogger(CopyProvince.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

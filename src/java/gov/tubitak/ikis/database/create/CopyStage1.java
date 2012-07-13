/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.database.create;

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
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import jxl.*;
import jxl.read.biff.BiffException;

/**
 *
 * @author cem.ozkan
 */
public class CopyStage1 {

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

        File file = new File("C:\\Users\\cem.ozkan\\Documents\\ikis - Sektor Verileri ve Aciklamalari\\duzey1.xls");
        WorkbookSettings ws = new WorkbookSettings();
        Workbook w;
        try {
            ws.setEncoding("Windows-1254");//ENCODİNG bu olmalı yoksa türkçe karakterlerde hata oluyo,
            ws.setAutoFilterDisabled(true);
            w = Workbook.getWorkbook(file, ws);
            Sheet sheet = w.getSheet(0);
            for (int j = 1; j < sheet.getRows(); j++) {

                VERI_GIR_DUZ1_ID = convert(sheet.getCell(0, j).getContents());//0
                VERI_GIR_DUZ1 = convert(sheet.getCell(1, j).getContents());//1
                VERI_GIR_DUZ1_IBBS = convert(sheet.getCell(2, j).getContents());//2
                try {
                    Sparql.insertProperty(TUIK + VERI_GIR_DUZ1.toLowerCase().replaceAll("-", ""), RDF + "type", TUIK + "Stage1");
                    Sparql.insertLiteral(TUIK + VERI_GIR_DUZ1.toLowerCase().replaceAll("-", ""), TUIK + "id", VERI_GIR_DUZ1_IBBS);
                    Sparql.insertLiteral(TUIK + VERI_GIR_DUZ1.toLowerCase().replaceAll("-", ""), RDFS + "label", VERI_GIR_DUZ1);
                } catch (DatabaseConnectException ex) {
                    Logger.getLogger(CopyProvince.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DatabaseWriteOrDeleteException ex) {
                    Logger.getLogger(CopyProvince.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println(VERI_GIR_DUZ1_ID + " " + VERI_GIR_DUZ1 + " " + VERI_GIR_DUZ1_IBBS);
            }
//                for (int j = 1; j < sheet.getRows(); j++) {
//                    
//                    VERI_GIR_IL_ID = sheet.getCell(0, j).getContents();//0
//                    VERI_GIR_IL = sheet.getCell(1, j).getContents();//1
//                    VERI_GIR_IL_IBBS = sheet.getCell(2, j).getContents();//2
//                    VERI_GIR_DUZ2_ID = sheet.getCell(5, j).getContents();//5
//                    VERI_GIR_DUZ2 = sheet.getCell(6, j).getContents();//6
//                    VERI_GIR_DUZ2_IBBS = sheet.getCell(7, j).getContents();//7
//                    DUZEY2_IL_LISTESI = sheet.getCell(8, j).getContents();//8
//                    VERI_GIR_DUZ1_ID = sheet.getCell(9, j).getContents();//9
//                    VERI_GIR_DUZ1 = sheet.getCell(10, j).getContents();//10
//                    VERI_GIR_DUZ1_IBBS = sheet.getCell(11, j).getContents();//11
//                    YIL = sheet.getCell(12, j).getContents();//12
//                    TOP_DONEMI_ID = sheet.getCell(13, j).getContents();//13
//                    TOP_DONEMI = sheet.getCell(14, j).getContents();//14
//                    IL_KOOR_TOP_ID = sheet.getCell(16, j).getContents(); //16
//                    IL_KOOR_TOP = sheet.getCell(17, j).getContents();      //17  
//                    IL_KOOR_TOP_TARIHI = sheet.getCell(18, j).getContents();//18
//                    BASLIK_ID = sheet.getCell(19, j).getContents();//19
//                    BASLIK_ADI = sheet.getCell(20, j).getContents();//20
//                    USTVERI_ID = sheet.getCell(21, j).getContents();//21
//                    USTVERI_ADI = sheet.getCell(22, j).getContents();//22
//                    USTVERI_ILI_ID = sheet.getCell(24, j).getContents();//24
//                    USTVERI_ILI = sheet.getCell(25, j).getContents();//25
//                    USTVERI_ACIKLAMASI = sheet.getCell(27, j).getContents();//27
//                    USTVERI_DURUMU = sheet.getCell(28, j).getContents();//28
//                    USTVERI_FORM_TIPI = sheet.getCell(29, j).getContents();//29
//                    USTVERI_SAGLANMA_SEKLI = sheet.getCell(30, j).getContents();//30
//                    ALABILECEGI_EN_AZ_DEGER = sheet.getCell(32, j).getContents();//32
//                    ALABILECEGI_EN_FAZLA_DEGER = sheet.getCell(33, j).getContents();//33
//                    OLCU_BIRIMI_ID = sheet.getCell(34, j).getContents();//34
//                    OLCU_BIRIMI = sheet.getCell(35, j).getContents();//35
//                    SUTUN_ID = sheet.getCell(36, j).getContents();//36
//                    SUTUN = sheet.getCell(37, j).getContents();//37
//                    SEKTOR_ID = sheet.getCell(39, j).getContents();//39
//                    SEKTOR = sheet.getCell(40, j).getContents();//40
//                    SEKTOR_GORUNUM_SIRASI = sheet.getCell(41, j).getContents();//41
//                    TUM_SEKTOR_LISTESI = sheet.getCell(42, j).getContents();//42
//                    VERI_GIR_SUT = sheet.getCell(44, j).getContents();//44
//                    KURUM_ID = sheet.getCell(46, j).getContents();//46
//                    KURUM = sheet.getCell(47, j).getContents();//47
//                    BAGLI_OL_KURUM_ID = sheet.getCell(48, j).getContents();//48
//                    BAGLI_OL_KURUM = sheet.getCell(49, j).getContents();//49
//                    KURUM_VERISI = sheet.getCell(50, j).getContents();//50
//                    IL_VERISI =((NumberCell) sheet.getCell(52, j)).getContents();//52
//                    DUZEY2_VERISI = sheet.getCell(53, j).getContents();//53
//                    DUZEY1_VERISI = sheet.getCell(54, j).getContents();//54
//                    TURKIYE_VERISI = sheet.getCell(55, j).getContents();//55
//                    IL_VERISI_DUZEY2_SIRASI = sheet.getCell(56, j).getContents();//56
//                    IL_VERISI_DUZEY1_SIRASI = sheet.getCell(57, j).getContents();//57
//                    IL_VERISI_TURKIYE_SIRASI = sheet.getCell(58, j).getContents();//58
//                    DUZ2_VER_DUZ1_SIRASI = sheet.getCell(59, j).getContents();//59
//                    DUZ2_VER_TR_SIRASI = sheet.getCell(60, j).getContents();//60
//                    DUZ1_VER_TR_SIRASI =sheet.getCell(61, j).getContents();//61
//                    IL_VER_DUZ2_PAYI = ((NumberCell)sheet.getCell(62, j)).getValue();//62
//                    IL_VER_DUZ1_PAYI = ((NumberCell)sheet.getCell(63, j)).getValue();//63
//                    IL_VERISI_TURKIYE_PAYI = ((NumberCell)sheet.getCell(64, j)).getValue();//64
//                    DUZ2_VER_DUZ1_PAYI = ((NumberCell)sheet.getCell(65, j)).getValue();//65
//                    DUZ2_VER_TR_PAYI = ((NumberCell)sheet.getCell(66, j)).getValue();//66
//                    DUZ1_VER_TR_PAYI = ((NumberCell)sheet.getCell(67, j)).getValue();//67
//                    IL_BAZINDA_TURKIYE_MIN = sheet.getCell(68, j).getContents();//68
//                    IL_BAZINDA_TURKIYE_MAKS = sheet.getCell(69, j).getContents();//69
//                    IL_BAZINDA_TURKIYE_ORT = sheet.getCell(70, j).getContents();//70
//                    ENUM_VAS_TIPI = sheet.getCell(74, j).getContents();//74
//
                  
//                    System.out.println(VERI_GIR_IL_ID + " " + VERI_GIR_IL + " " + VERI_GIR_IL_IBBS + " " + VERI_GIR_DUZ2_ID + " "
//                            + VERI_GIR_DUZ2 + " " + VERI_GIR_DUZ2_IBBS + " " + DUZEY2_IL_LISTESI + " " + VERI_GIR_DUZ1_ID + " "
//                            + VERI_GIR_DUZ1 + " " + VERI_GIR_DUZ1_IBBS + " " + YIL + " " + TOP_DONEMI_ID + " " + TOP_DONEMI + " " + IL_KOOR_TOP_ID + " "
//                            + IL_KOOR_TOP + " " + IL_KOOR_TOP_TARIHI + " " + BASLIK_ID + " " + BASLIK_ADI + " " + USTVERI_ID + " "
//                            + USTVERI_ADI + " " + USTVERI_ILI_ID + " " + USTVERI_ILI + " " + USTVERI_ACIKLAMASI + " " + USTVERI_DURUMU + " "
//                            + USTVERI_FORM_TIPI + " " + USTVERI_SAGLANMA_SEKLI + " " + ALABILECEGI_EN_AZ_DEGER + " " + ALABILECEGI_EN_FAZLA_DEGER + " "
//                            + OLCU_BIRIMI_ID + " " + OLCU_BIRIMI + " " + SUTUN_ID + " " + SUTUN + " " + SEKTOR_ID + " " + SEKTOR + " "
//                            + SEKTOR_GORUNUM_SIRASI + " " + TUM_SEKTOR_LISTESI + " " + VERI_GIR_SUT + " " + KURUM_ID + " " + KURUM + " "
//                            + BAGLI_OL_KURUM_ID + " " + BAGLI_OL_KURUM + " " + KURUM_VERISI + " " + IL_VERISI + " " + DUZEY2_VERISI + " "
//                            + DUZEY1_VERISI + " " + TURKIYE_VERISI + " " + IL_VERISI_DUZEY2_SIRASI + " " + IL_VERISI_DUZEY1_SIRASI + " "
//                            + IL_VERISI_TURKIYE_SIRASI + " " + DUZ2_VER_DUZ1_SIRASI + " " + DUZ2_VER_TR_SIRASI + " " + DUZ1_VER_TR_SIRASI + " "
//                            + IL_VER_DUZ2_PAYI + " " + IL_VER_DUZ1_PAYI + " " + IL_VERISI_TURKIYE_PAYI + " " + DUZ2_VER_DUZ1_PAYI + " "
//                            + DUZ2_VER_TR_PAYI + " " + DUZ1_VER_TR_PAYI + " " + IL_BAZINDA_TURKIYE_MIN + " " + IL_BAZINDA_TURKIYE_MAKS + " "
//                            + IL_BAZINDA_TURKIYE_ORT + " " + ENUM_VAS_TIPI);
//                }
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
        String s = null;
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

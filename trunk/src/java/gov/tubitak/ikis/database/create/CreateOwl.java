/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.database.create;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.Store;
import com.hp.hpl.jena.sdb.StoreDesc;
import com.hp.hpl.jena.sdb.sql.SDBConnection;
import com.hp.hpl.jena.sdb.store.StoreFactory;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cem.ozkan
 */
public class CreateOwl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        CreateDatabase.main(args);
//        CopySector.main(args);
//        CopyProperty.main(args);
//        CopyProvince.main(args);
//        CopyStage1.main(args);
//        CopyStage2.main(args);
//        CopyEnterprise.main(args);
//        CopyData.main(args);
        
        StoreDesc str = new StoreDesc("layout2/index", "mysql");
        SDBConnection conn = new SDBConnection("jdbc:mysql://localhost/ikis", "ikis", "ikis");
        Store store = StoreFactory.create(str, conn);
        Dataset data = SDBFactory.connectDataset(store);
        File file = new File("ikiswithind.owl");
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateOwl.class.getName()).log(Level.SEVERE, null, ex);
        }
        data.getDefaultModel().write(os, "RDF/XML-ABBREV");
        try {
            os.close();
        } catch (IOException ex) {
            Logger.getLogger(CreateOwl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

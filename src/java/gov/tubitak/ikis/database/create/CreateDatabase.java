/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.database.create;

import sdb.sdbconfig;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFReader;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.Store;
import com.hp.hpl.jena.sdb.StoreDesc;
import com.hp.hpl.jena.sdb.sql.SDBConnection;
import com.hp.hpl.jena.sdb.store.StoreFactory;
import com.hp.hpl.jena.util.FileManager;
import com.mysql.jdbc.Statement;
import java.io.InputStream;

/**
 *
 * @author Ethem Cem Ozkan
 * @email ethemcem.ozkan@gmail.com
 * No 07110604
 */
public class CreateDatabase {
    	public static void test(){
		Model model =ModelFactory.createDefaultModel();
		InputStream in=FileManager.get().open("test.owl");
		model.read(in, null);
		System.out.println(model.supportsTransactions());
		//model.write(System.out);
		
	}
	
	public static void main(String[] arg){
		
		StoreDesc str=new StoreDesc("layout2/index", "mysql");
		SDBConnection conn=new SDBConnection("jdbc:mysql://localhost/ikis", "ikis", "ikis");
		Store store = StoreFactory.create(str, conn);
		arg=new String[3];
		arg[0]="--sdb";
		arg[1]="sdb.ttl";
		arg[2]="--format";
		sdbconfig.main(arg);
		Dataset data=SDBFactory.connectDataset(store);
		
        Model model = SDBFactory.connectDefaultModel(store) ;
        InputStream in=FileManager.get().open("tuikV3.owl");
        model.read(in, null);
        data.getDefaultModel().add(model);
        data.getDefaultModel().write(System.out, "N-TRIPLE");
        StmtIterator sIter = model.listStatements() ;
        sIter.close() ;
        store.close() ;
	}
}

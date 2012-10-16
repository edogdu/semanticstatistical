package gov.tubitak.ikis.database;


import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sdb.SDBFactory;
import com.hp.hpl.jena.sdb.Store;
import com.hp.hpl.jena.sdb.StoreDesc;
import com.hp.hpl.jena.sdb.sql.JDBC;
import com.hp.hpl.jena.sdb.sql.SDBConnection;
import com.hp.hpl.jena.sdb.store.DatasetStore;
import gov.tubitak.tuik.exceptions.DatabaseConnectException;
import gov.tubitak.tuik.exceptions.DatabaseWriteOrDeleteException;
import gov.tubitak.tuik.exceptions.MysqlConnectException;
import gov.tubitak.tuik.exceptions.SdbConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ethem Cem Ozkan
 * Email cemozkan89@hotmail.com
 * No 07110604
 */
public class Sparql {
    private static Dataset DATASET;
    private static Store store;
    private static Connection watcher;
    /**
     * String olarak verilen sparql sorgusunu iÅŸler ve sonuÃ§ dÃ¶ndÃ¼rÃ¼r.
     * @param queryString
     * @return 
     */
    public static ResultSet search(String queryString){
	  String queryWithPrefix=NameSpaces.RDF+" "+NameSpaces.TUIK+" "+NameSpaces.FOAF+" "+NameSpaces.RDFS+" "+NameSpaces.OWL+" "+queryString;
	  try {
		makeSDBConnection();
                Query query = QueryFactory. create(queryWithPrefix) ;
                QueryExecution quex=QueryExecutionFactory.create(query,DATASET);
                ResultSet execSelect = quex.execSelect();
                DATASET.close();
                store.close();
                return execSelect;
	  } catch (Exception ex) {
		Logger.getLogger(Sparql.class.getName()).log(Level.SEVERE, null, ex);
	  }
          return null;
          
    }
    
    /**
     * Servera 3lÃ¼ resource ekler
     * @param res
     * @param pro
     * @param obj
     * @return
     * @throws DatabaseWriteOrDeleteException 
     */
    public static boolean insertProperty(String res,String pro,String obj) throws DatabaseWriteOrDeleteException{
	  try {
		makeSDBConnection();
	  } catch (Exception ex) {
		throw new DatabaseWriteOrDeleteException();
	  }
	  Model model=DATASET.getDefaultModel();
	  Resource resource=model.createResource(res);
                        Property p=model.createProperty(pro);
                        Property p2=model.createProperty(obj);
                        resource.addProperty(p, p2);
	  DATASET.close();
	  store.close();
          close();
                        garbageCollector();
	  return true;
    }
    
    /**
     * servera 3lÃ¼ string ekler.
     * @param res
     * @param pro
     * @param obj
     * @return
     * @throws DatabaseWriteOrDeleteException
     * @throws DatabaseConnectException 
     */
    public static boolean insertLiteral(String res,String pro,int obj) throws DatabaseWriteOrDeleteException, DatabaseConnectException{
            try {
                makeSDBConnection();
            } catch (Exception ex) {
                throw new DatabaseConnectException();
            }
            Model model=DATASET.getDefaultModel();
            Resource resource=model.createResource(res);
            Property p=model.createProperty(pro);
             resource.addLiteral(p, obj); 
            DATASET.close();
            store.close();
            close();
            garbageCollector();
            return true;
    }
    
    
     /**
     * servera 3lÃ¼ string ekler.
     * @param res
     * @param pro
     * @param obj
     * @return
     * @throws DatabaseWriteOrDeleteException
     * @throws DatabaseConnectException 
     */
    public static boolean insertLiteralWithLang(String res,String pro,String obj) throws DatabaseWriteOrDeleteException, DatabaseConnectException{
            try {
                makeSDBConnection();
            } catch (Exception ex) {
                throw new DatabaseConnectException();
            }
            Model model=DATASET.getDefaultModel();
            Resource resource=model.createResource(res);
            Property p=model.createProperty(pro);
             resource.addLiteral(p, obj); 
            DATASET.close();
            store.close();
            close();
            garbageCollector();
            return true;
    }
    
    /**
     * servera 3lÃ¼ string ekler.
     * @param res
     * @param pro
     * @param obj
     * @return
     * @throws DatabaseWriteOrDeleteException
     * @throws DatabaseConnectException 
     */
    public static boolean insertLiteral(String res,String pro,String obj, String lang) throws DatabaseWriteOrDeleteException, DatabaseConnectException{
        try {
            makeSDBConnection();
        } catch (Exception ex) {
            throw new DatabaseConnectException();
        }
        Model model=DATASET.getDefaultModel();
        Resource resource=model.createResource(res);
        Literal literal = model.createLiteral(obj, lang);
        Property p=model.createProperty(pro);
        resource.addLiteral(p, literal); 
        DATASET.close();
        store.close();
        close();
        garbageCollector();
        return true;
    }
    
    /**
     * servera 3lÃ¼ int ekler
     * @param res
     * @param pro
     * @param obj
     * @return
     * @throws DatabaseWriteOrDeleteException
     * @throws DatabaseConnectException 
     */
    public static boolean insertLiteral(String res,String pro,String obj) throws DatabaseWriteOrDeleteException, DatabaseConnectException{
            try {
                makeSDBConnection();
            } catch (Exception ex) {
                throw new DatabaseConnectException();
            }
            Model model=DATASET.getDefaultModel();
            Resource resource=model.createResource(res);
            Property p=model.createProperty(pro);
            resource.addProperty(p, obj); 
            DATASET.close();
            close();
            garbageCollector();
            return true;
    }
    
    public static void updateResource(String res,String pro,String newValue) throws DatabaseWriteOrDeleteException, DatabaseConnectException{
	  try {
		makeSDBConnection();
	  } catch (Exception ex) {
		throw new DatabaseConnectException();	  
	  }
	  Model model=DATASET.getDefaultModel();
	  if(model.containsResource(model.getResource(res.replace("id", "c")))==false||model.containsResource(model.getProperty(pro))==false)
		throw new DatabaseWriteOrDeleteException();
	  Resource resource=model.createResource(res.replace("id", "c"));
	  Property p=model.getProperty(pro);
	  removeResource(res, pro, resource.getProperty(p).getString());
	  Property p2=model.createProperty(newValue);
	  resource.addProperty(p, p2);
    }
    
    public static void updateLiteral(String res,String pro,String newValue) throws DatabaseWriteOrDeleteException, DatabaseConnectException{
	  try {
		makeSDBConnection();
	  } catch (Exception ex) {
		throw new DatabaseConnectException();
	  }
	  Model model=DATASET.getDefaultModel();
	  if(model.containsResource(model.getResource(res.replace("id", "c")))==false||model.containsResource(model.getProperty(pro))==false)
		throw new DatabaseWriteOrDeleteException();
	  Resource resource=model.getResource(res.replace("id", "c"));
	  Property p=model.getProperty(pro);
	  removeResource(res, pro, resource.getProperty(p).getString());
	  resource.addProperty(p, newValue);
    }
    
    /**
     * serverdan resource 3lÃ¼sÃ¼ siler
     * @param res
     * @param pro
     * @param obj
     * @throws DatabaseWriteOrDeleteException
     * @throws DatabaseConnectException 
     */
    public static void removeResource(String res,String pro,String obj) throws DatabaseWriteOrDeleteException, DatabaseConnectException{
            try {
                makeSDBConnection();
            } catch (Exception ex) {
                throw new DatabaseConnectException();
            }
            Model model=DATASET.getDefaultModel();
            if(model.containsResource(model.getResource(res))==false||model.containsResource(model.getProperty(pro))==false||model.containsResource(model.getProperty(obj))==false){
                throw new DatabaseWriteOrDeleteException();//CONTAÄ°NS RESOURCE METODUNA BAK
            }
            Resource resource=model.getResource(res);
            Property p=model.createProperty(pro);
            //resource.removeAll(p);
            Property p2=model.createProperty(obj);
            model.remove(resource, p, p2);
            DATASET.close();
            try {
            store.getConnection().getSqlConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Sparql.class.getName()).log(Level.SEVERE, null, ex);
        }
            garbageCollector();
    }
    
    /**
     * serverdan literal 3lÃ¼sÃ¼ siler.
     * @param res
     * @param pro
     * @throws DatabaseWriteOrDeleteException
     * @throws DatabaseConnectException 
     */
    public static void removeLiteral(String res,String pro) throws DatabaseWriteOrDeleteException, DatabaseConnectException{
            try {
                makeSDBConnection();
            } catch (Exception ex) {
                throw new DatabaseConnectException();
            }
            Model model=DATASET.getDefaultModel();
            if(model.containsResource(model.getResource(res))==false||model.containsResource(model.getProperty(pro))==false){
                throw new DatabaseWriteOrDeleteException();//CONTAÄ°NS RESOURCE METODUNA BAK
            }
            Resource resource=model.getResource(res);
            Property p=model.getProperty(pro);
            resource.removeAll(p);
            //String test=resource.getProperty(p).getString();
            //model.remove(resource, p, model.createLiteral(resource.getProperty(p).getString()));
            DATASET.close();
            try {
            store.getConnection().getSqlConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(Sparql.class.getName()).log(Level.SEVERE, null, ex);
        }
            garbageCollector();
    }
    
    /**
     * jdbc baÄŸlantÄ±sÄ± kurar.
     * @return
     * @throws Exception 
     */
    private static SDBConnection makeConnection() throws MysqlConnectException{ 
            SDBConnection conn=new SDBConnection(ConnectionPool.getConnection()) ;
            if(conn==null)
                throw new MysqlConnectException("Database baÄŸlantÄ± hatasÄ±");

        return conn;
    }
    
    /**
     * sdb ile baÄŸlantÄ± kurar.
     * @throws Exception 
     */
    private static void makeSDBConnection() throws SdbConnectionException{
	  JDBC.loadDriverMySQL();
	  StoreDesc storeDesc = new StoreDesc("layout2/index","mysql") ;
	  try {
		store=SDBFactory.connectStore(makeConnection(), storeDesc);
		DATASET=DatasetStore.create(store);
	  } catch (Exception ex) {
		throw new SdbConnectionException("SDB baÄŸlantÄ± hatasÄ±");
	  }
    }
    
    private static void garbageCollector(){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            watcher = DriverManager.getConnection ("jdbc:mysql://localhost/benimetum",
//                                                         "benimetum",
//                                                         "benimetum");
//            java.sql.Statement stmt = watcher.createStatement();
//            java.sql.ResultSet rs = stmt.executeQuery("SHOW PROCESSLIST");
//            int count=0;
//             if(rs.last())
//                 count=rs.getRow();
//             java.sql.Statement stm= watcher.createStatement();
//            java.sql.ResultSet rs2 = stm.executeQuery("SHOW PROCESSLIST");
//            if (count>=15) {
//                while (rs2.next()) {
//                String result = rs2.getString(1);
//                String state=rs2.getString(5);
//                int time=rs2.getInt(6);
//                if(state.equals("Sleep")&&(time>=5)){
//                    java.sql.Statement stm2 = watcher.createStatement();
//                    java.sql.ResultSet r = stm2.executeQuery("KILL "+result);
//                }
//                }
//            }
//            
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Sparql.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Sparql.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public static void close(){
        ConnectionPool.returnConnection(store.getConnection().getSqlConnection());
    }
}

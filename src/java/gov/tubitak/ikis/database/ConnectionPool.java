/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.ikis.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author celebron
 */
public class ConnectionPool {
    private static Connection[] pool=new Connection[25];
    private static boolean mutex;
    private static Analyzer analyzer=new Analyzer();
    static{
        try {
            pool[0]=DriverManager.getConnection("jdbc:mysql://localhost/ikis",
                                                             "ikis",
                                                             "ikis") ;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Connection getConnection(){
        mutex=true;
        try {
            //        FacesContext context = FacesContext.getCurrentInstance();
            //	  HomePageBean obj = context.getApplication().evaluateExpressionGet(context, "#{HomePageBean}", HomePageBean.class);
            //        int poolId = obj.getPoolId();
            //        if(poolId==100){
            //        for (int i = 0; i < pool.length; i++) {
            //            if(pool[i]==null){
            //                    try {
            //                        pool[i]=DriverManager.getConnection("jdbc:mysql://localhost/benimetum",
            //                                                             "benimetum",
            //                                                             "benimetum") ;
            //                        obj.setPoolId(i);
            //                        return pool[i];
            //                    } catch (SQLException ex) {
            //                        Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            //                    }
            //            }
            //
            //        }
            //        }
                    if(pool[0].isClosed())
                        pool[0]=DriverManager.getConnection("jdbc:mysql://localhost/ikis",
                                                                         "ikis",
                                                                         "ikis") ;
                
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pool[0];
    }
    
    public static void returnConnection(Connection conn){
//        for (int i = 0; i < pool.length; i++) {
//            Connection connection = pool[i];
//            if(connection!=null)
//            if(connection.equals(conn)){
//                try {
//                    pool[i].close();
//                    pool[i]=null;
//                    break;
//                } catch (SQLException ex) {
//                    Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
    }
}

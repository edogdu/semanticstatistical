/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.components;

import gov.tubitak.components.models.GraphModel;
import gov.tubitak.ikis.items.Data;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import org.primefaces.component.inputtext.InputText;

/**
 *
 * @author cem.ozkan
 */
public class IkisGraph extends InputText{
    
    private GraphModel model;
  
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        model = (GraphModel) getValueExpression("model").getValue(getFacesContext().getELContext());
        
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("div", this);
        writer.writeAttribute("id", "canvas", "div");
        writer.startElement("script", null);
        writer.writeAttribute("type", "text/javascript", "script");
        Data[] data = model.getData();
        Random random= new Random();
         if (data !=null) {
            for (int i = 0; i < data.length; i++) {
                Data object = data[i];
                String stage = object.getBelongsTo();
                String property = object.getProperty().getTrLabel();
                String value = object.getValue();
                String year = object.getYear();
                String sector = object.getSector();
                String uniqueId = ""+random.nextInt(1000000);
                String blankNode = "b"+uniqueId;
                writer.append("var "+stage+"r = function(){addStage(\"abs\");addEdge(\"abs\",\""+stage+"\");redraw();};");
                writer.append("addStage(\""+stage+"\", "+stage+"r);"); 
                writer.append("addBlankNode(\""+blankNode+"\");");
                writer.append("addNode(\"v"+uniqueId+"\",\""+value+"\");");
                writer.append("addSector(\""+sector+"\");");
                writer.append("addYear(\""+year+"\");");
                writer.append("addEdge(\""+stage+"\",\""+blankNode+"\",\""+property+"\");");
                writer.append("addEdge(\""+blankNode+"\",\"v"+uniqueId+"\",\""+"hasValue"+"\");");
                writer.append("addEdge(\""+blankNode+"\",\""+year+"\",\""+"hasYear"+"\");");
                writer.append("addEdge(\""+blankNode+"\",\""+sector+"\",\""+"hasSector"+"\");");
            }
        }
        writer.append("draw(\"500\", \"500\");");
        writer.endElement("script");
        writer.endElement("div");
    }
    
    
}

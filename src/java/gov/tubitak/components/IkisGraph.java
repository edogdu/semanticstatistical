/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.tubitak.components;

import gov.tubitak.components.models.GraphModel;
import java.io.IOException;
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
        writer.append("var redraw;");
        writer.append("window.onload = function() {");
        writer.append("var g = new Graph();");
        writer.append("g.addNode(\"id34\", { label : \"Duzey1\" });");
         writer.append("g.addNode(\"id35\", { label : \"Duzey2\" });");
         writer.append("g.addNode(\"id36\", { label : \"Il\" });");
         writer.append("g.addEdge(\"id35\", \"id34\", { stroke : \"#bfa\" , fill : \"#56f\", label : \"hasRegion\", directed : true});");
         writer.append("g.addEdge(\"id35\", \"id36\", { stroke : \"#bfa\" , fill : \"#56f\", label : \"hasCity\", directed : true});");
         writer.append("var layouter = new Graph.Layout.Spring(g);");
         writer.append("layouter.layout();");
         writer.append("var renderer = new Graph.Renderer.Raphael('canvas', g, '400', '300');");
         writer.append("renderer.draw();");
         writer.append("redraw = function() {layouter.layout();renderer.draw();};};");
        writer.endElement("script");
        writer.endElement("div");
    }
    
    
}

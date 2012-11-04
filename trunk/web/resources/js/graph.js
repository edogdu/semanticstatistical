/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var g = new Graph();
var stages = new Array();
var years = new Array();
var sectors = new Array();
var renderers = new Array();
var layouter;
var renderer;

function addSector(sector){
    var exist = new Boolean(true);
    for(var i=0; i<sectors.length; i++) {
	var value = sectors[i];
	if(value == sector){
            exist = false;
        }
    }
    if(exist){
        sectors.push(sector);
        addNode(sector, sector);
    }
}

function addYear(year){
    var exist = new Boolean(true);
    for(var i=0; i<years.length; i++) {
	var value = years[i];
	if(value == year){
            exist = false;
        }
    }
    if(exist){
        years.push(year);
        addNode(year, year);
    }
}

function addNode(id,name,onClick){
    if(onClick != undefined){
        var renderer = function(r, n) {
        /* the Raphael set is obligatory, containing all you want to display */
        var set = r.set()
        var shape = r.rect(n.point[0]-30, n.point[1]-13, 62, 66).attr({"fill": "#fa8", "stroke-width": 2, r : "9px"});
        var text = r.text(n.point[0], n.point[1] + 20, n.label).attr({"font-size":"14px"});
        shape.node.onclick = onClick;//functionu buraya vericeksin
        set.push(shape);
        set.push(text);
        return set;
    };
      g.addNode(id, { label : name,render : renderer});
    }else{
     g.addNode(id, { label : name});
    }
}

function addBlankNode(id){
    g.addNode(id);
}

function addEdge(source, target, labelName){
    g.addEdge(source, target, { stroke : "#bfa" , fill : "#56f", label : labelName, directed : true});
}

function addStage(name, onClick){
    var exist = new Boolean(true);
    for(var i=0; i<stages.length; i++) {
	var value = stages[i];
	if(value == name){
            exist = false;
        }
    }
    if(exist){
        stages.push(name);
        addNode(name, name,onClick);
        addTree(name);
    }
}
function addTree(name){
    var exist = new Boolean(true);

}

function draw(width, height){
    layouter = new Graph.Layout.Spring(g);
    layouter.layout();
    renderer = new Graph.Renderer.Raphael('canvas', g, width, height);
    renderer.draw();
    
}
function redraw() {
        layouter.layout();
        renderer.draw();
}

function addRenderer(id,onClick){
    var array = new Array();
    var renderFunc = function(r, n) {
        /* the Raphael set is obligatory, containing all you want to display */
        var set = r.set()
        var shape = r.rect(n.point[0]-30, n.point[1]-13, 62, 66).attr({"fill": "#fa8", "stroke-width": 2, r : "9px"});
        var text = r.text(n.point[0], n.point[1] + 20, n.label).attr({"font-size":"14px"});
        shape.node.onclick = onClick;//functionu buraya vericeksin
        set.push(shape);
        set.push(text);
        return set;
    };
    array.push(id, renderFunc);
    renderers.push(array);
}


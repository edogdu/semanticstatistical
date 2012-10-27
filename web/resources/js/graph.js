/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var g = new Graph();
var stages = new Array();
var years = new Array();
var sectors = new Array();

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

function addNode(id,name){
    g.addNode(id, { label : name});
}

function addBlankNode(id){
    g.addNode(id);
}

function addEdge(source, target, labelName){
    g.addEdge(source, target, { stroke : "#bfa" , fill : "#56f", label : labelName, directed : true});
}

function addStage(name){
    var exist = new Boolean(true);
    for(var i=0; i<stages.length; i++) {
	var value = stages[i];
	if(value == name){
            exist = false;
        }
    }
    if(exist){
        stages.push(name);
        addNode(name, name);
        addTree(name);
    }
}
function addTree(name){
    var exist = new Boolean(true);

}

function draw(width, height){
    var layouter = new Graph.Layout.Spring(g);
    layouter.layout();
    var renderer = new Graph.Renderer.Raphael('canvas', g, width, height);
    renderer.draw();
}


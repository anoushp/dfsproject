<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script type="text/javascript">
/* takes a string phrase and breaks it into separate phrases 
no bigger than 'maxwidth', breaks are made at complete words.*/

function formatLabel(str, maxwidth){
 var sections = [];
 var words = str.split(" ");
 var temp = "";

 words.forEach(function(item, index){
     if(temp.length > 0)
     {
         var concat = temp + ' ' + item;

         if(concat.length > maxwidth){
             sections.push(temp);
             temp = "";
         }
         else{
             if(index == (words.length-1))
             {
                 sections.push(concat);
                 return;
             }
             else{
                 temp = concat;
                 return;
             }
         }
     }

     if(index == (words.length-1))
     {
         sections.push(item);
         return;
     }

     if(item.length < maxwidth) {
         temp = item;
     }
     else {
         sections.push(item);
     }

 });

 return sections;
}
function start() {
	var cty = document.getElementById("radar-chart-main");
	var config_main={
			  type: 'radar',
			  data: {
			      labels: ${mapkeys},
			      datasets: [
			        {
			          label: "Weight",
			          fill: true,
			          backgroundColor: "rgba(255,99,132,0.2)",
			          borderColor: "rgba(255,99,132,1)",
			          pointBorderColor: "#fff",
			          pointBackgroundColor: "rgba(255,99,132,1)",
			          pointBorderColor: "#fff",
			          data: ${mapvalues},
			        }
			      ]
			    },
			    options: {
			    	
			    	 scale: {
			    		    ticks: {
			    		      beginAtZero: true,
			    		      min: 0,
			    		      max: 5,
			    		      stepSize: 1
			    		    },
			    	 },
			      title: {
			        display: true,
			        text: ''
			      }
			    }
	};
	//alert(attrnames);
	var myChartmain = new Chart(cty, config_main
	);
	for (i=0; i<${fn:length(categories)};i++){
		//${fn:length(categories)}
		
		var ctx = document.getElementById("radar-chart"+i);
		var attrnames= [];
		var nameElement =document.getElementById("attrnames"+i).value;
		nameElement=nameElement.replace('[', '');
		nameElement=nameElement.replace(']', '');
		
		var arrayNames = nameElement.split(",");
		alert(arrayNames[0] + arrayNames[1]);
		for (j=0;j<arrayNames.length;j++){
			arrayNames[j]=formatLabel(arrayNames[j], 30)
		}
		
		var attrweights=document.getElementById("attrweights"+i).value;
		attrweights=attrweights.replace('[', '');
		attrweights=attrweights.replace(']', '');
		var arrayWeights = attrweights.split(",");
        if (i==2){
			arrayNames=["Intra-organisational collaboration", "Inter-organisational collaboration"]
		}
		var config={
				  type: 'radar',
				  data: {
				      labels: arrayNames,
				      datasets: [
				        {
				          label: "Weight",
				          fill: true,
				          backgroundColor: "rgba(255,99,132,0.2)",
				          borderColor: "rgba(255,99,132,1)",
				          pointBorderColor: "#fff",
				          pointBackgroundColor: "rgba(255,99,132,1)",
				          pointBorderColor: "#fff",
				          data: arrayWeights,
				        }
				      ]
				    },
				    options: {
				    	
				    	 scale: {
				    		    ticks: {
				    		      beginAtZero: true,
				    		      min: 0,
				    		      max: 5,
				    		      stepSize: 1
				    		    },
				    	 },
				      title: {
				        display: true,
				        text: ''
				      }
				    }
		};
		//alert(attrnames);
		var myChart = new Chart(ctx, config
		);
	};
}
window.onload = start;
</script>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">
<h1>Results for Assessment  </h1>
<p>Title: <c:out value="${title}"></c:out><p>
<p>Total scored is <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${score}"/></b> out of max <b>5</b></p>
<p>Percentage <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${percentage}"/>%</b></p>
<p> Maturity level based on total score: <b><fmt:formatNumber value="${score+(1-(score%1))%1}" type="number" pattern="#"/></b>
<h2>Main Attributes</h2>
<div style="width:40%">
        <canvas id="radar-chart-main" width="800" height="600" ></canvas>
 </div>
<h2>Results by Attribute Criteria</h2>

<c:forEach var="mapelement" items="${hashmapweight}" varStatus="loop">
 <input type="hidden" id="attrweights${loop.index}" value='${mapelement.value.attrweights}'/>
 <input type="hidden" id="attrnames${loop.index}" value='${mapelement.value.attrnames}'/>
 <h2>Sub-Attribute: ${mapelement.key} </h2>
 <div style="width:40%">
        <canvas id="radar-chart${loop.index}" width="800" height="600" ></canvas>
 </div>
 </c:forEach>
 
 <h2>Results Summary Table </h2>
<table class="table table-striped">
<thead>
    <tr>
    <th scope="col">Attribute Criteria</th>
    <th scope="col">Score</th>
    </tr>
</thead>
 <c:forEach var="cat_results" items="${hashmap}" varStatus="loopmain">
<tr><td><c:out value="${cat_results.key}"></c:out></td>
<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${cat_results.value}"/></td>
</tr>
</c:forEach>
</table>
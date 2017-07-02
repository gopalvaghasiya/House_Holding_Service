var cnt=1;
var content=document.getElementById("content");
var btn=document.getElementById("btn");

btn.addEventListener("click",function(){

	var details = new XMLHttpRequest();

	details.open('GET','https://learnwebcode.github.io/json-example/animals-'+cnt+'.json');

	details.onload=function(){
		var data=JSON.parse(details.responseText);

		renderHTML(data);
	};

	details.send();
	cnt++;
	if(cnt>3){
		console.log(btn);
		btn.classList.add("hide-me");
	}

});

function renderHTML(data){

	var string="";

	for(i=0;i<data.length;i++){
		string+= "<p>" + data[i].name + " is a " + data[i].species + ".</p>";
	}

	content.insertAdjacentHTML('beforeend',string);
}


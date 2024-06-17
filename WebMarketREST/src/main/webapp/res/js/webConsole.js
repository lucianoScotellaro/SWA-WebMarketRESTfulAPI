"use strict";

export default function WebConsole(){

    let container = document.getElementById("web-console");

    this.logLine = function(content, classNames = []){
        //Create line element
        let line = container.ownerDocument.createElement("div");
        line.className = "line";
        for(let className of classNames)
            line.classList.add(className);
        line.innerHTML = content;

        //Append to console container
        container.appendChild(line);
    }

    this.clear = function(){
        container.innerHTML = "";
    }
}
"use strict";
import tests from "./tests.js";
import webConsole from "./webConsole.js";

export default function FetchClient() {
    let console = new webConsole();
    let runTests = true;
    let bearerToken;
    let excludedHeaders = ["connection", "content-length", "date", "keep-alive"];

    this.getBearerToken = function(){
        return bearerToken;
    }

    this.setBearerToken = function(token){
        bearerToken = token;
    }

    this.startTests = async function(){
        console.clear();
        await this.doTests();
    }

    this.doTests = async function ()
    {
        runTests = true;
        if(runTests){
            for(let test of tests){
                this.logRequest(test);

                let response = await fetch(test.url, this.fetchRequestParameters(test));
                if(response.headers.has("Authorization"))
                    this.setBearerToken(response.headers.get("Authorization").substring("Bearer".length).trim());
                await this.logResponse(response);
            }
        }
    }

    this.stopTests = function ()
    {
        runTests = false;
        console.logLine("Tests aborted", ["error"]);
    }

    this.completed = function(){
        console.logLine("Tests completed", ["success"]);
    }

    this.fetchRequestParameters = function(test){
        return {
            "method": test.method,
            "body": test.payload,
            "headers": this.fetchHeaders(test),
            "signal": AbortSignal.timeout(2000)
        }
    }

    this.fetchHeaders = function(test){
        let headers = {};

        if(test.authorization)
        {
            headers['Authorization'] = "Bearer " + this.getBearerToken();
        }

        if(test.acceptType){
            headers['Accept'] = test.acceptType;
        }

        if(test.payloadType){
            headers['Content-Type'] = test.payloadType;
        }

        return headers;
    }

    this.logRequest = function(test)
    {
        console.logLine(test.description, ["title"]);
        console.logLine("REQUEST", ["test-header"]);
        console.logLine("Method: " + test.method);
        console.logLine("URL: " + test.url);
        console.logLine("Headers: ", ["message-portion"]);
        console.logLine("Bearer: " + this.getBearerToken());
        console.logLine("Accept: " + test.acceptType);
        console.logLine("Content-Type: " + test.payloadType);
        console.logLine("Body: ", ["message-portion"]);
        console.logLine("Payload: " + test.payload);
    }

    this.logResponse = async function(response){
        console.logLine("RESPONSE", ["test-header"]);
        console.logLine("Headers: ", ["message-portion"]);
        for(let header of response.headers){
            if(!excludedHeaders.includes(header[0])) {
                console.logLine(header[0] + ": " + header[1]);
            }
        }
        console.logLine("Status: " + response.status + " (" + response.statusText + ")");
        let responseText = await response.text();
        if(responseText !== "") {
            console.logLine("Body: ", ["message-portion"]);
            console.logLine(responseText);
        }
    }
}
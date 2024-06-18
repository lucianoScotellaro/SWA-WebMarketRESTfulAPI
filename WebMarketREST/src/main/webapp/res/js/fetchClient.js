"use strict";
import tests from "./tests.js";
import webConsole from "./webConsole.js";

export default function FetchClient() {
    let console = new webConsole();
    let runTests = true;
    let bearerToken;
    let excludedHeaders = ["connection", "content-length", "date", "keep-alive"];
    const baseUrl = "http://localhost:8080/WebMarketREST/rest/";


    //Bearer
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


    //Console
    this.doTests = async function ()
    {
        runTests = true;
        if(runTests){
            for(let test of tests){
                this.logRequestFromTest(test);

                let response = await fetch(test.url, this.fetchRequestParametersFromTest(test));
                if(response.headers.has("Authorization"))
                    this.setBearerToken(response.headers.get("Authorization").substring("Bearer".length).trim());
                await this.logResponse(response);
            }
        }
    }

    this.logRequestFromTest = function(test)
    {
        console.logLine(test.description, ["title"]);
        console.logLine("REQUEST", ["test-header"]);
        console.logLine("Method: " + test.method);
        console.logLine("URL: " + test.url);
        console.logLine("Headers: ", ["message-portion"]);
        console.logLine("Bearer: " + this.getBearerToken());
        console.logLine("Accept: " + test.acceptType);
        console.logLine("Content-Type: " + test.payloadType);
        console.logLine("Payload: ", ["message-portion"]);
        console.logLine(test.payload);
    }

    this.logRequest = function(url, requestParameters){
        console.logLine("REQUEST", ["test-header"]);
        console.logLine("Method: " + requestParameters.method);
        console.logLine("URL: " + url);
        console.logLine("Headers: ", ["message-portion"]);

        if("Authorization" in requestParameters.headers){
            console.logLine("Authorization: " + requestParameters['headers']['Authorization']);
        }

        if("Accept" in requestParameters.headers){
            console.logLine("Accept: " + requestParameters['headers']['Accept']);
        }

        if(requestParameters['body'] != null){
            console.logLine("Content-Type: " + requestParameters['headers']['Content-Type']);
            console.logLine("Payload: ", ["message-portion"]);
            console.logLine(requestParameters.body);
        }
    }

    this.logResponse = async function(response){
        console.logLine("RESPONSE", ["test-header"]);
        console.logLine("Headers: ", ["message-portion"]);
        for(let header of response.headers){
            if(!excludedHeaders.includes(header[0])) {
                console.logLine(header[0] + ": " + header[1]);
            }
        }
        console.logLine("Status: " + response.status);
        let responseText = await response.text();
        if(responseText !== "" && response.status !== 401) {
            console.logLine("Body: ", ["message-portion"]);
            console.logLine(responseText);
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

    this.clearConsole = function ()
    {
        console.clear();
    }

    //Authentication
    this.login = async function (username, password){
        const url = this.buildUrl("auth/login");
        const parameters = this.fillRequestParameters("POST", false, "username=" + username + "&password=" + password, "application/x-www-form-urlencoded");

        this.logRequest(url, parameters);

        let response = await fetch(url, parameters);
        this.setBearerToken(response.headers.get("Authorization").substring("Bearer".length).trim())
        await this.logResponse(response);
    }

    this.logout = async function (){
        const url = this.buildUrl("auth/logout");
        const parameters = this.fillRequestParameters("DELETE", true)

        this.logRequest(url, parameters);

        bearerToken = null;
        let response = await fetch(url, parameters);
        await this.logResponse(response);
    }

    //Operations
    this.addRequest = async function(category, firstChar, secondChar, thirdChar, notes){
        const url = this.buildUrl("requests");
        const parameters = this.fillRequestParameters("POST", true,
            '{ "category": "'+ category +'","characteristics":{ "firstChar": "'+ firstChar +'","secondChar":"'+ secondChar +'","thirdChar":"'+ thirdChar +'"}, "notes": "'+ notes +'"}',
            "application/json");

        this.logRequest(url, parameters);

        let response = await fetch(url , parameters);
        await this.logResponse(response);
    }

    this.deleteRequest = async function (requestId){
        const url = this.buildUrl("requests/" + requestId);
        const parameters = this.fillRequestParameters("DELETE", true);

        this.logRequest(url, parameters);

        let response = await fetch(url, parameters);
        await this.logResponse(response);
    }

    this.getRequestDetails = async function(requestId){
        const url = this.buildUrl("requests/" + requestId);
        const parameters = this.fillRequestParameters("GET", true);

        this.logRequest(url, parameters);

        let response = await fetch(url, parameters);
        await this.logResponse(response);
    }

    this.setTechnician = async function(requestId){
        const url = this.buildUrl("requests/"+ requestId +"/technician");
        const parameters = this.fillRequestParameters("PUT", true, '{"id":1, "username": "mimmo", "email": "mimmo@gmail.com"}',"application/json");

        this.logRequest(url, parameters);

        let response = await fetch(url , parameters);
        await this.logResponse(response);
    }

    this.updateProposal = async function(requestId){
        const url = this.buildUrl("requests/"+ requestId +"/proposal");
        const parameters = this.fillRequestParameters("PUT", true, '{ "product": {"name": "Acer Aspire", "producer": "Acer", "id": 1, "price": 299.99, "url": "www.google.com"}, "notes": "Un bel pc" }', "application/json");

        this.logRequest(url, parameters);

        let response = await fetch(url , parameters);
        await this.logResponse(response);
    }

    this.acceptProposal = async function (requestId, reason){
        const url = this.buildUrl("requests/"+ requestId +"/proposal/answer");
        const parameters = this.fillRequestParameters("PUT", true,'{ "accepted": true, "reason": "' + reason + '" }',"application/json");

        this.logRequest(url, parameters);

        let response = await fetch(url, parameters);
        await this.logResponse(response);
    }

    this.getOngoingRequests = async function(){
        const url = this.buildUrl("requests/ongoing?ordererid=1");
        const parameters = this.fillRequestParameters("GET", true);

        this.logRequest(url, parameters);

        let response = await fetch(url, parameters);
        await this.logResponse(response);
    }

    this.getUnassigned = async function(){
        const url = this.buildUrl("requests/unassigned");
        const parameters = this.fillRequestParameters("GET", true);

        this.logRequest(url, parameters);

        let response = await fetch(url , parameters);
        await this.logResponse(response);
    }

    this.getTechnicianRequests = async function(){
        const url = this.buildUrl("requests?techid=1");
        const parameters = this.fillRequestParameters("GET", true);

        this.logRequest(url, parameters);

        let response = await fetch(url, parameters);
        await this.logResponse(response);
    }

    //Utils
    this.fillRequestParameters = function (method, authorization = false, body = null, contentType = null){
        const parameters = {
            "method": method,
            "headers": {},
            "signal": AbortSignal.timeout(2000)
        }

        if(authorization){
            parameters['headers']['Authorization'] = "Bearer " + this.getBearerToken();
        }

        if(body != null){
            parameters['body'] = body;
            parameters['headers']['Content-Type'] = contentType;
        }

        return parameters;
    }

    this.buildUrl = function (subpath){
        return baseUrl + subpath;
    }

    this.fetchRequestParametersFromTest = function(test){
        return {
            "method": test.method,
            "body": test.payload,
            "headers": this.fetchHeadersFromTest(test),
            "signal": AbortSignal.timeout(2000)
        }
    }

    this.fetchHeadersFromTest = function(test){
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
}
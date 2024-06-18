"use strict";

import fetchClient from "./fetchClient.js";

const client = new fetchClient();

//Console buttons
const startBtn = document.getElementById("start-tests-btn");
const stopBtn = document.getElementById("stop-tests-btn");
const clearBtn = document.getElementById("clear-console-btn");

//Authentication
const usernameInput = document.getElementById("username");
const passwordInput = document.getElementById("password");
const loginBtn = document.getElementById("login-btn");
const logoutBtn = document.getElementById("logout-btn");

//Operations Buttons
const addRequestBtn = document.getElementById("add-request-btn");
const deleteBtn =  document.getElementById("delete-request-btn");
const getDetailsBtn= document.getElementById("get-details-btn");
const setTechnicianBtn = document.getElementById("set-technician-btn");
const updateProposalBtn = document.getElementById("update-proposal-btn");
const acceptBtn = document.getElementById("accept-proposal-btn");
const getOngoingBtn = document.getElementById("get-ongoing-requests-btn");
const getUnassignedBtn = document.getElementById("get-unassigned-btn");
const getTechnicianRequestsBtn = document.getElementById("get-technician-requests-btn");

//Operations Inputs
const requestId = document.getElementById("request-id");
const reasonArea = document.getElementById("reason-area");
const category = document.getElementById("category");
const firstChar = document.getElementById("firstChar");
const secondChar = document.getElementById("secondChar");
const thirdChar = document.getElementById("thirdChar");
const notes = document.getElementById("notes");


//Console
startBtn.addEventListener("click", () => {
    startBtn.disabled = true;
    stopBtn.disabled = false;

    client.startTests().finally(() => {
        startBtn.disabled = false;
        stopBtn.disabled = true;
        client.completed();
    });
});

stopBtn.addEventListener("click", () => {
    stopBtn.disabled = true;
    startBtn.disabled = false;
    client.stopTests();
});

clearBtn.addEventListener("click", () => {
    client.clearConsole();
});

//Authentication
loginBtn.addEventListener("click", () => {
    loginBtn.disabled = true;
    logoutBtn.disabled = false;
    client.login(usernameInput.value, passwordInput.value);
});

logoutBtn.addEventListener("click", () => {
    loginBtn.disabled = false;
    logoutBtn.disabled = true;
    client.logout();
});

//Operations
addRequestBtn.addEventListener("click", () =>{
    client.addRequest(category.value,firstChar.value,secondChar.value, thirdChar.value, notes.value);
});

deleteBtn.addEventListener("click", () => {
    client.deleteRequest(requestId.value);
});

getDetailsBtn.addEventListener("click", () =>{
    client.getRequestDetails(requestId.value);
});

setTechnicianBtn.addEventListener("click", () => {
    client.setTechnician(requestId.value);
});

updateProposalBtn.addEventListener("click", () => {
   client.updateProposal(requestId.value);
});

acceptBtn.addEventListener("click", () => {
    client.acceptProposal(requestId.value, reasonArea.value);
});

getOngoingBtn.addEventListener("click", () =>{
    client.getOngoingRequests();
});

getUnassignedBtn.addEventListener("click", () => {
   client.getUnassigned();
});

getTechnicianRequestsBtn.addEventListener("click", () => {
    client.getTechnicianRequests();
});


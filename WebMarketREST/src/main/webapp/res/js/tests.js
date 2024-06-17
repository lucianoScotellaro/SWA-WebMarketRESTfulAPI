"use strict";

let baseUrl = "http://localhost:8080/WebMarketREST/rest/";

let dummyTechnician = '{ "id": 13, "username": "pluto", "email": "pluto@gmail.com"}';

let dummyAnswer = '{ "accepted": true, "reason": ""}';

let dummyProposal = '{ "product": { "name": "HP qualcosa", "producer": "HP", "id": 1, "price": 799.99, "url": "www.youtube.com" }, "notes": "Questo e` proprio un bel computer", "answer": ' + dummyAnswer + '}';

let dummyPurchaseRequest = '{ "id": 1, "orderer": { "id": 12, "username": "pippo", "email": "pippo@gmail.com" }, "technician": ' + dummyTechnician + ', "category": "informatics", "characteristics": { "GPU": "RTX4090Ti", "CPU": "Ryzen5600X", "RAM": "32GB" }, "notes": "Vorrei un bel computer", "proposal": ' + dummyProposal + ', "ongoing": false, "outcome": "accepted"}';

let tests = [
  {
    "description": "Login",
    "url": baseUrl + "auth/login",
    "method": "POST",
    "payload": "username=pippo&password=paperino",
    "payloadType": "application/x-www-form-urlencoded",
    "acceptType": null,
    "authorization": null
  },
  {
    "description": "Insert new purchase request",
    "url": baseUrl + "requests",
    "method": "POST",
    "payload": dummyPurchaseRequest,
    "payloadType": "application/json",
    "acceptType": "application/json",
    "authorization": true
  },
  {
    "description": "Assign purchase request to technician",
    "url": baseUrl + "requests/123/technician",
    "method": "PUT",
    "payload": dummyTechnician,
    "payloadType": "application/json",
    "acceptType": null,
    "authorization": true
  },
  {
    "description": "Assign or update proposal",
    "url": baseUrl + "requests/123/proposal",
    "method": "PUT",
    "payload": dummyProposal,
    "payloadType": "application/json",
    "acceptType": null,
    "authorization": true
  },
  {
    "description": "Update answer",
    "url": baseUrl + "requests/123/proposal/answer",
    "method": "PUT",
    "payload": dummyAnswer,
    "payloadType": "application/json",
    "acceptType": null,
    "authorization": true
  },
  {
    "description": "Delete purchase request",
    "url": baseUrl + "requests/123",
    "method": "DELETE",
    "payload": null,
    "payloadType": null,
    "acceptType": null,
    "authorization": true
  },
  {
    "description": "Orderer ongoing purchase requests",
    "url": baseUrl + "requests/ongoing?ordererid=1234",
    "method": "GET",
    "payload": null,
    "payloadType": null,
    "acceptType": "application/json",
    "authorization": true
  },
  {
    "description": "Unassigned purchase requests",
    "url": baseUrl + "requests/unassigned",
    "method": "GET",
    "payload": null,
    "payloadType": null,
    "acceptType": "application/json",
    "authorization": true
  },
  {
    "description": "Proposal details",
    "url": baseUrl + "requests/123/proposal",
    "method": "GET",
    "payload": null,
    "payloadType": null,
    "acceptType": "application/json",
    "authorization": true
  },
  {
    "description": "Technician purchase requests",
    "url": baseUrl + "requests?techid=1234",
    "method": "GET",
    "payload": null,
    "payloadType": null,
    "acceptType": "application/json",
    "authorization": true
  },
  {
    "description": "Logout",
    "url": baseUrl + "auth/logout",
    "method": "DELETE",
    "payload": null,
    "payloadType": null,
    "acceptType": null,
    "authorization": true
  }
];

export default tests;
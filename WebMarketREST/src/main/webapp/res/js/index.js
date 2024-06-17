"use strict";

import fetchClient from "./fetchClient.js";

const client = new fetchClient();
const startBtn = document.getElementById("start-tests-btn");
const stopBtn = document.getElementById("stop-tests-btn");

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
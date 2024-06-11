"use strict";
import {byId, toon, verberg} from "./util.js";

const response = await fetch("bevestigingen");
if (response.ok) {
    const bevestigingen = await response.json();
    const bevestigingenBody = byId("bevestigingenBody");
    for (const bevestiging of bevestigingen) {
        const tr = bevestigingenBody.insertRow();
        tr.insertCell().innerText = bevestiging.voornaam;
        tr.insertCell().innerText = bevestiging.familienaam;
        tr.insertCell().innerText = bevestiging.eetMee;
    }
} else {
    toon("storing");
}
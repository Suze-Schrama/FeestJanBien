"use strict";
import {byId, setText, toon, verberg} from "./util.js";

byId("confirmationForm").onsubmit = async function (event) {
    event.preventDefault();

    verbergFouten();
    verbergSucces();
    const voornaamInput = byId("voornaam");
    const familienaamInput = byId("familienaam");
    const eetMeeInput = byId("eetMee");
    const opmerkingInput = byId("opmerking");

    if (!voornaamInput.checkValidity()) {
        toon("voornaamFout");
        voornaamInput.focus();
        return;
    }

    if (!familienaamInput.checkValidity()) {
        toon("familienaamFout");
        familienaamInput.focus();
        return;
    }

    const bevestiging = {
        voornaam: voornaamInput.value,
        familienaam: familienaamInput.value,
        eetMee: eetMeeInput.value,
        opmerkingen: opmerkingInput.value
    };

    console.log("Bevestiging object:", bevestiging);
    await voegToe(bevestiging);
};

function verbergFouten() {
    verberg("voornaamFout");
    verberg("familienaamFout");
    verberg("storing");
}

function verbergSucces() {
    verberg("succes");
}

async function voegToe(bevestiging) {
    console.log("Sending bevestiging to API at '/bevestigingen'");
    const response = await fetch("/bevestigingen", { // Adjust the path here if needed
        method: "POST",
        headers: {'Content-Type': "application/json"},
        body: JSON.stringify(bevestiging)
    });

    console.log("Response status:", response.status);
    if (response.ok) {
        toon("succes");
        setTimeout(() => {
            window.location = "index.html";
        }, 2000);
    } else {
        toon("storing");
    }
}
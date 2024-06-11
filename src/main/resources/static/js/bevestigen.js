"use strict";
import {byId, setText, toon, verberg} from "./util.js";

function capitalizeNames(name) {
    const lowerExceptions = ["de", "van", "der"];
    const words = name.split(/\s+/);
    const capitalizedWords = words.map((word, index) => {
        // If the word is one of the exceptions and it is not the first word, return it as is
        if (lowerExceptions.includes(word.toLowerCase()) && index !== 0) {
            return word;
        } else {
            return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
        }
    });
    return capitalizedWords.join(" ");
}

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

    // Capitalize names using the capitalizeNames function
    const capitalizedVoornaam = capitalizeNames(voornaamInput.value);
    const capitalizedFamilienaam = capitalizeNames(familienaamInput.value);

    // Set the capitalized names to the input fields
    voornaamInput.value = capitalizedVoornaam;
    familienaamInput.value = capitalizedFamilienaam;

    const bevestiging = {
        voornaam: capitalizedVoornaam,
        familienaam: capitalizedFamilienaam,
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
            window.location = "allegasten.html";
        }, 2000);
    } else {
        toon("storing");
    }
}

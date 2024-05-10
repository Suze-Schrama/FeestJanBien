"use strict";
import {byId, toon, verberg} from "./util.js";

byId("toevoegen").onclick = async function () {
    verbergFouten();
    verbergSuccess();
    const voornaamInput = byId("voornaam");
    if (!voornaamInput.checkValidity()) {
        toon("naamFout");
        voornaamInput.focus();
        return;
    }
    const familienaamInput = byId("familienaam");
    if (!familienaamInput.checkValidity()) {
        toon("naamFout");
        familienaamInput.focus();
        return;
    }

    const eetMeeInput = byId("eetMee");
    const opmerkingen = byId("opmerkingen");

    const bevestiging = {
        voornaam: voornaamInput.value,
        familienaam: familienaamInput.value,
        eetMee: eetMeeInput.value.toString(),
        opmerking: opmerkingen.value
    };
    await voegToe(bevestiging);
}

    function verbergFouten() {
        verberg("naamFout");
        verberg("prijsFout");
        verberg("storing");
    }

    function verbergSuccess() {
        verberg("success");
    }

    async function voegToe(bevestiging) {
        const response = await fetch("bevestiging",
            {
                method: "POST",
                headers: {'Content-Type': "application/json"},
                body: JSON.stringify(bevestiging)
            });
        if (response.ok) {
            toon("success");
        } else {
            toon("storing");
        }
    }


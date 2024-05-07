package be.vanmanen.feestjanbien.gasten;

class Bevestiging {
 private final long id;
 private final String voornaam;
 private final String familienaam;
 private final boolean eetMee;
 private final String opmerking;

 public Bevestiging(long id, String voornaam, String familienaam, boolean eetMee, String opmerking) {
  this.id = id;
  this.voornaam = voornaam;
  this.familienaam = familienaam;
  this.eetMee = eetMee;
  this.opmerking = opmerking;
 }

 public long getId() {
  return id;
 }

 public String getVoornaam() {
  return voornaam;
 }

 public String getFamilienaam() {
  return familienaam;
 }

 public boolean getEetMee() {
  return eetMee;
 }

 public String getOpmerking() {
  return opmerking;
 }
}

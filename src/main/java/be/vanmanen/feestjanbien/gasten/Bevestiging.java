package be.vanmanen.feestjanbien.gasten;

class Bevestiging {
 private final long id;
 private final String voornaam;
 private final String familienaam;
 private final String eetMee;
 private final String opmerkingen;

 public Bevestiging(long id, String voornaam, String familienaam, String eetMee, String opmerkingen) {
  this.id = id;
  this.voornaam = voornaam;
  this.familienaam = familienaam;
  this.eetMee = eetMee;
  this.opmerkingen = opmerkingen;
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

 public String getEetMee() {
  return eetMee;
 }

 public String getOpmerkingen() {
  return opmerkingen;
 }
}

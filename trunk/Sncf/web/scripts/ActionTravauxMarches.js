function CalculePrixEtablissement(){
var majoPourcentage = parseFloat(document.getElementById('Champ').EtablissementDocumentPourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').EtablissementDocumentPrix.value);
document.getElementById('Champ').EtablissementDocumentPrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}

function CalculePrixModification(){
var majoPourcentage = parseFloat(document.getElementById('Champ').ModificationDocumentPourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').ModificationDocumentPrix.value);
document.getElementById('Champ').ModificationDocumentPrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}

function CalculePrixTraitement(){
var majoPourcentage = parseFloat(document.getElementById('Champ').TraitementPourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').TraitementPrix.value);
document.getElementById('Champ').TraitementPrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}

function CalculePrixTeintage(){
var majoPourcentage = parseFloat(document.getElementById('Champ').TeintagePourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').TeintagePrix.value);
document.getElementById('Champ').TeintagePrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}

function CalculePrixBordage(){
var majoPourcentage = parseFloat(document.getElementById('Champ').BordagePourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').BordagePrix.value);
document.getElementById('Champ').BordagePrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}




function CalculePrixHelio(){
var majoPourcentage = parseFloat(document.getElementById('Champ').HelioPourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').HelioPrix.value);
document.getElementById('Champ').HelioPrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}
function CalculePrixPhotocopie(){
var majoPourcentage = parseFloat(document.getElementById('Champ').PhotocopiePourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').PhotocopiePrix.value);
document.getElementById('Champ').PhotocopiePrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}
function CalculePrixCC1(){
var majoPourcentage = parseFloat(document.getElementById('Champ').CC1Pourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').CC1Prix.value);
document.getElementById('Champ').CC1PrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}
function CalculePrixCC2(){
var majoPourcentage = parseFloat(document.getElementById('Champ').CC2Pourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').CC2Prix.value);
document.getElementById('Champ').CC2PrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}
function CalculePrixCC3(){
var majoPourcentage = parseFloat(document.getElementById('Champ').CC3Pourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').CC3Prix.value);
document.getElementById('Champ').CC3PrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}
function CalculePrixCCSup(){
var majoPourcentage = parseFloat(document.getElementById('Champ').CCSupPourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').CCSupPrix.value);
document.getElementById('Champ').CCSupPrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}



function CalculePrixDAO1A4(){
var majoPourcentage = parseFloat(document.getElementById('Champ').DAO1A4Pourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').DAO1A4Prix.value);
document.getElementById('Champ').DAO1A4PrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}
function CalculePrixDAO2A4(){
var majoPourcentage = parseFloat(document.getElementById('Champ').DAO2A4Pourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').DAO2A4Prix.value);
document.getElementById('Champ').DAO2A4PrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}
function CalculePrixDAO3A4(){
var majoPourcentage = parseFloat(document.getElementById('Champ').DAO3A4Pourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').DAO3A4Prix.value);
document.getElementById('Champ').DAO3A4PrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}
function CalculePrixSupplement(){
var majoPourcentage = parseFloat(document.getElementById('Champ').SupplementPourcentage.value);
var majoPrix = parseFloat(document.getElementById('Champ').SupplementPrix.value);
document.getElementById('Champ').SupplementPrixTotale.value = majoPrix+majoPrix*majoPourcentage/100;
}
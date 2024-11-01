[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/31XZyb90)
# Sistem de Management al Resurselor Umane
### Gușcă Ana-Maria-Iasmina

## Descriere
Aceasta aplicatie vine in ajutorul companiilor, punandu-le la dispozitie un mod eficient prin care sa gestioneze informatiile despre angajati, cererile de concediu, de invoire sau de demisie, si sa monitorizeze performanta acestora.


## Obiective
 Obiectiv 1: Usurarea procesului de cautare a informatiile necesare in legatura cu un angajat, o cerere sau performanta angajatiilor unei echipe 

 Obiectiv 2: Facilitarea crearii de rapoarte de performanta pentru fiecare angajat intr-o maniera structurata pentru cresterea nivelului de dezvoltare profesionala a angajatilor

 Obiectiv 3: Îmbunătățirea comunicarii dintre angajat si manager prin intermediul feedback-ului pe care angajatii il pot transmite in urma primirii raportului de performanta

 Obiectiv 4: Gestionarea fara probleme a cererilor de concediu, invoire, demisie, printr-un sistem rapid care ofera sansa primirii unei aprobari sau respingeri din partea managerului intr-un mod eficient

 Obiectiv 5: Oferirea unei modalitati prin care managerii și echipele lor să programeze întâlniri de comun acord, să comunice detalii importante și să trimită notificări.

## Arhitectura
  Clasa Angajat contine nume, pozitie, salariu, dataAngajarii, telefon, email, adresa

Metode: actualizeazaInformatii si toString() suprascrisa pentru afisare, adaugaAngajat, stergeAngajat

  Clasa RaportPerformanta contine raportId, dataEvaluare, scor, comentarii

Metode: gettere și settere, adaugaRaport

  Clasa Cerere contine cerereId, tipCerere, dataInceput, dataSfarsit, status, comentariiManager

Metode: actualizeazaStatus, adaugaCerere(cerere)

  Clasa Eveniment contine evenimentId, numeEveniment, dataEveniment, locatie

Metode: gettere și settere, adaugaEveniment(eveniment)

Baza de date

1. Tabelul Angajati: Înregistrează informatiile despre angajati

 Coloane:

 angajat_id - Cheie primară, identificator unic pentru fiecare angajat.

 nume - Numele complet al angajatului.

 pozitie - Poziția în firmă.

 salariu - Salariul angajatului.

 data_angajare - Data angajării.

 numar_telefon - Numărul de telefon al angajatului.

 email - Adresa de email.

 adresa - Adresa de domiciliu.

 manager_id - Legătură către managerul angajatului (poate fi NULL pentru angajatii care nu au manager).

2. Tabelul Rapoarte: Înregistrează rapoartele fiecărui angajat.

 Coloane:

 evaluare_id - Cheie primară, identificator unic pentru fiecare raport

 angajat_id - Foreign key 

 data_evaluare - Data evaluării.

 scor - Scorul de performanță acordat în evaluare.

 comentariile - Observații și feedback al managerului care evaluează 

3. Tabelul Cereri: Reprezintă cererile de concediu și alte cereri administrative făcute de angajați.

 Coloane:

 cerere_id - Cheie primară, identificator unic pentru fiecare cerere.

 angajat_id - Foreign key

 tip_cerere - Tipul cererii (concediu, invoire, demisie).

 data_inceput - Data de început a cererii (de exemplu, începutul concediului).

 data_sfarsit - Data de sfârșit a cererii.

 status - Statusul cererii (aprobat, respins, in asteptare).

4. Tabelul Evenimente: Gestionează programările și evenimentele interne ale companiei.

 Coloane:

 eveniment_id - Cheia primară, identificator unic pentru fiecare eveniment.

 nume_eveniment - Numele evenimentului.

 data_eveniment - Data și ora la care are loc evenimentul.

 locatie - Locul desfășurării evenimentului.

Relații principale între tabele:

 Angajati - Cereri: Asociază fiecare cerere cu angajatul care a făcut solicitarea.

 Angajati - Rapoarte: Legătura între un angajat și rapoartele primite de la manager 


## Functionalitati/Exemple utilizare
 Autentificare

-> Angajatii au un cont cu care se pot autentifica

 Managementul Angajaților:
 
-> Înregistrarea informațiilor despre angajați: Nume, pozitie in firma, salariu, data angajarii, numarul de telefon, email-ul, adresa

-> Vizualizarea si modificarea datelor angajatiilor

-> Ștergerea informațiilor despre acestia daca demisioneaza/sunt concediati
 
-> Evaluarea Performanței de catre managerul de echipa
 
-> Crearea de rapoarte in care sunt evaluati angajații pe care ii are in subordine un manager

-> Posibilitatea ca angajatii sa ofere feedback pentru evaluarile primite(scoruri, comentarii).

Gestionarea Cererilor
 
-> Tipuri de cerere: cerere de concediu, de invoire, de demisie

-> Înaintarea cererilor de concediu (tip de concediu, date).

-> Aprobat sau respins de către manageri

-> Vizualizarea istoricului cererilor unui angajat

 Programări si Evenimente 

-> Crearea și gestionarea întâlnirilor interne, de exemplu un manager poate stabili o sedinta pentru echipa sa, iar angajatii sai primesc fiecare cate o norificare cu datele despre aceasta locatia/ora 

-> Angajatii au posibilitatea de a accepta sau sugera o alta ora

create table partecipante(
 nickname varchar(20) primary key,
 nome varchar(20) not null,
 cognome varchar(20) not null,
 mail varchar(30) not null unique);

create table squadra(
 nickname varchar(20) primary key references partecipante(nickname) ,
 puntiClassifica int not null default 0,
 panrtiteGiocate int not null default 0,
 vittorie int not null default 0,
 pareggi int not null default 0,
 sconfitte int not null default 0,
 puntiPrestazioneTot int not null default 0);

create table conto_crediti(
 nickname varchar(20) primary key references partecipante(nickname),
 saldo int not null check(saldo>0),
 creditiIniziali int not null);

create table calciatore(
 IDCalciatore varchar(20) primary key,
 nome varchar(20) not null,
 cognome varchar(20) not null,
 quotazioneAcquisto int,
 età int not null,
 ruolo varchar(15) not null,
 nazionalità varchar(30) not null,
 nickname varchar(20) references partecipante(nickname)); 

create table prestazione(
puntiValutazione float default 0,
IDCalciatore varchar(10) references calciatore(IDCalciatore), 
IDMatch varchar(5) references maatch(IDMatch));

create table modificatore(
nomeModificatore varchar(20) not null,
valore float default 0,
tipo varchar(20) not null,
IDCalciatore varchar(10) references calciatore(IDCalciatore), 
IDMatch varchar(5) references maatch(IDMatch));


create table maatch(
IDMatch varchar(5) primary key,
AnnoCorrente int references calendario(AnnoCorrente),
squadraCasa varchar(20) not null,
squadraTrasferta varchar(20) not null, 
puntiValutazioneCasa int,
puntiValutazioneTrasferta int);


create table calendario(
AnnoCorrente int primary key, 
numPartite int not null);

create table affronta(
IDMatch varchar(5) references maatch(IDMatch), 
nickname varchar(20) references partecipante(nickname), 
puntiPartita float);

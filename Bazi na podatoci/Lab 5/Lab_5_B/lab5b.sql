1. Да се напише DML израз со кој ќе се вратат имињата и презимињата на сите шалтерски работници кои имаат направено трансакција за исплата на средства во износ поголем од 1000 EUR од сметка која работи со валута EUR,
подредени според името на шалтерските работници.

select v.ime, v.prezime
from Vraboten v join Transakcija_shalter ts on v.ID=ts.ID_v join Smetka s on s.MBR_k=ts.MBR_k_s and s.broj=ts.broj
where ts.tip='isplata' and ts.suma>1000 and s.valuta='EUR'
order by v.ime;

-- reshenie so views, mnogu pokomplicirano i nepotrebno
-- create view shalterski_transakcii as
-- select v.ID, v.ime, v.prezime,ts.MBR_k_s,ts.broj
-- from Vraboten v join Transakcija_shalter ts on v.ID=ts.ID_v
-- where ts.tip='isplata' and ts.suma>1000;

-- create view smetki as
-- select s.MBR_k, s.broj
-- from Smetka s
-- where s.valuta='EUR';

-- select sh.ime,sh.prezime
-- from shalterski_transakcii sh join smetki s on sh.MBR_k_s=s.MBR_k and sh.broj=s.broj
-- order by sh.ime;

2. Да се напише DML израз со кој за секој шалтерски работник ќе се врати неговата шифра,
датумот и бројот на трансакции за датумот на кој има направено најголем број на трансакции.

create view counts as
select sr.ID as vraboten, ts.datum, count(ts.ID) as broj_transakcii
from Vraboten sr join Transakcija_shalter ts on sr.ID=ts.ID_v
group by sr.ID, ts.datum;

create view maxes as
select sr.vraboten, sr.datum, max(broj_transakcii) as broj_transakcii
from counts sr
group by sr.vraboten;

select c.vraboten, c.datum, m.broj_transakcii
from counts c join maxes m on c.broj_transakcii=m.broj_transakcii 
and c.vraboten=m.vraboten

3. Да се напише DML израз со кој ќе се вратат имињата и презимињата на сите премиум корисници
кои препорачале видео запис со времетраење подолго од 2 часа и за кој оставиле оцена поголема или еднаква на 4,
подредени според датумот на регистрација во растечки редослед.

create view preporaki as
select * from Preporaka p natural join Video_zapis v
where v.vremetraenje>120 and p.ocena>=4;

create view premium as 
select k.k_ime, k.ime, k.prezime, k.datum_reg
from Premium_korisnik pk natural join Korisnik k;

select distinct k.ime,k.prezime
from preporaki p join premium k on p.k_ime_od=k.k_ime
order by k.datum_reg;

4. Да се напише DML израз со кој ќе се вратат корисничкото име и бројот на видео записи кои му биле препорачани на корисникот кој дал најголем број на препораки.
Напомена: при оценување на оваа задача нема да се признаваат решенија со користење на ORDER BY.

create view counts_preporacuvaci as
select k.k_ime, count(p.ID) as preporaki
from Preporaka p join Korisnik k on p.k_ime_od=k.k_ime
group by k.k_ime;

create view maxes_preporacuvaci as
select max(preporaki) as preporaki from counts_preporacuvaci;

create view korisnici as
select k_ime from counts_preporacuvaci natural join maxes_preporacuvaci;

create view dobieni as
select k.k_ime, count(p.ID) as dobieni_preporaki
from korisnici k join Preporaka p on k.k_ime=p.k_ime_na
group by k.k_ime;

select * from dobieni;

5.Да се напише DML израз со кој за секој профил ќе се врати името на профилот и просечната оцена на видео записите во листата на желби асоцирана со тој профил. 
(Просечната оцена на секој видео запис се пресметува од сите оцени за тој видео запис).

create view video_zapisi_prosek as
select vz.naslov, avg(p.ocena) as prosek
from Video_zapis vz join Preporaka p on vz.naslov=p.naslov
group by vz.naslov;

select p.ime, avg(vz.prosek) as po_profil
from video_zapisi_prosek vz natural join (Profil p natural join Lista_zelbi lz) 
group by p.ime;

6. Да се напише DML израз со кој ќе се вратат името и презимето на корисниците кои во ист ден посетиле објекти кои се наоѓаат во соседни градови. 

create view g1 as
select kor_ime, grad1, grad2, datum
from Poseta natural join Objekt join Sosedi on Objekt.id_grad=Sosedi.grad1;

create view g2 as
select kor_ime, grad1, grad2, datum
from Poseta natural Join Objekt join Sosedi on Objekt.id_grad=Sosedi.grad2;

select k.ime, k.prezime
from g1 natural join g2 natural join Korisnik k

7. Да се напише DML израз со кој ќе се врати името на градот во кој се наоѓа објектот што бил посетен најголем број пати.

create view counts as
select o.id_grad, o.id_mesto, count(*) as br_poseti
from Objekt o natural join Poseta p
group by o.id_mesto;

create view najposeteno as
select id_grad, id_mesto, max(br_poseti) as br_poseti
from counts;

select m.ime 
from Grad g join najposeteno n on g.id_mesto=n.id_grad join Mesto m on g.id_mesto=m.id;

-- prva zadaca

SELECT DISTINCT l.id
FROM Lice l JOIN Test t ON l.id=t.id JOIN Vakcinacija_datum v ON l.id=v.id_lice
WHERE t.datum<v.datum AND t.rezultat='pozitiven'
ORDER BY l.id ASC

-- vtora zadaca dva nacini

-- WITH 
-- pozitivni_testovi as (
--     select t.id from Test t where t.rezultat='pozitiven' and t.datum between '2021-08-01' and '2021-08-31'
-- ),
-- pozitivni_broj as (
--     select count(DISTINCT id) as counts from pozitivni_testovi 
-- ),
-- vakcinirani as (
--     select count(*) as vak from pozitivni_testovi LEFT JOIN Vakcinacija_datum v on pozitivni_testovi.id=v.id_lice 
--     group by id_lice
--     having count(*) < 2
-- )
-- select count(counts)*1.0/counts*100 as procent from pozitivni_broj cross join vakcinirani;
-- select * from pozitivni_broj

CREATE VIEW pozitivni_testovi_view AS
    SELECT t.id
    FROM Test t
    WHERE t.rezultat = 'pozitiven' AND t.datum BETWEEN '2021-08-01' AND '2021-08-31';

CREATE VIEW pozitivni_broj_view AS
    SELECT COUNT(DISTINCT id) AS counts FROM pozitivni_testovi_view;

CREATE VIEW vakcinirani_view AS
    SELECT COUNT(*) AS vak
    FROM pozitivni_testovi_view pt
    LEFT JOIN Vakcinacija_datum v ON pt.id = v.id_lice
    GROUP BY id_lice
    HAVING COUNT(*) < 2;

SELECT COUNT(counts) * 1.0 / counts * 100 AS procent
FROM pozitivni_broj_view
CROSS JOIN vakcinirani_view;

DROP VIEW pozitivni_testovi_view;
DROP VIEW pozitivni_broj_view;
DROP VIEW vakcinirani_view;

-- treta zadaca

select distinct k1.ime, k2.prezime
from Korisnik k1 natural join Poseta p1 natural join Objekt o1
join Sosedi on Sosedi.grad1 = o1.id_grad and Sosedi.rastojanie < 300
join (Korisnik k2 natural join Poseta p2 natural join Objekt o2)
on o2.id_grad = Sosedi.grad2 and k1.kor_ime=k2.kor_ime

-- cetvrta zadaca
create view poseti_per_grad as
select g.id_mesto as id_grad, count(*) as br_poseti from Poseta p natural join Grad g group by g.id_mesto;

create view najposeten_grad as
select id_grad from poseti_per_grad
where br_poseti=(select max(br_poseti) from poseti_per_grad);

select ime from najposeten_grad natural join Objekt o join Mesto m on o.id_mesto=m.id
order by ime desc;

drop view poseti_per_grad;
drop view najposeten_grad

-- petta zadaca

select distinct ime,prezime
from Premium_korisnik pk natural join Korisnik k join 
Preporaka p on pk.k_ime=p.k_ime_od join Video_zapis v on v.naslov=p.naslov
where p.ocena>=4 and v.vremetraenje>120
order by k.datum_reg asc

-- sesta zadaca

create view najgolemi_preporacuvaci as 
SELECT k_ime_od
FROM (
    SELECT k_ime_od, COUNT(k_ime_od) AS counts
    FROM Preporaka
    GROUP BY k_ime_od
)
WHERE counts = (SELECT MAX(counts) FROM (
                  SELECT COUNT(k_ime_od) AS counts
                  FROM Preporaka
                  GROUP BY k_ime_od
               ) AS max_counts);


select np.k_ime_od as k_ime, count(k_ime_na) as dobieni_preporaki from
Preporaka p join najgolemi_preporacuvaci np on p.k_ime_na=np.k_ime_od 
group by k_ime_na

-- sedma zadaca

create view prosecni_oceni as
select naslov, avg(ocena) as average from Preporaka natural join Video_zapis group by naslov;

select lz.ime, avg(po.average) as po_profil from prosecni_oceni po natural join Lista_zelbi lz natural join Profil p group by p.ime, p.k_ime

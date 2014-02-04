SELECT DISTINCT S.imie, S.nazwisko, S.indeks, GC.nazwa, TER.dzien_tygodnia, TER.godzina_start
FROM grupa_student AS GS JOIN student AS S JOIN grupa_cwiczeniowa AS GC JOIN terminy AS TER
	ON GS.id_student = S.id 
	AND GS.id_grupa_cwiczeniowa = GC.id
	AND GC.id = TER.id_grupa_cwiczeniowa
	
SELECT * FROM terminy

D
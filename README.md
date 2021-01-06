PROJEKT MAX: 
 - 140 ptk na osobę. ( przy 1 osobie )
 - 130 ptk na osobę. ( przy 2 osobach )
 - 120 ptk na osobę. ( przy 3 osobach )

Wersja podstawowa ( max: 50 ptk ) ( 2 osoby:  40 pkt ) ( 3 osoby: 30 ptk )
System oparty o dowolną baze danych ( H2, Oracle, Postgres, Mongo ) 
System z dostępem shellowym
System oparty o tworzenie i czytanie XML. 


0. Poprawność kodu / działąjący kod zastosowanie prawidłowych wzorców 
1. System powinien umożliwić synchronizacje plików klientów dowolnej firmy ( komenda shell ) 
 - synchronizacja polega na dodaniu nowych klientów lub zmianę klientów już istniejących 
2. System powienin umożliwić synchronizacje zleceń dowolnej firmy ( komenda shell ) 
3. System powinien umożliwić wyświetlić listę w shell 
4. System powinien umożliwić export plików synchronizacji ( zlecen ) do firm zlecen nie wysłanych. 

5. Obsługa błedów 
 - błąd walidacji danych wejściowych
 - bład danych rachunku ( określony obiorca nie posiada podanego ranchunku bankowego )
 - błąd w przypadku gdy odbiorca nie został znaleziony
 
Założenia
Klient / Odbiorca
1. posiada imie i nazwisko 
2. posiada pesel 
3. posiada przynajmniej jeden dokument potwierdzające toższamość ( ID, PASZPORT itd )
4. posiada przynajmniej jeden adres 
5. posiada przynajmniej jeden telefon kontkaktowy
6. posiada przynajmniej jeden mail 
7. posiada przynajmniej jeden rachunek bankowy
8. RODO

Zlecenie 
1. posiada datę
2. posiada dane zlecającego 
3. posiada dane odbiorcy w tym rachunek bankowy
4. posiada kwote


Opcjionalne dodatkowe punktu
1. System powinien umożliwić dodanie klienta za pomocą api po zalogowaniu ( ROLA OPERATOR / ADMIN )  ( 10 ptk )
2. System powinien umóżliwić wyświetlić listę klientów po zalogowaniu ( ROLA ADMIN ) ( 10 ptk )
3. System powinien umożliwić zmianę klienta za pomocą api. po zalogowaniu ( ROLA ADMIN ) ( 10 ptk )
4. System powinien umożliwić wyświetlenie listy i anulowanie nie synchronizaowanego zlecenia w api po zalogowaniu  ( ROLA ADMIN ) ( 10 ptk ) 
5. System powinien umożliwić wyświetlenie listy i anulowanie nie synchronizaowanego zlecenia w shell ( 10 ptk ) 
6. System powinien umożliwić dodanie nowego zlecenia przez api po zalogowaniu ( ROLA OPERATOR / ADMIN ) ( 10 ptk )
7. System powinien wykryć zmianę danych przy dodawaniu zleceniu i dodać wpis do synchronizacji danych klientów 
 - utworzenie synchronizacji klientów ( 10 ptk )
 - w przypadku anulowania powinien również anulować synchronizacje danych klient powiązanych ze zleceniem. ( 10 ptk ) 
 - tylko jedna sychronizacja nowych danych poszczególnego klienta ( 10 ptk ) 
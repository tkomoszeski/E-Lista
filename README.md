# E-Lista

<b>Jest to system pozwalający na:</b></br>
-zarządzanie obecnością</br>
-tworzenia grup użytkowników i zarządzania nimi</br>
-prowadzenia ewidencji nieobecnosci</br>
-ustalenia własnego trybu pracy i monitorowania pracy;</br>
</br>
<b>Aktywności: </b></br>
-Nieobecnosci</br>
-Grupy</br>
-Obecnosci</br>
-Dzienniki planów </br>
<i>-Zarządzanie użytkownikami</i></br>
</br>
<b> Serwisy: </b> </br>

a)Użytkownik Controller:</br>

Metoda | Funkcjonalnosć | Opis 
 --- | --- | --- 
*/zapiszUzytkownika* | Tworzenie/edytowanie użytkownika | Funkcjonalność przeznaczona do tworzenia nowych użytkowników lub do edycji istniejących. W czasie tworzenia użytkownika trzeba uzupełnić pewne niezbędne pola, które są wymagane podczas tworzenia nowego użytkownika. Polami tymi są : imię, nazwisko, hasło, telefon i email. Email i hasło są polami po przez które użytkownik będzie się logował do aplikacji.
*/pobierzWszystkich* | Znajdź wszystkich | Funkcjonalność umożliwiająca pokazanie nam wszystkich użytkowników jakich mamy utworzonych w systemie. Po wyszukaniu użytkowników zostaną wyświetlone ich wszystkie dane. Jest przydatna bardzo w czasie różnorodnego dokonywania przeglądu kadr gdyż umożliwia podgląd wszystkich pracowników. 
*/pobierzPoId* | Znajdź użytkownika po id | Funkcjonalność należąca do najlepszego typu gdyż każdy użytkownik ma swój nie powtarzalny numer id, który go identyfikuje. Polem kluczowym jest id, które musi zostać podane w czasie wyszukiwania. Jeżeli nie ma konta przypisanego do wpisanego numeru id nic nie zostanie wyświetlone. Po znalezieniu naszego użytkownika który ma przypisany id zostaną wyświetlone jego dane. 
*/pobierzPoImieniu* | Znajdź po imieniu | Funkcjonalność działająca w podobny sposób do innych opierających się na znajdowaniu użytkownika. W tym przypadku użytkownika możemy znaleźć po jego imieniu. Pole to jest kluczowe w tym wyszukiwaniu i musi być uzupełnione, nie może zostać puste. Jeżeli jest puste mamy komunikat o jego wypełnieniu. Jeżeli wpiszemy imię użytkownika, które nie pasuje do żadnego naszego pracownika dane nie zostaną wyświetlone. Po znalezieniu użytkownika bądź kilku użytkowników (kilka osób może mieć to samo imię) zostaną wyświetlone dane użytkownika. 
*/pobierzPoNazwisku* | Znajdź po nazwisku | Umożliwia nam wyszukanie użytkowników systemu po przez wpisanie nazwiska użytkownika. Podczas wyszukiwania po nazwisku pole to jest polem obowiązkowym do wpisania. Jeśli tego nie zrobimy to pole nazwisko zostanie przemienione na czerwony prostokąt, sygnalizujący że nic nie zostało wpisane. Jeżeli zaś wpiszemy nazwisko, którego nie ma w systemie zostanie nam wyświetlony komunikat o błędzie. Gdy użytkownik zostanie znaleziony system wyświetli nam informacje, które zostały podane podczas tworzenia użytkownika, grupa do której należy wyszukany użytkownik a także jej uprawnienia.
*/pobierzPoImieniuINazwisku* | Znajdź po imieniu i nazwisku | Wyszukanie użytkownika podobnie jak inne funkcjonalności znajduje nam użytkownika. Różnicą jest sposób wyszukiwania naszego użytkownika. W tym przypadku znajdujemy go po przez podanie imienia i nazwiska w pola wymagane. Pola kluczowe to imię i nazwisko. Gdy dane te nie zostaną wprowadzone lub jedno z pól nie zostanie wprowadzone to odpowiednie pole lub oba zostaną objęte czerwonym prostokątem. Jeżeli dane zostaną źle wprowadzone nic nie zostanie wyświetlone. Jeżeli wyszukanie powiedzie się zostaną wyświetlone wszystkie dane odpowiedniego użytkownika, które zostały podane podczas tworzenia jego konta, a także informacje odnośnie grupy do której użytkownik należy i uprawnienia do jego grupy. 

# Rentable Offices
## Uruchamianie
### Prerequisites
- Java 13
- Maven
- Docker + Docker compose
### Serwisy
- W folderze `docker` jest plik docker compose (uruchomienie `docker-compose up`)
- Po uruchomieniu poza bazą i rabbitem, są również ich panele zarządzania
  - Rabbit: localhost:15672 (user: user, password: bitnami)
  - Postgres: localhost:8089 (user: postgres, password: postgres, db: offices)
### Aplikacja
- CMD: `mvn clean install` żeby zbudować, `mvn spring-boot:run` żeby uruchomić
- Intellij: Run -> Edit configurations -> Dodać nowy Spring Boot. Potem Run -> Run/Debug RentableOffices application

### Testy REST API
- W folderze `postman` znajduje się kolekcja requestów programu [Postman](https://www.postman.com/)
## Kod
### Struktura pakietów
- Pakiet nadrzędny `pl.edu.agh.rentableoffices`
  - `common` -> Dzielone funkcjonalności
  - `apartment` -> Zarządzanie lokalami 
### Struktura przykładowego pakietu (apartment)
- `dao` -> Warstwa repozytoriów (dostępu do danych)
- `dto` -> Klasy DTO (Data Transfer Object) do przyjmowania z frontu/zwracania przez back/wysyłania po rabbicie
- `mapper` -> Konwersja Encji na DTO
- `model` -> Warstwa modelu. Zawiera encje itp.
- `service` -> Zawiera serwisy (włączając listenery i sendery z rabbita)
- `web` -> Zawiera kontrolery RESTowe.
### Zawartość commona
- `EntityBase` - Klasa po której powinny dziedziczyć wszystkie encje (@Entity). Zawiera pole `id` typu long
- `AbstractMapper` - Interfejs do konwertowania encji na klasy wyjściowe dto.
- `Address` - Encja typu embeddable reprezentująca adres
- `AddressDto` - Dto adresu

### Lombok
- Projekt zaciąga biblioteke Lombok
- Biblioteka do generowania getterów, setterów, konstruktorów etc.
- W Intellij musimy się upewnić, że mamy właczone annotation processing 
  - File -> Settings -> Build, Execution, Deployment- > Compiler -> Annotation Processors -> Enable annotation processing
- Zastosowane annotacje
  - `@Getter` (mozna na polu i na klasie)
  - `@Setter` (mozna na polu i na klasie)
  - `@Builder` (Generuje wzorzec projektowy builder)
  - `@NoArgsConstructor/@AllArgsConstructor` - Konstruktor bezargumentowy/zawierający wszystkie pola. (Uwaga. Może być problem z wołaniem super())
  - `@Slf4j` - Logger (pole statyczne o nazwie `log`)
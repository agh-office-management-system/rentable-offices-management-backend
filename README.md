# Rentable Offices
Projekt do zarządzania wynajmem biur.
## Architektura
TODO diagram jak będzie już gotowe na 100 pro
- Aplikacja jest monolitem działającym jako serwlet Springa na wbudowanym serwerze Tomcat. 
- Udostępnia ona API RESTowe jako swój interfejs.
- Moduły wynajmującego i najemcy komunikują się między sobą asynchronicznie poprzez kolejkę komunikatów.
## Stos technologiczny
- Java
- REST
- Spring Boot
- Tomcat
- Docker
- PostgreSQL
## Lista funkcji
- Dodawanie biura
- Pobranie szczegółów biura
- Uaktualnienie danych biura
# Uruchamianie
## Prerequisites
- Java 13
- Maven
- Docker + Docker compose
## Serwisy
- W folderze `docker` jest plik docker compose (uruchomienie `docker-compose up`)
- Po uruchomieniu poza bazą i rabbitem, są również ich panele zarządzania
  - Rabbit: localhost:15672 (user: user, password: bitnami)
  - Postgres: localhost:8089 (user: postgres, password: postgres, db: offices)
## Aplikacja
- CMD: `mvn clean install` żeby zbudować, `mvn spring-boot:run` żeby uruchomić
- IntelliJ: Run -> Edit configurations -> Dodać nowy Spring Boot. Potem Run -> Run/Debug RentableOffices application

## Testy REST API
- W folderze `postman` znajduje się kolekcja requestów programu [Postman](https://www.postman.com/)

# Kod
## Struktura pakietów
- Pakiet nadrzędny `pl.edu.agh.rentableoffices`
  - `common` -> Dzielone funkcjonalności
  - `apartment` -> Zarządzanie lokalami 
## Struktura przykładowego pakietu (apartment)
- `dao` -> Warstwa repozytoriów (dostępu do danych)
- `dto` -> Klasy DTO (Data Transfer Object) do przyjmowania z frontu/zwracania przez back/wysyłania po rabbicie
- `mapper` -> Konwersja Encji na DTO
- `model` -> Warstwa modelu. Zawiera encje itp.
- `service` -> Zawiera serwisy (włączając listenery i sendery z rabbita)
- `web` -> Zawiera kontrolery RESTowe.
## Zawartość commona
- folder `communication` - Klasy reprezentujące abstrakcyjną wiadomość, listenera i sendera RabbitMQ
- `AbstractMapper` - Interfejs do konwertowania encji na klasy wyjściowe dto.
- `Address` - Encja typu embeddable reprezentująca adres
- `AddressDto` - Dto adresu
- `BusinessException` - Klasa wyjątku po którym dziedziczyć wszystkie błędy biznesowe
- `EntityBase` - Klasa po której powinny dziedziczyć wszystkie encje (@Entity). Zawiera pole `id` typu long
- `ErrorDto` - Klasa reprezentująca błąd zwracany przez system. Zawiera kod błędu i wiadomość.
- `ResponseDto` - Klasa generyczna, którą powinien zwracać każdy endpoint. Zawiera albo odpowiedź albo obiekt klasy `ErrorDto`
- `WebControllerAdvice` - Aspekt wyłapujący wyjątki, które wydostały się poza wartwe webową. Zamienia je w obiekty klasy `GenericResponseDto`
## Założenia programistyczne i stylistyczne
### Ogólne
- Typ `Optional<T>` jest preferowany niż konstrukcje typu `if (x == null)`
- Należy korzystać z wyrażeń lambda, interfejsów funkcyjnych i 
- Loggery powinny być tworzene poprzez dodanie adnotacji `@Slf4j` na klasie. Można się potem odwołać do loggera poprzez pole `log`
- Wyjątki biznesowe powinny dziedziczyć po `BusinessException` i `BusinessRuntimeException`
- Wyjątki `BusinessException` i `BusinessRuntimeException` powinny mieć kod (jakiś unikalny string)
- (TODO) `@Autowire` by constructor. (Można dać `@RequiredArgsConstructor` i pola wstrzykiwane jako `final`)
### Warstwa Modelu
- Każda encja powinna dziedziczyć po `EntityBase`
- Logika powinna być prowadzona na encji (serwis wywołuje np. tylko metodę update a nie zestaw setterów)
### Warstwa dostępu danych
- Należy korzystać z interfejsów Spring Data (Ewentualnie stosując adnotację `@Query` jak zapytanie jest skomplikowane)

### Warstwa serwisów
- Każdy serwis korzystający z bazy danych powinien być oznaczony adnotacją `@Transactional`
- Jeżeli po wyrzuceniu wyjątku chcemy zakończyć request i poinformować użytkownika o błędzie to *nie* łapiemy wyjątku
- (TODO) Wyjątki są łapane przez `@RestControllerAdvice` i tłumaczone na język polski korzystając z plików property.

### Warstwa web
- Każdy endpoint powinien zwracać klasę `ResponseDto<T>`
- Każdy endpoint nie powinien zwracać klasy (Parametr `T`) z pakietu `model` tylko `dto` bądź typ prymitywny/wrapper
- Każdy endpoint powinien mieć początek url `/api/`
- Każdy create powinien być `POST`em, każdy update `PUT`em, każde pobranie danych `GETe`m

## Dodatkowe informacje
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
  
## TODO
- Struktura modelu (plus diagramy)
- Rabbit transaction rollback
- @PreAuthorize
- Podzielić pakiety na część landlorda i wynajmującego
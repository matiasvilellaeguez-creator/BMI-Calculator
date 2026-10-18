# BMI_TERMINAL — Calculadora d'IMC

Aplicació Android desenvolupada amb **Kotlin i XML** que permet calcular l'**Índex de Massa Corporal (IMC)** d'una persona a partir del seu pes i la seva alçada.

Aquest projecte presenta una interfície personalitzada inspirada en l'estètica de les **terminals informàtiques i el món hacker**, utilitzant un disseny fosc, colors verds fluorescents, tipografia monoespaiada i elements visuals que simulen una consola de sistema.

L'objectiu principal és desenvolupar una aplicació Android funcional, interactiva i visualment atractiva, aplicant els conceptes bàsics de programació amb Kotlin i el sistema tradicional de vistes XML.

---

## Interfície de l'Aplicació (UI)

La calculadora disposa d'una interfície d'estil hacker que simula un terminal informàtic.

A continuació es mostren les dues pantalles principals de l'aplicació:

| Pantalla inicial | Resultat del càlcul |
|---|---|
| ![Pantalla inicial](docs/imatges/vista-inicial.png) | ![Resultat IMC](docs/imatges/vista-resultat.png) |

> **Nota:** Les imatges inicials són previsualitzacions del disseny. Abans de lliurar el projecte, s'han de substituir per captures reals de l'aplicació executada en Android.

### Característiques del disseny

La interfície incorpora diferents elements visuals inspirats en les terminals de programació:

- **Fons negre:** simula l'entorn d'una terminal informàtica.
- **Verd fluorescent:** utilitzat en textos, botons i indicadors.
- **Tipografia monoespaiada:** proporciona una aparença de consola.
- **Panells rectangulars:** organitzen els diferents apartats.
- **Ordres de terminal:** simulen l'execució de processos informàtics.
- **Resultats personalitzats:** presenten el càlcul com un informe del sistema.

Aquest disseny busca diferenciar l'aplicació d'una calculadora tradicional sense perdre la facilitat d'ús.

---

## Funcionalitats de l'Aplicació

La calculadora permet realitzar les operacions següents:

### Introducció del nom

L'usuari pot introduir el seu nom mitjançant un camp de text.

El sistema comprova que el camp no estigui buit abans de calcular l'IMC.

### Selecció del pes

El pes es pot modificar mitjançant dos botons:

- **[−]** Disminuir el pes.
- **[+]** Augmentar el pes.

El pes inicial és de **80 kg** i es pot modificar entre **1 i 300 kg**.

### Selecció de l'alçada

L'usuari pot seleccionar la seva alçada mitjançant una barra lliscant (SeekBar).

L'alçada es mostra en centímetres i es pot modificar entre **100 i 220 cm**.

### Càlcul de l'IMC

Quan l'usuari prem el botó de calcular, l'aplicació:

1. Comprova que el nom sigui correcte.
2. Obté el pes seleccionat.
3. Obté l'alçada seleccionada.
4. Calcula l'IMC.
5. Mostra el resultat i la seva classificació.

### Visualització del resultat

Després de realitzar el càlcul, l'aplicació mostra:

- Nom de l'usuari.
- Pes introduït.
- Alçada seleccionada.
- Valor de l'IMC.
- Categoria corresponent.

El resultat es presenta en un panell amb aparença d'informe informàtic.

---

## Fórmula de l'IMC

L'Índex de Massa Corporal es calcula dividint el pes en quilograms entre el quadrat de l'alçada en metres.

**Fórmula:**

IMC = Pes (kg) / Alçada² (m)

### Exemple de càlcul

Suposem que una persona té les dades següents:

- Nom: Matias
- Pes: 80 kg
- Alçada: 180 cm

Primer convertim l'alçada a metres:

180 cm = 1,80 m

Després apliquem la fórmula:

IMC = 80 / (1,80 × 1,80)

**Resultat: IMC = 24,7**

La persona es troba dins de la categoria de pes saludable.

### Classificació de l'IMC

| Valor de l'IMC | Classificació |
|---|---|
| Menys de 18,5 | Pes baix |
| Entre 18,5 i 24,9 | Pes saludable |
| Entre 25 i 29,9 | Sobrepès |
| 30 o superior | Obesitat |

L'IMC és un indicador orientatiu i no substitueix una valoració mèdica.

---

## Tecnologies Utilitzades

Per desenvolupar aquesta aplicació s'han utilitzat les tecnologies següents:

### Kotlin

Llenguatge de programació utilitzat per implementar:

- La lògica de l'aplicació.
- Els càlculs matemàtics.
- La validació de dades.
- La gestió dels botons.
- La interacció amb els components visuals.

### XML

Utilitzat per definir l'estructura i el disseny de la interfície.

Els components principals són:

- `TextView`: mostrar textos i resultats.
- `EditText`: introduir el nom.
- `SeekBar`: seleccionar l'alçada.
- `AppCompatButton`: modificar el pes i calcular l'IMC.
- `LinearLayout`: organitzar els components.
- `ScrollView`: permetre el desplaçament vertical.

### Android Studio

Entorn de desenvolupament utilitzat per crear, modificar i executar l'aplicació.

### Gradle

Sistema utilitzat per gestionar les dependències i la compilació del projecte.

### AndroidX / AppCompat

Biblioteques utilitzades per millorar la compatibilitat dels components visuals.

### JUnit

Biblioteca utilitzada per definir proves unitàries relacionades amb el càlcul de l'IMC.

---

## Funcionament del Codi

L'aplicació està desenvolupada amb Kotlin i utilitza una interfície definida amb XML.

### MainActivity.kt

És la classe principal de l'aplicació.

S'encarrega de:

- Inicialitzar els components visuals.
- Detectar les pulsacions dels botons.
- Actualitzar els valors del pes i l'alçada.
- Validar les dades introduïdes.
- Mostrar i ocultar el resultat.
- Actualitzar els missatges de la interfície.

Per accedir als components XML s'utilitza:

`findViewById()`

Per detectar les pulsacions dels botons:

`setOnClickListener()`

Per detectar els canvis de la barra d'alçada:

`setOnSeekBarChangeListener()`

### CalculadoraImc.kt

Conté la lògica relacionada amb el càlcul de l'Índex de Massa Corporal.

Aquesta separació permet mantenir el codi més organitzat i facilitar-ne el manteniment.

---

## Validació de Dades

L'aplicació incorpora diferents comprovacions per evitar errors:

- No permet calcular sense introduir un nom.
- Limita el pes entre 1 i 300 kg.
- Limita l'alçada entre 100 i 220 cm.
- Actualitza els valors quan l'usuari modifica els controls.
- Oculta el resultat anterior quan es modifiquen les dades.
- Conserva les dades principals quan es gira la pantalla mitjançant `onSaveInstanceState`.

Aquestes validacions permeten millorar l'experiència d'usuari i evitar resultats incorrectes.

---

## Estructura del Projecte

El projecte segueix una estructura habitual d'Android Studio.

```text
BMICalculator/
│
├── app/
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── org/matiasvilella/bmicalculator/
│       │   │       ├── MainActivity.kt
│       │   │       └── CalculadoraImc.kt
│       │   │
│       │   └── res/
│       │       ├── layout/
│       │       │   └── activity_main.xml
│       │       │
│       │       ├── drawable/
│       │       │
│       │       └── values/
│       │
│       └── test/
│
├── docs/
│   └── imatges/
│       ├── vista-inicial.png
│       └── vista-resultat.png
│
├── gradle/
├── README.md
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle.kts
```

---

## Instal·lació i Execució

Per executar l'aplicació cal seguir aquests passos:

### 1. Descarregar el projecte

Clonar el repositori de GitHub:

```bash
git clone https://github.com/matiasvilellaeguez-creator/BMI-Calculator.git
```

### 2. Obrir Android Studio

Obrir Android Studio i seleccionar:

**File → Open**

Seleccionar la carpeta del projecte descarregat.

### 3. Sincronitzar Gradle

Esperar que Android Studio descarregui les dependències necessàries.

### 4. Executar l'aplicació

Seleccionar un emulador Android o connectar un dispositiu físic.

Prémer el botó:

**Run**

L'aplicació s'iniciarà i permetrà utilitzar la calculadora.

### Requisits

- Android Studio.
- Kotlin.
- Android SDK.
- Android 7.0 (API 24) o superior.

---

## Objectius d'Aprenentatge

Durant el desenvolupament d'aquest projecte s'han treballat els conceptes següents:

- Creació d'una aplicació Android amb Kotlin.
- Disseny d'interfícies mitjançant XML.
- Utilització de components visuals.
- Gestió d'esdeveniments i botons.
- Validació de dades introduïdes per l'usuari.
- Realització de càlculs matemàtics.
- Actualització dinàmica de la interfície.
- Visualització i ocultació de components.
- Personalització de colors, formes i estils.
- Organització del codi en diferents classes.
- Utilització de GitHub per publicar un projecte.

---

## Autor

**Matias Vilella Eguez**

Estudiant de segon curs del Cicle Formatiu de Grau Superior de Desenvolupament d'Aplicacions Multiplataforma (DAM).

**Centre:** Institut Baix Camp — Reus.

**Projecte:** Calculadora d'IMC amb Kotlin i XML.

**GitHub:** [matiasvilellaeguez-creator](https://github.com/matiasvilellaeguez-creator)

---

## Conclusions

Aquest projecte ha permès posar en pràctica els coneixements bàsics del desenvolupament d'aplicacions Android.

S'ha implementat una calculadora funcional que permet introduir dades, interactuar amb diferents components visuals i obtenir un resultat mitjançant un càlcul matemàtic.

A més, s'ha personalitzat la interfície amb una estètica inspirada en les terminals informàtiques, demostrant que és possible combinar funcionalitat i disseny visual en una aplicació senzilla.

**BMI_TERMINAL — Calculadora d'IMC amb estètica hacker.**

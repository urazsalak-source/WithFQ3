# WithFQ

Minecraft **1.21.11** / Fabric / Java 21 icin kendi client modum.

## Derleme
1. JDK 21 kur.
2. Gradle wrapper dosyalarini (gradlew, gradlew.bat, gradle/wrapper/) FabricMC/fabric-example-mod
   reposundan bu klasore kopyala (Gradle 9.2+ gerekir) ya da IntelliJ IDEA ile klasoru ac, Gradle import etsin.
3. `./gradlew build` -> jar: `build/libs/withfq-0.1.0.jar`
4. Jar'i `.minecraft/mods` icine at (Fabric Loader 0.18.1+ ve Fabric API gerekir).

Gelistirirken: `./gradlew runClient`

## Kullanim
- **Sag Shift**: ClickGUI (sol tik ac/kapat, sag tik ayarlar)
- Her modul icin Ayarlar > Kontroller > Tus Atamalari > Miscellaneous altindan tus atayabilirsin.
- Config: `.minecraft/config/withfq.json`

## Yeni modul eklemek
`modules/` altina `Module` (veya HUD icin `HudModule`) turet, `ModuleManager` icinde `register(...)` ile ekle.

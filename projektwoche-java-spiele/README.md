# Projektwoche Java – Konsolenspiele

Konsolenspiele in Java aus der Projektwoche des Moduls **„Einführung in die Programmierung“** (B.Sc. Informatik – Data Science, FH Dortmund).
Alle Spiele laufen in der Konsole und brauchen nur das JDK (keine externen Bibliotheken).

| Spiel | Ordner | Inhalt |
|---|---|---|
| Mensch ärgere dich nicht | `mensch-aergere-dich-nicht` | 2–6 Spieler, je 3 Figuren, Würfeln, Schlagen gegnerischer Figuren |
| Tic-Tac-Toe | `tic-tac-toe` | 2 Spieler, beliebige Feldgröße n × n, Siegstatistik |
| Memory | `memory` | 4 × 6 Feld mit A–L / a–l, Paarprüfung über ASCII-Werte |
| Kniffel | `kniffel` | 2–8 automatische Spieler (`SpielerDumm`, `SpielerSchlau`), vollständiger Spielblock mit Bonus |
| Würfel-21 / Black Jack | `wuerfel-21` | 1 Spieler gegen Computer oder 2 Spieler, bis 100 Punkte |
| Zahlen raten | `zahlen-raten` | Tipps auf min/max-Bereiche, 1 oder 2 Spieler, bis 5 Punkte |
| Würfelfeld | `wuerfelfeld` | n × n Feld mit Würfelzahlen füllen, Wertung über Diagonale, Zeilen und Spalten |
| Wanderer (Brücke & Taschenlampe) | `wanderer` | Logikrätsel: 4 Wanderer müssen in 60 Minuten über die Brücke |
| Solitär | `solitaer` | 5 × 5 Steckspiel, Feldgröße variabel |

## Starten

Voraussetzung: Java (JDK 11 oder neuer).

```bash
# Beispiel: Tic-Tac-Toe
cd tic-tac-toe
javac *.java
java TicTacToe
```

| Ordner | Startklasse |
|---|---|
| `mensch-aergere-dich-nicht` | `javac menschaergeredichnicht/*.java && java menschaergeredichnicht.Spiel` |
| `tic-tac-toe` | `TicTacToe` |
| `memory` | `Main` |
| `kniffel` | `Kniffel` |
| `wuerfel-21` | `BlackJack` |
| `zahlen-raten` | `ZahlenRaten` |
| `wuerfelfeld` | `Spiel` |
| `wanderer` | `WandererSpiel` |
| `solitaer` | `Solitaer` |

## Was geübt wird

- Objektorientierte Programmierung: Klassen, Konstruktoren, Vererbung und abstrakte Klassen (Kniffel)
- Zweidimensionale Arrays als Spielfelder
- Spiellogik: Gewinnprüfung, Punkteberechnung, Zufallszahlen mit `Random`
- Konsolen-Ein- und -Ausgabe mit `Scanner` und Prüfung falscher Eingaben

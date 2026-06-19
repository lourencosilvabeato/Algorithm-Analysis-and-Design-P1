# Crystal Castle

[![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)](https://www.java.com/)
[![Course](https://img.shields.io/badge/Course-ADA-4A90D9)](https://www.fct.unl.pt/)
[![University](https://img.shields.io/badge/University-NOVA%20FCT-004F9E)](https://www.fct.unl.pt/)

> Academic project for the **Análise e Desenho de Algoritmos** (Algorithm Analysis and Design) course at NOVA FCT · 2024/2025.

## Overview

In a magical grid-shaped land, the explorer Ava must travel from the top-left tile to the bottom-right tile — the Crystal Castle — while navigating obstacles and respecting strict movement rules. Jumping tires her, so both the number of consecutive jumps and the total jumps across the entire journey are bounded. This project counts the number of valid paths Ava can take, modulo 10⁹ + 7.

## Problem

The land is an R × C grid of tiles. Ava starts at the top-left and must reach the bottom-right. She can move in five ways: two regular steps (right or down) and three jump moves (diagonally or doubly downward). Some tiles restrict or block movement entirely.

### Movement Types

| Move | Description | Counts as Jump |
|------|-------------|:--------------:|
| **R** | One step right (same row) | No |
| **D** | One step down (same column) | No |
| **LD** | Diagonal leap: one row down, one column left | Yes |
| **DD** | Double-down leap: two rows down, same column | Yes |
| **RD** | Diagonal leap: one row down, one column right | Yes |

### Tile Types

| Character | Meaning |
|:---------:|---------|
| `.` | No constraints |
| `X` | Diagonal jumps (LD and RD) forbidden from this tile |
| `J` | All jumps (LD, DD, and RD) forbidden from this tile |
| `#` | Impassable — cannot be stepped on |

### Example

For the first sample case (3 × 4 grid, M = 1, N = 3):

```
. X . J
. . # .
# . . .
```

With at most 1 consecutive jump and 3 total jumps, there are **12** valid paths, including sequences such as `D R D R R`, `R R LD D R R`, and `RD D R R`.

## Algorithm

The solution uses **4-dimensional bottom-up dynamic programming**.

| Step | Description |
|------|-------------|
| **1. State definition** | `dp[row][col][consecutive_jumps][total_jumps]` — number of paths from `(row, col)` to the bottom-right tile given the current jump state |
| **2. Base case** | Every reachable cell on the bottom row is initialised to `1` across all valid jump-state combinations |
| **3. Transitions** | For each cell (bottom-up), accumulate contributions from valid successors: R and D reset the consecutive counter; LD, DD, and RD increment both counters, subject to tile restrictions and jump limits |
| **4. Answer** | `dp[0][0][0][0]` — paths from the top-left with no jumps used |

All values are taken modulo `10⁹ + 7`.

### Complexity

| | Bound |
|--|--|
| **Time** | O(R × C × M × N) |
| **Space** | O(R × C × M × N) |

## Project Structure

```
src/
├── Main.java            # Entry point: parses input and drives test cases
└── CrystalCastle.java   # Core logic: 4-D bottom-up DP solver
```

## Getting Started

### Prerequisites

- Java 11 or higher

### Compile

```bash
javac src/*.java -d out/
```

### Run

Provide input via stdin:

```bash
java -cp out/ Main < input.txt
```

### Input Format

```
T                       ← number of test cases
R C M N                 ← grid dimensions, max consecutive jumps, max total jumps
<R lines of C chars>    ← tile map (., X, J, #)
...
```

### Sample Input / Output

**Input:**
```
3
3 4 1 3
.X.J
..#.
#...
3 4 2 3
.X.J
..#.
#...
10 20 5 10
....................
....................
....................
....................
....................
....................
....................
....................
....................
....................
```

**Output:**
```
12
15
140916123
```

## Constraints

| Parameter | Range | Description |
|-----------|:-----:|-------------|
| T | 1 – 20 | Number of test cases |
| R | 1 – 400 | Number of rows |
| C | 1 – 400 | Number of columns |
| M | 1 – 5 | Max consecutive jumps |
| N | 1 – 10 | Max total jumps in the journey |

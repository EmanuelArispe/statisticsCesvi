# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Purpose

Java + Selenium automation that logs into `sistema-orion.com` and navigates the management portal to filter and download statistics reports for CESVI appraisers.

## Build & Run

```powershell
# Compile
mvn compile

# Run tests (placeholder only)
mvn test

# Package JAR
mvn package

# Run the automation (requires Firefox installed)
mvn exec:java -Dexec.mainClass="com.statisticsCesvi.App"
```

> Selenium 4.43 includes Selenium Manager, which auto-downloads `geckodriver` — no manual setup needed as long as Firefox is installed.

## Architecture

The automation runs as a single linear script via `main()` in `App.java`:

1. **`Driver`** — Singleton `FirefoxDriver` wrapper. Call `getDriver()` to get the `WebDriver` instance; call `tearDown()` when done (currently commented out in `App.main()`).

2. **`Login`** — `loginUser()` navigates to the URL and submits credentials. `getManagement()` clicks through to the management section and dismisses a confirmation dialog.

3. **`Management`** — `managementDownload()` sets four cascading dropdown filters (perito → group → province → city) using `WebDriverWait`. The city dropdown (`ddlLocalidad`) requires a custom wait because it populates asynchronously after province is selected.

## Hardcoded Values

Credentials and filter values are hardcoded in `App.java` and `Management.java`. The target appraiser is `ARISPE EMANUEL`, group `Zona10-Chiappanni`, province `Buenos Aires`, city `Tandil`.

## Incomplete Work

The report search and Excel export steps are commented out at the bottom of `Management.java`. The commented block shows the remaining DOM interactions needed to complete the download flow (`btnBuscar` → `btnExportarExcelExportBusqueda`).

## Stack:

Java 21
Maven
Selenium
PostgreSQL (Neon)
Apache POI

## Purpose:
Download Excel files, parse data, and load it into PostgreSQL.

## Coding Rules
1. Follow existing project structure.
2. Prefer simple solutions.
3. Reuse existing code before creating new abstractions.
4. Use explicit waits instead of Thread.sleep().
5. Use PreparedStatement for SQL.
6. Validate Excel headers before processing rows.
7. Handle null and empty cells safely.
8. Do not expose credentials or secrets.

## Git

### Use Conventional Commits:

feat, fix, refactor, perf, test, docs, chore

### Never add:

Co-Authored-By
AI attribution
Claude references

When generating commits, analyze only git diff, never the entire repository.
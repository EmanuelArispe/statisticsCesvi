Review the specified file (or `$ARGUMENTS`) for simplification, reuse, and efficiency, then apply the fixes directly.

## Focus areas

- Replace `Thread.sleep()` with explicit Selenium waits (`WebDriverWait` + `ExpectedConditions`).
- Eliminate duplicated `driver.findElement()` calls — locate once, reuse the reference.
- Replace raw `Statement` with `PreparedStatement` for any SQL.
- Validate Excel headers before iterating rows; handle null and empty cells safely.
- Remove dead code, unused imports, and unnecessary comments.
- Prefer loops or streams over repeated identical statements.

## Rules

- Do not add features or behavior not already present.
- Do not introduce new abstractions unless three or more identical blocks exist.
- Do not add comments unless the why is non-obvious.
- Follow the existing project structure (`com.statisticsCesvi` package, Maven layout).
- Selenium stability
- Excel processing speed
- PostgreSQL performance
- Memory usage

## Steps

1. Read the target file(s).
2. Identify concrete improvements from the focus areas above.
3. Apply each fix with Edit.
4. Report what was changed and why in one sentence per change.
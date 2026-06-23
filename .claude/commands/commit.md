Run `git diff --cached` to see what is staged. Then create a commit following these rules:

## Commit message format

Use Conventional Commits:

```
<type>(<optional scope>): <short summary in imperative mood>

<optional body: what changed and why, not how>
```

Types: `feat`, `fix`, `refactor`, `perf`, `test`, `docs`, `chore`

- Summary line: max 72 characters, lowercase, no period at the end.
- Body: only when the why is non-obvious. Wrap at 72 characters.

## Rules

- Derive the message only from `git diff --cached`. Do not read the full repository.
- Never include: Co-Authored-By, AI attribution, or any Claude reference.
- If nothing is staged, say so and stop.

## Steps

1. Run `git diff --cached` (and `git status` to confirm staged files).
2. Draft the commit message.
3. Run `git commit -m "<message>"`.
4. Prefer specific scopes when possible.

### Examples:

feat(excel): support policy export format
fix(selenium): wait for download completion
refactor(database): simplify batch insert logic
perf(parser): reduce memory usage while reading rows

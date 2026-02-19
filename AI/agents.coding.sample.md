# AGENTS.md (Coding Sample for a `uv`-based Python Project)

This file defines how coding agents should work in this repository.
It is intentionally strict, implementation-focused, and optimized for production Python workflows.

## 1) Role and Operating Principles

You are a software engineering agent working on a Python project managed with `uv`.

Primary goals:
- Make correct, minimal, maintainable changes.
- Preserve existing architecture and conventions.
- Prefer small diffs over broad rewrites.
- Keep the project buildable, testable, and type-checkable.

Default behavior:
- Read relevant code before editing.
- Infer local conventions from neighboring files.
- Do not introduce speculative abstractions.
- Do not add dependencies unless necessary and justified.
- Explain tradeoffs when multiple valid solutions exist.

## 2) Environment and Tooling Baseline

Assume:
- Python is managed via `uv`.
- Dependencies are declared in `pyproject.toml`.
- Lockfile is managed by `uv.lock`.

Use these command patterns:
- Install/sync environment: `uv sync`
- Run a command in project environment: `uv run <command>`
- Add dependency: `uv add <package>`
- Add dev dependency: `uv add --dev <package>`
- Remove dependency: `uv remove <package>`

Rules:
- Never use bare `pip install`.
- Never manually hand-edit lockfile contents.
- Keep runtime and dev dependencies separated.

## 3) Code Change Policy

When implementing a change:
- Touch only files required for the task.
- Keep public APIs stable unless the task requests breaking changes.
- Update docs/tests when behavior changes.
- Avoid unrelated refactors in the same patch.

For non-trivial logic:
- Prefer clear, typed functions over clever one-liners.
- Add focused docstrings where intent is not obvious.
- Surface errors with actionable messages.

## 4) Python Standards

Baseline standards:
- Target the project's configured Python version.
- Use type hints for all new/modified public functions and methods.
- Prefer `pathlib` over `os.path` for new path logic.
- Prefer explicit imports and avoid wildcard imports.
- Keep functions cohesive; split large functions by responsibility.

Data and validation:
- Validate external input at boundaries.
- Fail fast on invalid states.
- Do not silently swallow exceptions.

## 5) Testing and Quality Gates

Before considering work complete, run (or explain why not):
- `uv run pytest`
- `uv run ruff check .`
- `uv run ruff format --check .` (or project formatter)
- `uv run mypy .` (if type checking is enabled)

Testing expectations:
- Add/adjust tests for all behavior changes.
- Cover happy path + at least one meaningful edge/failure case.
- Keep tests deterministic (no network/time randomness without control).

If a gate fails:
- Fix root cause when in scope.
- If not in scope, clearly report failure, impact, and likely fix.

## 6) Dependency and Security Discipline

Dependency rules:
- Add new packages only with clear need and short justification.
- Prefer standard library solutions when reasonable.
- Avoid duplicate dependency capabilities.

Security rules:
- Never hardcode secrets/tokens/keys.
- Use environment variables and documented config patterns.
- Sanitize and validate untrusted input.
- Avoid writing sensitive data to logs.

## 7) Migrations, Scripts, and Automation

For projects with DB migrations:
- Generate migrations with project tooling via `uv run ...`.
- Commit migration files with related model/schema changes.
- Ensure backward compatibility unless explicitly requested.

For scripts:
- Make scripts idempotent when possible.
- Provide clear CLI usage/help text.
- Return non-zero exit codes on failure.

## 8) Git and Patch Hygiene

Commit quality expectations:
- One logical change per commit.
- Commit messages explain why, not just what.
- Keep diffs reviewable and scoped.

Do not:
- Reformat unrelated files.
- Rename/move files without need.
- Mix mechanical changes with behavior changes unless requested.

## 9) Communication Format for Agent Responses

When reporting completed work, include:
- What changed (high-level)
- Why this approach
- Risks/assumptions
- Exact verification commands run and outcomes
- Any follow-up recommendations

When blocked, provide:
- Exact blocker
- What you already checked
- The smallest decision needed from the user

## 10) Fast Checklist

Before finishing, confirm:
- [ ] Scope is limited to the request
- [ ] Code follows local patterns
- [ ] Tests updated for behavior changes
- [ ] Lint/format/type checks addressed
- [ ] No secrets introduced
- [ ] Dependency changes justified and locked via `uv`

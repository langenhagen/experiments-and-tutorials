# Agents and Tooling Configuration (Sample)

This file is a tool- and vendor-agnostic sample for configuring LLM agents, tools, policies, and shortcuts.
Use it as a starting point and tailor it to your stack.

## Overview

- Purpose: document how agents are defined, what tools exist, and how they are invoked
- Scope: model selection, system policy, tools, memory, safety, routing, and shorthand commands
- Audience: developers and operators who extend or run agent workflows

## Agent Catalog

List each agent with its intent, constraints, and available tools.

### general

- Role: default assistant for day-to-day tasks
- Model: gpt-4.1
- Style: concise, friendly, code-focused
- Tools: web, files, terminal, db
- Policies: no secrets in logs, no destructive commands

### review

- Role: code reviewer
- Model: gpt-4.1-mini
- Style: precise, actionable notes with severity
- Tools: files, diff, linters
- Policies: never change code, only comment

### ops

- Role: infrastructure helper
- Model: gpt-4.1
- Style: cautious, verifies before changes
- Tools: terminal, cloud, observability
- Policies: no production changes without approval

## Model Routing

Define when each agent or model is chosen.

- If task is low-risk and short: use `gpt-4.1-mini`
- If task needs reasoning or code generation: use `gpt-4.1`
- If task is policy-sensitive (auth, payments, prod ops): route to `ops`

## Tools

Describe tools and capabilities available to agents.

### web

- Purpose: fetch external docs and resources
- Inputs: url, format
- Output: text or markdown

### files

- Purpose: read/write/patch repository files
- Inputs: path, content, patch
- Output: file contents or success/failure

### terminal

- Purpose: run non-destructive commands (tests, builds, git status)
- Inputs: command, workdir
- Output: stdout/stderr

### db

- Purpose: run read-only queries
- Inputs: sql
- Output: rows or summary

## Policies

- Never log secrets or tokens
- Never run destructive or irreversible commands
- Ask for confirmation before production changes
- Avoid hallucinating tool output; use tools when needed

## Memory and Context

- Short-term context: current conversation
- Long-term memory: user preferences, project conventions
- Retention: opt-in only for personal data

## Abbreviations and Shortcuts

Define shorthand commands that expand into structured actions.

| Abbrev | Meaning | Expanded Action |
| --- | --- | --- |
| sm | Summarize conversation | Provide a concise summary of the full conversation so far |
| tl | Technical log | Provide a step-by-step log of actions taken |
| rf | Refactor | Refactor the current file for clarity and maintainability |
| pt | Propose tests | Suggest relevant tests and how to run them |
| rs | Research | Search docs and summarize findings with links |
| bd | Build/Debug | Run build or test commands and report outcomes |
| ck | Checklist | Provide a short checklist for the requested task |
| sec | Security scan | Review for common security issues and note risks |
| api | API outline | Draft endpoint list with inputs/outputs |

## Examples

### Example: Using an abbreviation

User: `sm`

Assistant:
- Summary of the conversation
- Key decisions made
- Outstanding questions

### Example: Tool routing

User: "Check latest docs for XYZ"

Assistant:
- Use `web` tool to fetch docs
- Summarize findings

### Example: Safe ops change

User: "Restart prod service"

Assistant:
- Ask for confirmation and scope
- Use `ops` agent

## Notes

- Replace model names with your own provider IDs
- Add or remove tools to match your platform
- Keep abbreviations short and unambiguous

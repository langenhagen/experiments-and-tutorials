# TypeScript Hello

## Steps

```bash
pnpm env use --global 22.18.0  # tells pnpm to set --global: global Node.js version to 22.18.0 using its built-in env manager
```

```bash
pnpm init  # creates `package.json`

pnpm add typescript ts-node @types/node chalk
# the @ prefix means scoped package — it’s part of npm’s namespace system.
#   e.g. eslint, prettier, etc. → plain, top-level packages.
#   @typescript-eslint/parser → scoped under the @typescript-eslint org on npm.

# pnpm add -D ts-node typescript @types/node  # -D for dev dependencies
# - `typescript` gives the compiler
# - `ts-node` allows direct TS execution (no manual build step)
# - `@types/node` gives Node typings
# - `chalk` is lib to colorize console output

pnpm add -D tsx globals
# tsx is a tool / runtime that lets you run TypeScript files directly (without first doing tsc).
# globals provides predefined global variable lists (for ESLint, e.g. console, process, etc.)

# npx is a command runner that comes with npm
# tsc is the typescript compiler
npx tsc --init  # init tsconfig

# linters, formatters and stuff
pnpm add -D eslint \
  @typescript-eslint/parser \
  @typescript-eslint/eslint-plugin \
  eslint-config-prettier \
  prettier \
  eslint-plugin-import \
  eslint-plugin-simple-import-sort \
  @eslint/js
```

- I added `"dev": "node --loader ts-node/esm src/index.ts"` to `package.json`
  - defines a command `pnpm dev`
- I added `"type": "module",` to `package.json`
  - tells Node.js to interpret all `.js` and `.ts` files as **ESM (ECMAScript Modules)**, so you can
    use modern syntax like `import` / `export` instead of `require` / `module.exports`.

Added `eslint.config.js`

Edit `src/index.ts`

Formatting:
```bash
pnpm prettier --check .
pnpm prettier --write .
```

Linting:
```bash
pnpm eslint src
pnpm eslint src --fix
```


Run immediately:
```bash
pnpm dev
# or
pnpm tsx src/index.ts
```

Compile and run:
```bash
pnpm tsc  # compile to JS
node dist/index.js  # run via `node`
```

`pnpm` — performant Node.js package manager
  - See: https://pnpm.io/installation
  - Alternative to `npm`/`Yarn`
  - Uses global content-addressable store → saves disk, speeds up installs
  - Strict dependency isolation, prevents phantom deps
  - Great for monorepos via `pnpm-workspace.yaml`
  - Deterministic lockfile `pnpm-lock.yaml`

  - Common cmds:
    - `pnpm i` → install
    - `pnpm add <pkg>` → add dep
    - `pnpm remove <pkg>` → remove dep
    - `pnpm run <script>` or `pnpm <script>`
    - `pnpm dlx <cmd>` → like `npx`

  - Caveats:
    - Some packages need `--shamefully-hoist`
    - Stricter CLI validation
    - Node version mgmt via `pnpm env use`

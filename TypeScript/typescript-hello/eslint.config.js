import js from "@eslint/js";
import tseslint from "@typescript-eslint/eslint-plugin";
import tsparser from "@typescript-eslint/parser";
import prettier from "eslint-config-prettier";
import simpleImportSort from "eslint-plugin-simple-import-sort";
import globals from "globals";

export default [
  // ignore compiled output
  { ignores: ["dist", "node_modules"] },

  js.configs.recommended,

  {
    files: ["**/*.ts", "**/*.tsx"],
    languageOptions: {
      parser: tsparser,
      parserOptions: { projectService: true, sourceType: "module" },
      globals: globals.node, // enable Node globals: console, process, etc.
    },
    plugins: {
      "@typescript-eslint": tseslint,
      "simple-import-sort": simpleImportSort,
    },
    rules: {
      ...tseslint.configs.recommended.rules, // the ... is the spread operator in JS/TS, unpacks arrays/lists
      "simple-import-sort/imports": "warn",
      "simple-import-sort/exports": "warn",
    },
  },

  prettier,
];

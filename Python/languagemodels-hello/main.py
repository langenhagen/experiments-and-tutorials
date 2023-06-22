#!/usr/bin/env python3
"""Showcase the the 3rd party educational package `languagemodels`.

`languagemodels` is an easy to use, tiny educational, experimental LLM.

See: https://github.com/jncraton/languagemodels
"""
import languagemodels as lm
import wikipedia as wiki

print("\n--- 1 more RAM more smart ---")
lm.set_max_ram('8gb')  # 512mb should be the default

print("\n--- 2 do() - respond to an input ---")

q = "What color is the sky?"
a = lm.do(q)
print(f"q: {q}\na: {a}")


q = "Translate to English: Hola, mundo!"
a = lm.do(q)
print(f"q: {q}\na: {a}")

q = "What is the capital of Germany?"
a = lm.do(q)
print(f"q: {q}\na: {a}")


print("\n--- 3 complete() - complete a sentence ---")

q = "Everybody hates the police because"
a = lm.complete(q)
print(f"q: {q}\na: {a}.")


print("\n--- 4 external retrieval ---")

print(f"{lm.get_wiki('Chemistry')=}.")  # get_wiki gets the wikipedia summary
print(f"{lm.get_date()=}.")
# only seems to work for select coordinate ranges
print(f"{lm.get_weather(41.8, -87.6)=}.")


print("\n--- 5 little chatbot ---")

history = (
    "System: Respond as a helpful assistant. "
    f"The current time is {lm.get_date()}. "
    "Your name is Jules.\n"
)

q = "What time is it?"
history += "User: " + q + "\nAssistant: "
a = lm.chat(history)
history += f"{a}\n"
print(f"q: {q}\na: {a}.")

q = "What is your name?"
history += "User: " + q + "\nAssistant: "
a = lm.chat(history)
history += f"{a}\n"
print(f"q: {q}\na: {a}.")


print("\n--- 6 store and retrieve semantic info simple ---")

lm.store_doc("Mars is a planet")
lm.store_doc("The sun is hot")

q = "What is Mars?"
a=lm.load_doc(q)
print(f"q: {q}\na: {a}.")  # "Mars is a planet"

q = "What is Dandala?"
a=lm.load_doc(q)
print(f"q: {q}\na: {a}.")  # "The sun is hot." Thanks, I guess


print("\n--- 7 store and retrieve semantic info more more realworld scenario ---")

context = wiki.page("A Midsummer Night's Dream", auto_suggest=False).content
for i in range(0, len(context), 1000):
    part = context[i:i+1000]
    lm.store_doc(part)

q="tell me about Fairies"
a=lm.get_doc_context(q)
print(f"q: {q}\na: {a}.")

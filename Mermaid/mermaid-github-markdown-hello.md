# Mermaid Hello
Showcase the usage of mermaid diagrams in Github Markdown.

See: https://mermaid-js.github.io/mermaid/#/

Mermaid renders nicely on GitHub, but not in a VSCode Markdown preview.
However, there is a VSCode Plugin to support Markdown Mermaid: https://marketplace.visualstudio.com/items?itemName=bierner.markdown-mermaid

```mermaid
graph TD;
    A-->B;
    A-->C;
    B-->D;
    C-->D;
```

"is p3.2xlarge working? No"
"is option g4dn.xlarge working? No"
"is option g4dn.2xlarge working? No"
"is option g4dn.4xlarge working? No"
"is the time since we first attemted to start an instance >n minutes ? No"
"sleep n seconds"
"is p3.2xlarge working? No"
...


Another example:
```mermaid
graph LR
    note_time[Note the time] --> pick_first[Pick the first type]
    pick_first --> available?{Is the type available?}
    available? --> |yes| ok(Great!)
    available? --> |no| next?{Is there a next type?}
    next? --> |no| time?{Still time left?}
    next? --> |yes| pick_next[Pick next type]
    pick_next --> available?
    time? --> |no| fail[Fail]
    time? --> |yes| sleep[Sleep a bit]
    sleep --> pick_first
```

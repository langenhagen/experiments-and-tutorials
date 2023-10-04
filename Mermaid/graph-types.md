# Graph Types
Types of graphs of which I know that Mermaid supports them.

## 1. Flowchart
With `flowchart`:
```mermaid
flowchart LR

A[Hard] -->|Text| B(Round)
B --> C{Decision}
C -->|One| D[Result 1]
C -->|Two| E[Result 2]
```

With `graph`:
```mermaid
graph LR;
    A[Start] --> B[Process]
    B --> C{Decision}
    C -->|Yes| D[Result 1]
    C -->|No| E[Result 2]
    D --> F[End]
    E --> F
```

## 2. Sequence Diagram
```mermaid
sequenceDiagram
    participant A as User
    participant B as System
    A->>B: Request
    B-->>A: Response
```

## 3. Gantt Chart
```mermaid
gantt
    dateFormat YYYY-MM-DD
    title Project Schedule
    section Planning
    Define Tasks :a1, 2023-01-01, 7d
    section Development
    Task 1 :a2, 2023-01-08, 5d
    Task 2 :after a2, 3d
```

## 4. Pie Chart
```mermaid
pie
    title Expenses by Category
    "Food" : 42
    "Rent" : 20
    "Utilities" : 12
    "Entertainment" : 26
```

## 5. Class Diagram
```mermaid
classDiagram
    ClassA --|> ClassB : Inheritance
    ClassA : +methodA()
    ClassB : -methodB()
```

## 6. Entity-Relationship Diagram
```mermaid
erDiagram
  CUSTOMER ||--o{ ORDER : places
  ORDER ||--|{ LINE-ITEM : contains
  CUSTOMER }|..|{ DELIVERY-ADDRESS : uses
```

## 7. State Diagram
```mermaid
stateDiagram
    [*] --> State1
    State1 --> State2
    State1 --> [*]
    State2 --> State3
    State3 --> State1
```

### 7.1. State Diagram 2
Don't know what's the difference to `stateDiagram` but here we are
```mermaid
stateDiagram-v2
[*] --> Still
Still --> [*]
Still --> Moving
Moving --> Still
Moving --> Crash
Crash --> [*]
```

## 8. User Journey Diagram
```mermaid
journey
    title User Journey
    section Sign Up
    Start --> Register: User registers
    Register --> Verify: Verify email
    Verify --> Complete: Complete registration
    Complete --> End: Done
```


## 9. GitGraph
```mermaid
gitGraph:
    options
    {
        "nodeSpacing": 100,
        "nodeRadius": 10,
        "layoutDirection": "LR"
    }
    end
    commit
    branch new-feature
    checkout new-feature
    commit
    commit
    branch master
    checkout master
    commit
    commit
    merge new-feature
```

## 10. Mind Map
```mermaid
mindmap
  root((my mindmap))
    Origins
      Long history
      ::icon(fa fa-book)
      Popularisation
        British popular psychology author Tony Buzan
    Research
      On effectiveness<br/>and features
      On Automatic creation
        Uses
            Creative techniques
            Strategic planning
            Argument mapping
    Tools
      Pen and paper
      Mermaid
```

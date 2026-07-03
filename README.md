# Java Concurrency Problems

Java implementations of classic concurrency and thread synchronization problems, solved using different mechanisms: `wait/notify`, `join`, semaphores, and locks.

## 📋 Problems Included

| # | Problem | Main Concept |
|---|---------|---------------|
| 01 | Threads | Thread creation and lifecycle |
| 02 | Football | Basic thread synchronization |
| 03 | Sleep & Rocket | `Thread.sleep()` and timing |
| 04 | Join | `Thread.join()` |
| 05 | Synchronization | `synchronized` blocks |
| 06 | Wait | `wait()` / `notify()` |
| 07-09 | Dining Philosophers | Deadlocks and their solutions (wait, lock) |
| 10 | The Smoker | Cigarette smokers problem |
| 11 | Sleeping Barber | Semaphores |
| 12 | Unisex Bathroom | Mutual exclusion with multiple conditions |

## 🛠️ Technologies

- Java
- Native Java synchronization mechanisms and `java.util.concurrent`

## 🚀 How to Run

Each folder contains its own source code in `src/`. To run any of the exercises:

```bash
cd 07-Filosofs/src
javac *.java
java MainClassName
```

## 📚 Context

Project developed during the DAM cycle, 
module M09 - Service and Process Programming, 
as practice for the main synchronization problems in concurrent systems.

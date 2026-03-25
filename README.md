=========================================================
Use Case 12: System Recovery & State Persistence
=========================================================
Goal:
To transition the BookMyStay system from a "Volatile" state (where data is lost on exit) to a "Persistent" state. This ensures that room inventory and booking records survive application restarts, crashes, or power failures.

Architectural Concept: Serialization
In Java, we use Object Serialization to convert the live Map and List objects in your computer's RAM into a linear stream of bytes that can be saved to a .ser or .dat file.

The Persistence Lifecycle
Startup (Deserialization): The system checks for an existing hotel_state.ser file. If found, it "thaws" the data back into memory.

Runtime: The system operates normally, modifying the inventory in RAM.

Shutdown (Serialization): Before the program closes, it "freezes" the current state of the objects back into the file.
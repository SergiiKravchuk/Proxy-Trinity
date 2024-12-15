### CASE 1

### Task setup
**Package**:
- `core` - local classes, consider it as local project or module, some parts of the code in this package should be updated in order to finish the task;  
- `external` - external classes, consider it as an external library or module, therefore you cannot update the code;

#### Core Classes:
 - `org.codeus.patterns.case1.core.Case1Client` - main class that calls all other local and external classes. The method's logic should be updated in order to finish the task;
 - `org.codeus.patterns.case1.core.ProcessingLogic` - a simple service that does some processing;
 - `org.codeus.patterns.case1.core.profiler.Profiler` - a local interface of profiling tool;
 - `org.codeus.patterns.case1.core.profiler.SimpleTimeProfiler`- a local implementation of the Profiler interface to conduct time-based profiling.

#### External Classes:
 - `org.codeus.patterns.case1.external.MemoryProfiler` - an external interface of profiling tool. Should not be changed;
 - `org.codeus.patterns.case1.external.ExternalMemoryProfiler` - an external implementation of the MemoryProfiler interface to conduct memory-based profiling. Should not be changed;

### Task Context
Imagine that Clients of your project asked to check memory consumption of the project and try to optimize it, as they want to migrate to another server instance type. 
You know only the overall memory consumption, and it won't fit in new instances. Therefore, you need to profile different parts of the project.  
There is no time to implement your own Memory Profiler, so you decided to use a good external library. 
One problem, the interface of that library differs from project's one and its integration may cause a lot of core code changes.
You want to avoid massive code changes.


### Task Goal
Apply one design pattern to the module to support other (external) profilers without changing source code, e.g. `org.codeus.patterns.case1.external.ExternalMemoryProfiler`.

**!NOTE:** This case DOESN'T imply the simultaneous use of different profiling tools.

### Rules:
- You should NOT update code in` org.codeus.patterns.case1.external` and `org.codeus.patterns.external_libraries` packages.
- You should NOT create instances of `ProcessingLogic`, `TimeProfiler` and `MemoryProfiler` by your own, use
  ones that is provided in the `Case1Client`. You can create instances for any other classes, if needed.

### Hints:
<details> 
  <summary>Hint 1: What patter to use? </summary>
   - Use Adapter pattern
</details>

<details> 
  <summary>Hint 2: Step-by-step implementation (each step can be seen individually)  </summary>
   <details> 
        <summary>Step 1</summary>
- Create a class MemoryProfilerAdapter that implements org.codeus.patterns.case1.core.profiler.Profiler.
    </details>
    <details> 
        <summary>Step 2</summary>
- Add a private field org.codeus.patterns.case1.external.MemoryProfiler memoryProfiler in MemoryProfilerAdapter.
    </details>
    <details> 
        <summary>Step 3</summary>
- Implement a constructor in MemoryProfilerAdapter to initialize memoryProfiler.
    </details>
    <details> 
        <summary>Step 4</summary>
- Override the process(Message message) method in BaseDecorator to delegate the call to processingLogic.
    </details>
    <details> 
        <summary>Step 5</summary>
- Override the startProfiling() method in MemoryProfilerAdapter to call startMemoryProfiling() on memoryProfiler.
    </details>
    <details> 
        <summary>Step 6</summary>
- Override the endProfiling() method in MemoryProfilerAdapter to call endMemoryProfiling() on memoryProfiler and format the results from getResults().
    </details>
    <details> 
        <summary>Step 7</summary>
- Adjust org.codeus.patterns.case1.core.Case1Client#doHeavyLifting to use the new MemoryProfilerAdapter class.
    </details>
</details>
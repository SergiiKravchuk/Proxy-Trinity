### CASE 2

### Task setup
**Package**:
- `core` - local classes, consider it as local project or module, some parts of the code in this package should be updated in order to finish the task;
- `external` - external classes, consider it as an external library or module, therefore you cannot update the code;

There is an additional module for commonly used external libraries -`org.codeus.patterns.external_libraries`. Treat this module the same as the `external`.

#### Core Classes:
- `org.codeus.patterns.case2.core.Case2Client` - main class that calls all other local and external classes. The method's logic should be updated in order to finish the task;
- `org.codeus.patterns.case2.core.LocalService` - a local service interface;
- `org.codeus.patterns.case2.core.LocalServiceImpl`- a local implementation of the `LocalService` interface that does some processing.

#### External Classes:
- `org.codeus.patterns.case2.external.ExternalService` - a service from an external module of the same project that uses `LocalService` instance to do some processing. Should not be changed;
- `org.codeus.patterns.external_libraries.orange.OrangeTimeProfiler` - an external time-based profiler tool. Should not be changed;

### Task Context
Imagine two teams, each responsible for oen module `core` and `external`. You're in the first team.
During high-load hours you've noticed that system has poor performance somewhere when communicating with `org.codeus.patterns.case2.external.ExternalService` module.
You want to make sure that `ExternalService` is correctly using `org.codeus.patterns.case2.core.LocalServiceImpl` by conducting a stress testing and profiling using `OrangeTimeProfiler` but you cannot use the profiler directly in the `ExternalService` because you don't own the codebase.

### Task Goal
Apply one design pattern to the module to profile `org.codeus.patterns.case2.core.LocalServiceImpl` using (`OrangeTimeProfiler`) inside the `org.codeus.patterns.case2.external.ExternalService` without changing source code of the `LocalServiceImpl` and `ExternalService`.


### Rules:
- You should NOT update code in` org.codeus.patterns.case2.external` and `org.codeus.patterns.external_libraries` packages.
- You should NOT create instances of `LocalService`, `ExternalService` and `TimeProfiler` by your own, use ones that is provided in the `Case2Client`. You can create instances for any other classes, if needed.


### Hints:
<details> 
  <summary>Hint 1: What patter to use? </summary>
   - Use Proxy pattern
</details>

<details> 
  <summary>Hint 2: Step-by-step implementation (each step can be seen individually)  </summary>
   <details> 
        <summary>Step 1</summary>
- Create a Proxy class that implements org.codeus.patterns.case2.core.LocalService.
    </details>
    <details> 
        <summary>Step 2</summary>
- Add a private field org.codeus.patterns.case2.core.LocalService localService in Proxy.
    </details>
    <details> 
        <summary>Step 3</summary>
- Add a protected field org.codeus.patterns.external_libraries.orange.TimeProfiler timeProfiler in Proxy.
    </details>
    <details> 
        <summary>Step 4</summary>
- Implement a constructor in Proxy to initialize localService and timeProfiler.
    </details>
    <details> 
        <summary>Step 5</summary>
- Override the process() method in Proxy to start profiling, delegate the call to localService, end profiling, and print the elapsed time.
    </details>
    <details> 
        <summary>Step 6</summary>
- - Adjust org.codeus.patterns.case2.core.Case2Client#doHeavyLifting to use the new Proxy class.
    </details>
</details>
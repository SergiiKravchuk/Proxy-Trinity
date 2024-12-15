### CASE 3

### Task setup
**Package**:
- `core` - local classes, consider it as local project or module, some parts of the code in this package should be updated in order to finish the task;
- `external` - external classes, consider it as an external library or module, therefore you cannot update the code;

There is an additional module for commonly used external libraries -`org.codeus.patterns.external_libraries`. Treat this module the same as the `external`.

#### Core Classes:
- `org.codeus.patterns.case3.core.Case3Client` - main class that calls all other local and external classes. The method's logic should be updated in order to finish the task;
- `org.codeus.patterns.case3.core.LocalProcessingLogic` - a local implementation of the `org.codeus.patterns.case3.external.ProcessingLogic` that does some `org.codeus.patterns.external_libraries.poodle.data.Message` processing;

#### External Classes:
- `org.codeus.patterns.case3.external.ExternalService` - a service from an external module of the same project that uses `LocalService` instance to do some processing. Should not be changed;
- `org.codeus.patterns.case3.external.ProcessingLogic` - an external module interface for a processing service;
- `org.codeus.patterns.case3.external.PerformanceOrientedService` - an external module service that targets performance processing using `ProcessingLogic` instance;
- `org.codeus.patterns.case3.external.DataConsistencyOrientedService` - an external module service that targets data consistency and uses `ProcessingLogic` instance for processing;
- `org.codeus.patterns.external_libraries.poodle.data.producer.EventMessageProducer` - an external module tool that produces `org.codeus.patterns.external_libraries.poodle.data.Message`s;
- `org.codeus.patterns.external_libraries.poodle.data.filter.EventMessageFilter` - an external module tool that filters `org.codeus.patterns.external_libraries.poodle.data.Message`s;
- `org.codeus.patterns.external_libraries.orange.OrangeTimeProfiler` - an external time-based profiler tool. Should not be changed;


### Task Context
Imagine two teams, each responsible for one module `core` and `external`. You're in the first team.
During high-load hours you've noticed that the system has poor performance somewhere when communicating with `org.codeus.patterns.case3.external.PerformanceOrientedService` module.
You want to make sure that `PerformanceOrientedService` is correctly using `org.codeus.patterns.case3.core.LocalServiceImpl` by conducting stress testing and profiling using `OrangeTimeProfiler` but you cannot use the profiler directly in the `PerformanceOrientedService` because you don't own the codebase.

Also, your company integrated a new Client that owns `DataConsistencyOrientedService` service. That service requires a filter - `org.codeus.patterns.external_libraries.poodle.data.filter.EventMessageFilter` - that should be applied to data before pushing it to the service. 
Filter cannot be applied to the `EventMessageProducer` because it is also used by other services.
Additionally, you want your code performs good when interacting with the new service by profiling `LocalServiceImpl` inside `DataConsistencyOrientedService` using the same `OrangeTimeProfiler`.

### Task Goal
Apply one design pattern to the module to apply different tools (profiling - `org.codeus.patterns.external_libraries.orange.OrangeTimeProfiler`, filtering - `org.codeus.patterns.external_libraries.poodle.data.filter.EventMessageFilter`) to your `org.codeus.patterns.case3.core.LocalServiceImpl` in runtime without changing source code of the `LocalServiceImpl` and any of external services/libraries.

**!NOTE:** This case DOES imply the simultaneous use of different tools.
**!NOTE:** Filtered `org.codeus.patterns.external_libraries.poodle.data.Message` should be indicated with `org.codeus.common.Result.SKIPPED` result status.

### Rules:
- You should NOT update code in` org.codeus.patterns.case3.external` and `org.codeus.patterns.external_libraries` packages.
- You should NOT create instances of `LocalProcessingLogic`, `PerformanceOrientedService`, `DataConsistencyOrientedService`, `TimeProfiler`, `EventMessageFilter` by your own, use ones that is provided in the `Case3Client`. You can create instances for any other classes, if needed.


### Hints:
<details> 
  <summary>Hint 1: What patter to use? </summary>
   - Use Decorator pattern
</details>

<details> 
  <summary>Hint 2: Step-by-step implementation (each step can be seen individually)  </summary>
   <details> 
        <summary>Step 1</summary>
- Create an abstract class BaseDecorator that implements `org.codeus.patterns.case3.external.ProcessingLogic`
    </details>
    <details> 
        <summary>Step 2</summary>
- Add a protected field `org.codeus.patterns.case3.external.ProcessingLogic` processingLogic in BaseDecorator.
    </details>
    <details> 
        <summary>Step 3</summary>
- Implement a constructor in BaseDecorator to initialize processingLogic.
    </details>
    <details> 
        <summary>Step 4</summary>
- Override the process(Message message) method in BaseDecorator to delegate the call to processingLogic.
    </details>
    <details> 
        <summary>Step 5</summary>
- Create a concrete decorator class MessageFilteringDecorator that extends BaseDecorator.
    </details>
    <details> 
        <summary>Step 6</summary>
- Add a protected field org.codeus.patterns.external_libraries.poodle.data.filter.Filter messageFilter in MessageFilteringDecorator.
    </details>
    <details> 
        <summary>Step 7</summary>
- Implement a constructor in MessageFilteringDecorator to initialize processingLogic and messageFilter.
    </details>
    <details> 
        <summary>Step 8</summary>
- Override the process(Message message) method in MessageFilteringDecorator to call org.codeus.patterns.external_libraries.poodle.data.filter.Filter#filter on a message before delegating it to BaseDecorator. If org.codeus.patterns.external_libraries.poodle.data.filter.Filter#filter returns false - print message and return org.codeus.common.Result.SKIPPED.
    </details>
    <details> 
        <summary>Step 9</summary>
- Create another concrete decorator class TimeProfilingDecorator that extends BaseDecorator.
    </details>
    <details> 
        <summary>Step 10</summary>
- Add a protected field org.codeus.patterns.external_libraries.orange.TimeProfiler timeProfiler in TimeProfilingDecorator.
    </details>
    <details> 
        <summary>Step 11</summary>
- Implement a constructor in TimeProfilingDecorator to initialize processingLogic and timeProfiler.
    </details>
    <details> 
        <summary>Step 12</summary>
- Override the process(Message message) method in TimeProfilingDecorator to profile the time taken for processing before delegating to BaseDecorator.
    </details>
    <details> 
        <summary>Step 13</summary>
- Adjust org.codeus.patterns.case3.core.Case3Client to use the new TimeProfilingDecorator in the doHeavyLiftingPart1() and TimeProfilingDecorator + MessageFilteringDecorator in the doHeavyLiftingPart2()
    </details>
</details>

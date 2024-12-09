CASE 3

**Goal:** adjust the code within `case3.core` package to make support different combinations of tools to be applied to the
`LocalProcessingLogic`.<br>
Adjust `Case3Client class` to demonstrate your changes.

**!NOTE:** This case DOES imply the simultaneous use of different tools.
**!NOTE:** You should not create instances of `LocalProcessingLogic`, `PerformanceOrientedService`, `ValidationOrientedService`, `TimeProfiler`, `Validator` by your own, use ones that is provided in the `Case3Client`. Any other class instance you can create.

**Description:** currently `LocalProcessingLogic` is used by external Services: `PerformanceOrientedService` and `ValidationOrientedService`.
There was an ask to add support for new logic: 
- profile `LocalProcessingLogic` when processing inputs from the `PerformanceOrientedService`
- profile `LocalProcessingLogic` and validate inputs when working with the `ValidationOrientedService`. 

Additionally, a new service is anticipated to be added, and it would need the same setup as the 2nd service (profiling + validation)
plus one more extra step before actually running the `LocalProcessingLogic`, e.g. caching.

_Incorporating these changes in the same manner as the existing one will make the code too complex and hard to support._

**!NOTE**: `PerformanceOrientedService` and `ValidationOrientedService` should not be a part the changes.


Hints:
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
- Create a concrete decorator class MessageValidationDecorator that extends BaseDecorator.
    </details>
    <details> 
        <summary>Step 6</summary>
- Add a protected field Validator (parametrized) messageValidator in MessageValidationDecorator.
    </details>
    <details> 
        <summary>Step 7</summary>
- Implement a constructor in MessageValidationDecorator to initialize processingLogic and messageValidator.
    </details>
    <details> 
        <summary>Step 8</summary>
- Override the process(Message message) method in MessageValidationDecorator to validate the message before delegating to BaseDecorator.
    </details>
    <details> 
        <summary>Step 9</summary>
- Create another concrete decorator class TimeProfilingDecorator that extends BaseDecorator.
    </details>
    <details> 
        <summary>Step 10</summary>
- Add a protected field TimeProfiler timeProfiler in TimeProfilingDecorator.
    </details>
    <details> 
        <summary>Step 11</summary>
- Implement a constructor in TimeProfilingDecorator to initialize processingLogic and timeProfiler.
    </details>
    <details> 
        <summary>Step 12</summary>
- Override the process(Message message) method in TimeProfilingDecorator to profile the time taken for processing before delegating to BaseDecorator.
    </details>
</details>

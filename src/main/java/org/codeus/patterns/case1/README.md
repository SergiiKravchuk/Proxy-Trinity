CASE 1

**Goal:** adjust the code within `case1.core` package to make support of different Profiling tools (including third party);<br>
Adjust `Case1Client class` to demonstrate your changes.

**!NOTE:** This case DOESN'T imply the simultaneous use of different profiling tools.
**!NOTE:** You should not create instances of `ProcessingLogic`, `TimeProfiler` and `MemoryProfiler` by your own, use ones that is provided in the `Case1Client`. Any other class instance you can create. 

**Description:** currently `ProcessingLogic` supports two tools to `TimeProfiler` and `MemoryProfiler`.
Imagine you need to add support for other Profiling tools. 
_Incorporating them in the same manner as the existing tools will make the code too complex and hard to support._


Hints:
<details> 
  <summary>Hint 1: What patter to use? </summary>
   - Use Adapter pattern
</details>
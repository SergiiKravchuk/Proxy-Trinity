CASE 2

**Goal:** adjust the code within `case2.core` package to extend capabilities (in this case add Profiling) of the
`LocalService` implementations without changing the original interface/class and its users (e.g. `ExternalService`).<br>
Adjust `Case1Client class` to demonstrate your changes.

**!NOTE:** You should not create instances of `LocalService`, `ExternalService` and `TimeProfiler` by your own, use ones that is provided in the `Case2Client`. Any other class instance you can create.

**Description:** currently `LocalServiceImpl` is a simple implementation of the interface `LocalService`. 
The main user of that implementation is `ExternalService`.
Imagine you need to extend the capabilities of the `LocalServiceImpl` in a way 
that the original class and its interface and im `ExternalService` are not changed.


Hints:
<details> 
  <summary>Hint 1: What patter to use? </summary>
   - Use Proxy pattern
</details>
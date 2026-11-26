# Activity 1: Weather Station — Self-Check Notes

1. **What is the role of the `Subject` interface?**
   It defines the contract for any observable publisher (`registerObserver`, `removeObserver`, `notifyObservers`), allowing observers and subjects to stay loosely coupled through abstractions.
2. **What happens if you call `notifyObservers()` without registering any observers?**
   The loop over the empty `observers` list executes zero iterations and nothing breaks or throws an exception—no notifications are sent.
3. **What is the difference between `registerObserver()` and `removeObserver()`?**
   `registerObserver()` subscribes an `Observer` by adding it to the `observers` list so it receives future updates, whereas `removeObserver()` unsubscribes it by removing it from the list so it stops receiving updates (and can be garbage-collected).
4. **Why is the `observers` list stored as an instance variable?**
   Each `WeatherData` object needs its own persistent list of subscribers that survives across multiple `registerObserver()`, `removeObserver()`, and `setMeasurements()` calls.

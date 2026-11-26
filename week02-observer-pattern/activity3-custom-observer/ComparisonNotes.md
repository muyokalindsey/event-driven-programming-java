# Activity 3: Refactoring & Coupling Comparison Notes

## Step 2: Identifying the Coupling Problem

1. **What happens if we want to add a third display class? Do we have to modify `DataModel`?**
   Yes—in the tightly coupled version, `DataModel.setValue()` explicitly instantiates and calls `new LabelDisplay()` and `new BarChartDisplay()`, so adding a third display forces us to edit `DataModel`.
2. **What happens if `LabelDisplay` is deleted or renamed?**
   `DataModel` fails to compile because it directly depends on the concrete `LabelDisplay` class name and its `showValue(int)` method.
3. **Can we easily test `DataModel` in isolation?**
   No. Any test calling `model.setValue(...)` automatically constructs real `LabelDisplay` and `BarChartDisplay` objects, making isolated unit testing or mocking difficult.

---

## Step 4: Tightly Coupled vs. Observer Pattern

| Aspect | Tightly Coupled | Observer Pattern |
|---|---|---|
| **Adding a new display** | Modify `DataModel` directly | Create a class implementing `DataObserver` and register it |
| **Testing `DataModel` in isolation** | Hard — directly depends on concrete display classes | Easy — test `DataModel` with no observers or with a simple mock `DataObserver` |
| **Removing a display** | Modify `DataModel` to delete the call | Simply don't register it (or call `removeObserver`) |
| **Reusability** | Low — `DataModel` is tied to specific UI classes | High — `DataModel` only depends on `DataObserver` |

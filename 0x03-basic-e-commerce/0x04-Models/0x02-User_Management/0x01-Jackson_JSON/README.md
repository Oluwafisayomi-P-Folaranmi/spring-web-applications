## **Jackson JSON**

### **`JsonProperty`**

The `@JsonProperty` annotation comes from the Jackson library (`com.fasterxml.jackson.annotation.JsonProperty`). It customizes how the password field is serialized and deserialized in JSON operations.

In the following table are other `JsonProperty.Access` Options:

| Option | Description |
|--------|-------------|
| `JsonProperty.Access.READ_ONLY` | The field is **only included in output JSON** (response), but ignored in input JSON (request). Hide **passwords** in requests but allow them in responses |
| `JsonProperty.Access.WRITE_ONLY` | The field is **only accepted in input JSON** (request), but omitted in output JSON (response). Accept **passwords** in requests but hide them in responses |
| `JsonProperty.Access.READ_WRITE` | The field is **included in both input and output JSON**. Allow fields like **email** to be both read and written |

# Type name shadows a built-in namespace

`qa-error-type-shadows-namespace` &middot; Error Handling &middot; Code Smell &middot; severity MAJOR &middot; enabled in the recommended profile

This rule detects classes or interfaces whose names match a built-in Apex namespace such as Schema, System, Database, or Messaging. Shadowing a namespace prevents the compiler from resolving references to the platform namespace, causing compilation errors or unexpected behavior throughout the project.

## Noncompliant code example

```apex
public class Schema { // Noncompliant — shadows the Schema namespace
    public String objectName;

    public Schema(String name) {
        this.objectName = name;
    }

    public String getObjectName() {
        return objectName;
    }
}

public class Database { // Noncompliant — shadows the Database namespace
    public void save(SObject record) {
        insert record;
    }
}
```

## Compliant solution

```apex
public class SchemaHelper {
    public String objectName;

    public SchemaHelper(String name) {
        this.objectName = name;
    }

    public String getObjectName() {
        return objectName;
    }
}

public class DatabaseService {
    public void save(SObject record) {
        insert record;
    }
}
```

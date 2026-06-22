# External input must not control resource identifiers

`qa-security-resource-injection` &middot; Security &middot; Security Hotspot &middot; severity CRITICAL &middot; enabled in the recommended profile

When external input is used to construct resource identifiers — such as class names passed to Type.forName(), file paths, or endpoint URLs — an attacker can manipulate the input to access unauthorized resources, instantiate unexpected classes, or redirect operations to malicious targets. Always validate resource identifiers against an explicit allowlist before use.

## Noncompliant code example

```apex
public class PluginLoader {
    public static Object loadPlugin() {
        String className = ApexPages.currentPage()
            .getParameters().get('plugin');
        Type pluginType = Type.forName(className);       // Noncompliant
        return pluginType.newInstance();
    }
}
```

## Compliant solution

```apex
public class PluginLoader {
    private static final Set<String> ALLOWED_PLUGINS = new Set<String>{
        'ReportPlugin', 'ExportPlugin', 'NotificationPlugin'
    };

    public static Object loadPlugin() {
        String className = ApexPages.currentPage()
            .getParameters().get('plugin');
        if (!ALLOWED_PLUGINS.contains(className)) {
            throw new SecurityException('Unknown plugin: ' + className);
        }
        Type pluginType = Type.forName(className);
        return pluginType.newInstance();
    }
}
```

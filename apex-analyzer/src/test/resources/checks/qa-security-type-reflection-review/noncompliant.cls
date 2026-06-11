public class UsesReflection {
    public void run(String className) {
        Type t = Type.forName(className);
        Object instance = t.newInstance();
    }

    @AuraEnabled
    public static Object createInstance(String typeName) {
        Type t = Type.forName(typeName);
        return t.newInstance();
    }
}

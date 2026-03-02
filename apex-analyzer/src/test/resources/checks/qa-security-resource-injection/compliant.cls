public class SafeType {
    private static final Map<String, Type> ALLOWED = new Map<String, Type>{
        'AccountHandler' => AccountHandler.class,
        'ContactHandler' => ContactHandler.class
    };

    public Object createHandler(String className) {
        Type t = ALLOWED.get(className);
        if (t == null) throw new IllegalArgumentException('Unknown handler: ' + className);
        return t.newInstance();
    }

    public void dispatch(String handlerName) {
        Type handlerType = ALLOWED.get(handlerName);
        if (handlerType == null) throw new IllegalArgumentException('Unknown handler');
        IHandler handler = (IHandler) handlerType.newInstance();
        handler.execute();
    }
}

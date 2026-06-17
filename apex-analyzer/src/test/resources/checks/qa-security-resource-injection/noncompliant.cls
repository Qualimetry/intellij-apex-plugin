public class UnsafeType {
    public Object createHandler(String className) {
        Type t = Type.forName(className);
        return t.newInstance();
    }

    public void dispatch(String handlerName) {
        Type handlerType = Type.forName(handlerName);
        IHandler handler = (IHandler) handlerType.newInstance();
        handler.execute();
    }
}

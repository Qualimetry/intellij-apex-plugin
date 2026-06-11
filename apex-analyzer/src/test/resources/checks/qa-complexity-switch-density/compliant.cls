public class DelegatedSwitch {
    private static final Map<String, IHandler> HANDLERS = new Map<String, IHandler>{
        'A' => new HandlerA(),
        'B' => new HandlerB(),
        'C' => new HandlerC()
    };

    public void process(String type) {
        IHandler handler = HANDLERS.get(type);
        if (handler != null) {
            handler.execute();
        }
    }
}

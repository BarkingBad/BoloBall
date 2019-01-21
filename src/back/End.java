package back;

public enum End {
    YES(true),
    NO(false);

    private boolean state;

    End(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }
}

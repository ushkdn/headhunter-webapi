package headhunter_webapi.entity;

public enum WorkMode {
    Distant("Distant work"),
    Office("In office"),
    Hybrid("Hybrid work");

    private String _workMode;
    WorkMode(String workMode) {
        _workMode =workMode;
    }
    public String getWorkMode(){
        return _workMode;
    }
}

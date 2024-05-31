package headhunter_webapi.entity;

public enum WorkPlan {
    Fullday("Full day"),
    Flexible("Flexible shedule");

    private String _workPlan;

    WorkPlan(String workPlan){
        _workPlan=workPlan;
    }

    public String getWorkPlan(){
        return _workPlan;
    }

}

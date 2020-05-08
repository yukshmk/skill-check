package q005;

/**
 * 作業時間管理クラス
 * 自由に修正してかまいません
 */
public class WorkData {
    /** 社員番号 */
    private String number;

    /** 部署 */
    private String department;

    /** 役職 */
    private String position;

    /** Pコード */
    private String pCode;

    /** 作業時間(分) */
    private int workTime;

    public WorkData(String num, String dep, String pos, String pc, int wt) {
        setNumber(num);
        setDepartment(dep);
        setPosition(pos);
        setPCode(pc);
        setWorkTime(wt);
    }

    private void setNumber(String num) {
        number = num;
    }
    private void setDepartment(String dep) {
        department = dep;
    }
    private void setPosition(String pos) {
        position = pos;
    }
    private void setPCode(String pc) {
        pCode = pc;
    }
    private void setWorkTime(int wt) {
        workTime = wt;
    }
    public String getNumber(){
        return number;
    }
    public String getDepartment() {
        return department;
    }
    public String getPosition() {
        return position;
    }
    public String getPCode() {
        return pCode;
    }
    public int getWorkTime() {
        return workTime;
    }
}

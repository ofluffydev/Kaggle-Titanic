public class TestPassenger {
    private String id;
    private String pclass;
    private String name;
    private String sex;
    private String age;
    private String sibSp;
    private String parch;
    private String ticket;
    private String fare;
    private String cabin;
    private String embarked;

    public TestPassenger(String id, String pclass, String name, String sex, String age, String sibSp, String parch, String ticket, String fare, String cabin, String embarked) {
        this.id = id;
        this.pclass = pclass;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.sibSp = sibSp;
        this.parch = parch;
        this.ticket = ticket;
        this.fare = fare;
        this.cabin = cabin;
        this.embarked = embarked;
    }

    @Override
    public String toString() {
        return "TestPassenger{" + "id='" + id + '\'' + ", pclass='" + pclass + '\'' + ", name='" + name + '\'' + ", sex='" + sex + '\'' + ", age='" + age + '\'' + ", sibSp='" + sibSp + '\'' + ", parch='" + parch + '\'' + ", ticket='" + ticket + '\'' + ", fare='" + fare + '\'' + ", cabin='" + cabin + '\'' + ", embarked='" + embarked + '\'' + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPclass() {
        return pclass;
    }

    public void setPclass(String pclass) {
        this.pclass = pclass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSibSp() {
        return sibSp;
    }

    public void setSibSp(String sibSp) {
        this.sibSp = sibSp;
    }

    public String getParch() {
        return parch;
    }

    public void setParch(String parch) {
        this.parch = parch;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getEmbarked() {
        return embarked;
    }

    public void setEmbarked(String embarked) {
        this.embarked = embarked;
    }
}

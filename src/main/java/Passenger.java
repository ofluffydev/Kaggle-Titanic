public class Passenger {
    // PassengerId,Survived,Pclass,Name,Sex,Age,SibSp,Parch,Ticket,Fare,Cabin,Embarked
    private String id;
    private String survived;
    private String passengerClass;
    private String name;
    private String sex;
    private String age;
    private String sibSp;
    private String parch;
    private String ticket;
    private String fare;
    private String cabin;
    private String embarked;

    @Override
    public String toString() {
        return "Passenger{" + "id='" + id + '\'' + ", survived='" + survived + '\'' + ", passengerClass='" + passengerClass + '\'' + ", name='" + name + '\'' + ", sex='" + sex + '\'' + ", age='" + age + '\'' + ", sibSp='" + sibSp + '\'' + ", parch='" + parch + '\'' + ", ticket='" + ticket + '\'' + ", fare='" + fare + '\'' + ", cabin='" + cabin + '\'' + ", embarked='" + embarked + '\'' + '}';
    }

    public Passenger(String id, String survived, String passengerClass, String name, String sex, String age, String sibSp, String parch, String ticket, String fare, String cabin, String embarked) {
        this.id = id;
        this.survived = survived;
        this.passengerClass = passengerClass;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurvived() {
        return survived;
    }

    public void setSurvived(String survived) {
        this.survived = survived;
    }

    public String getPassengerClass() {
        return passengerClass;
    }

    public void setPassengerClass(String passengerClass) {
        this.passengerClass = passengerClass;
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

    public boolean missingData() {
        return id.isEmpty() || survived.isEmpty() || passengerClass.isEmpty() || name.isEmpty() || sex.isEmpty() || age.isEmpty() || sibSp.isEmpty() || parch.isEmpty() || ticket.isEmpty() || fare.isEmpty() || cabin.isEmpty() || embarked.isEmpty();
    }
}

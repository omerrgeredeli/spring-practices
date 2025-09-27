package src;

public abstract class Person {
    private int id;//PersonID
    private String name;//Person name

    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public abstract void roleDescription();

    public String getInfo(){
        String s = "ID " + id + " ,Name " + name;
        return s;
    }

}

public class Account {
    int age;
    String name;
    public static void main(String[] args) {
        Account a = new Account();
        a.setAge(19);
        a.setName("Bob");
        System.out.println(a.getAge());
        System.out.println(a.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

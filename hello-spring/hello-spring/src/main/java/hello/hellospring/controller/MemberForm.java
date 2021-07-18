package hello.hellospring.controller;

public class MemberForm {
    private String name; // templates/createMemberForm.html의 ipput 태그 속 name과 매칭됨

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
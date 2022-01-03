package CICERO.Model;

public class AreaGeograficaClass implements AreaGeografica{

    private Object area;// come rappresentare un'area geografica? OPPURE ARRAYLIST DI STRINGHE PER PIU' TERRITORI
    private String toponimo;

    public AreaGeograficaClass(Object obj){
        this.area = obj;
    }

    @Override
    public String getToponimo() {
        return this.toponimo;
    }
}

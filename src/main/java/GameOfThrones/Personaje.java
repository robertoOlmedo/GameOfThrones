package GameOfThrones;

import material.tree.Position;

public class Personaje  {
    private String id;
    private String nombre;
    private String apellido;
    private String genero;
    private String edad;

    public Personaje(){
        this.id= "#";
        this.nombre= "Root";
        this.apellido="Slash";
        this.edad="15000";
        this.genero="F";



    }

    public Personaje(String id, String nombre, String apellido, String genero, String edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "GameOfThrones.Personaje{" + "id='" + id + '\'' + ", nombre='" + nombre + '\'' + ", apellido='" + apellido + '\'' +
                ", genero='" + genero + '\'' + ", edad='" + edad + '\'' + '}';
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

}


package org.formacio.setmana2.domini;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_matricules")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mat_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "mat_alumne")
    private Alumne alumne;
    @OneToOne
    @JoinColumn(name = "mat_curs")
    private Curs curs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alumne getAlumne() {
        return alumne;
    }

    public void setAlumne(Alumne alumne) {
        this.alumne = alumne;
    }

    public Curs getCurs() {
        return curs;
    }

    public void setCurs(Curs curs) {
        this.curs = curs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matricula)) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(getId(), matricula.getId()) &&
                Objects.equals(getAlumne(), matricula.getAlumne()) &&
                Objects.equals(getCurs(), matricula.getCurs());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getAlumne(), getCurs());
    }
}

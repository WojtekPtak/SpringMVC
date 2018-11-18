package beans.daos.mocks;

import beans.daos.db.AuditoriumDAOImpl;
import beans.models.Auditorium;

import java.util.List;


public class AuditoriumDAOMock extends AuditoriumDAOImpl {

    private final List<Auditorium> auditorias;

    public AuditoriumDAOMock(List<Auditorium> auditorias) {
        this.auditorias = auditorias;
    }

    public void init() {
        cleanup();
        auditorias.forEach(this :: add);
    }

    public void cleanup() {
        getAll().forEach(this :: delete);
    }
}

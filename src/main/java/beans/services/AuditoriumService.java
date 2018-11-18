package beans.services;

import beans.models.Auditorium;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/3/2016
 * Time: 11:04 AM
 */
public interface AuditoriumService {

    //TODO: we can add the same auditorium twice! FixIt!
    Auditorium add(Auditorium auditorium);

    List<Auditorium> getAuditoriums();

    Auditorium getByName(String name);

    int getSeatsNumber(String auditoriumName);

    List<Integer> getVipSeats(String auditoriumName);
}

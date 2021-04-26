package hu.nive.ujratervezes.kepesitovizsga.covid;

import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SinoPharm extends Vaccine {
    public SinoPharm(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Person> getVaccinationList() {
        List<Person> result = new ArrayList<>(super.getVaccinationList());
        List<Person> temp = result.stream()
                .filter(p -> p.getPregnant() == Pregnancy.nem)
                .filter(p -> p.getChronic() == ChronicDisease.nem)
                .collect(Collectors.toList());
        List<Person> old = temp.stream()
                .filter(p -> p.getAge() > 65)
                .collect(Collectors.toList());
        List<Person> etc = temp.stream()
                .filter(p -> p.getAge() < 66)
                .collect(Collectors.toList());

        result.clear();
        result.addAll(etc);
        result.addAll(old);
        return result;
    }
}

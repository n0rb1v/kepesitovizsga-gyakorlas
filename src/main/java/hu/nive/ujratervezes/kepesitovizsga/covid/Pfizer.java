package hu.nive.ujratervezes.kepesitovizsga.covid;

import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Pfizer extends Vaccine {
    public Pfizer(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Person> getVaccinationList() {
        List<Person> result = new ArrayList<>(super.getVaccinationList());
        List<Person> prg = result.stream()
                .filter(p -> p.getPregnant() == Pregnancy.igen)
                .collect(Collectors.toList());
        List<Person> old = result.stream()
                .filter(p -> p.getAge() > 65)
                .collect(Collectors.toList());
        List<Person> etc = result.stream()
                .filter(p -> p.getAge() < 66)
                .filter(p -> p.getPregnant() == Pregnancy.nem)
                .collect(Collectors.toList());

        result.clear();
        result.addAll(prg);
        result.addAll(old);
        result.addAll(etc);
        System.out.println(result);
        return result;
    }
}

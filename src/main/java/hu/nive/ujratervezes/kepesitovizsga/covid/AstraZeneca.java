package hu.nive.ujratervezes.kepesitovizsga.covid;

import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AstraZeneca extends Vaccine {
    @Override
    public List<Person> getVaccinationList() {
        List<Person> result = new ArrayList<>(super.getVaccinationList());
        List<Person> temp = result.stream()
                .filter(p -> p.getPregnant() == Pregnancy.nem)
                .collect(Collectors.toList());
        List<Person> chr = temp.stream()
                .filter(p -> p.getChronic() == ChronicDisease.igen)
                .collect(Collectors.toList());
        List<Person> old = temp.stream()
                .filter(p -> p.getChronic() == ChronicDisease.nem)
                .filter(p -> p.getAge() > 65)
                .collect(Collectors.toList());
        List<Person> etc = temp.stream()
                .filter(p -> p.getChronic() == ChronicDisease.nem)
                .filter(p -> p.getAge() < 66)
                .collect(Collectors.toList());

        result.clear();
        result.addAll(chr);
        result.addAll(old);
        result.addAll(etc);
        return result;
    }

    public AstraZeneca(DataSource dataSource) {
        super(dataSource);
    }
}

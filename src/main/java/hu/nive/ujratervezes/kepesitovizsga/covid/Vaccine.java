package hu.nive.ujratervezes.kepesitovizsga.covid;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class Vaccine {
    private List<Person> vaccinationList;
    private JdbcTemplate jdbc;

    public Vaccine(DataSource ds) {
        jdbc = new JdbcTemplate(ds);
        loadData();
    }

    private void loadData(){
        vaccinationList = jdbc.query("select * from registrations",
                (rs, index) -> new Person(
                        rs.getString("person_name"),
                        rs.getInt("age"),
                        ChronicDisease.valueOf(rs.getString("chronic_disease")),
                        Pregnancy.valueOf(rs.getString("pregnancy"))
                ));
    }

    public List<Person> getVaccinationList() {
        return vaccinationList;
    }
}

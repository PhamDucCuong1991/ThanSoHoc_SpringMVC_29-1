package dao;

import org.springframework.beans.factory.annotation.Autowired;
import trainers.controller.TrainersController;
import trainers.controller.TrainersService;
import model.Trainer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TrainerDAO {
    @Autowired
    EntityManager entityManager;



    //
    public List<Trainer> getAll() {
        String sql = "Select p from Trainer p";
        List<Trainer> trainers = entityManager.createQuery(sql).getResultList();
        return trainers;
    }
    //
    public void save(Trainer trainer) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.persist(trainer);
        txn.commit();
    }

    public void delete(Trainer trainer) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.remove(trainer);
        txn.commit();
    }
    //
    public void edit(Trainer trainer) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.merge(trainer);
        txn.commit();
    }

    public Trainer findTrainerById(int id) {
        String sql = "Select p from Trainer p where p.id = " + id;
        Trainer trainer = (Trainer) entityManager.createQuery(sql).getSingleResult();
        return trainer;
    }

    public String removeAccent(String s) {
        //removeAccent - hàm loại bỏ dấu tiếng Việt
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replace('đ','d').replace('Đ','D');
    }

    public List<Trainer> findListTrainerByName(String name) {

        name = name.trim();
        name = name.toUpperCase();
        name = removeAccent(name);
        List<Trainer> newList = new ArrayList<>();
        String sql = "Select p from Trainer p";
        List<Trainer> trainers = entityManager.createQuery(sql).getResultList();
        for (Trainer trainer : getAll()) {
            if (removeAccent(trainer.getName()).toUpperCase().contains(name)) {
                newList.add(trainer);
            }
        }
        return newList;
    }

}

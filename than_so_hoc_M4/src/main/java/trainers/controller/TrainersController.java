package trainers.controller;

import dao.TrainerDAO;
import model.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class TrainersController {

    @Autowired
    TrainerDAO trainerDAO;

    public List<Trainer> getAll() {
        return trainerDAO.getAll();
    }
    public void delete(int id) {
        trainerDAO.delete(trainerDAO.findTrainerById(id));
    }

    public Trainer findTrainerById(int id) {
        return  trainerDAO.findTrainerById(id);
    }

    public List<Trainer> findListTrainerByName(String name) {
       return trainerDAO.findListTrainerByName(name);
    }

    public void edit(Trainer trainer){
        trainerDAO.edit(trainer);
    }

    public void save(Trainer trainer){
        trainerDAO.save(trainer);
    }

    public int findIndexById(int id){
        for (Trainer trainer : getAll()) {
            if (trainer.getId()==id){
                return getAll().indexOf(trainer);
            }
        }
        return -1;
    }


}
